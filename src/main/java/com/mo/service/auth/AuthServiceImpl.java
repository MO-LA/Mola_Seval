package com.mo.service.auth;

import com.mo.domain.dto.auth.request.LoginDto;
import com.mo.domain.dto.auth.request.RegisterDto;
import com.mo.domain.dto.auth.res.LoginResDto;
import com.mo.domain.entity.User;
import com.mo.domain.repository.AuthRepo;
import com.mo.domain.repository.UserRepo;
import com.mo.enums.jwt.JwtAuth;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepo authRepository;
    private final UserRepo userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${auth.access}")
    private String ACCESS_SECRET_KEY;
    @Value("${auth.refresh}")
    private String REFRESH_SECRET_KEY;

    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Override
    @Transactional
    public void register(RegisterDto registerDto) {
        try {
            // 비밀번호 암호화
            registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            // 저장
            authRepository.save(registerDto.toEntity());
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(e.getStatusCode(), e.getResponseBodyAsString());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResDto login(LoginDto loginDto) {
        User loginUser = this.isThereUserById(loginDto.getId());

        if(passwordEncoder.matches(loginDto.getPassword(), loginUser.getPassword())) {
            String accessToken = this.createToken(loginUser.getIdx(), JwtAuth.ACCESS);
            String refreshToken = this.createToken(loginUser.getIdx(), JwtAuth.REFRESH);


            return new LoginResDto(accessToken, refreshToken);
        } else throw new BadCredentialsException("비밀번호 불일치");
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isThereId(String id) {
        return authRepository.findUserById(id).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public User isThereUserById(String id) {
        return authRepository.findUserById(id).orElseThrow(
                () -> new UsernameNotFoundException("없는 아이디입니다.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean IdDuplicateVerification(String id) {
        if (isThereId(id)) {
           return false;
        } else return true;
    }

    public String createToken(Long idx, JwtAuth authType) {
        Date expiredAt = new Date();
        byte[] keyBytes = null;

        if(authType == JwtAuth.ACCESS) {
            keyBytes = Decoders.BASE64.decode(ACCESS_SECRET_KEY);

            expiredAt = new Date(expiredAt.getTime() + 1000 * 60 * 60);
        } else if (authType == JwtAuth.REFRESH) {
            keyBytes = Decoders.BASE64.decode(REFRESH_SECRET_KEY);
            expiredAt = new Date(expiredAt.getTime() + 1000 * 60 * 60 * 24 * 7);
        }

        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject(idx.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredAt.getTime()))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    @Transactional(readOnly = true)
    public User accessTokenDecoding(String token) {
        try {
            Claims claims = decodingToken(token, ACCESS_SECRET_KEY);
            Long idx = Long.valueOf(claims.getSubject()).longValue();
            return userRepository.findById(idx).orElseGet(() -> {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 유저");
            });

        } catch (Exception e) {
            throw e;
        }
    }

    private Claims decodingToken(String token, String key) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.GONE, "토큰 만료");
        } catch (SignatureException | MalformedJwtException e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조");
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }
    }
}
