package com.mo.service.auth;

import com.mo.domain.dto.auth.request.LoginDto;
import com.mo.domain.dto.auth.request.RegisterDto;
import com.mo.domain.entity.User;
import com.mo.domain.repository.AuthRepo;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepo authRepository;

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
    public boolean login(LoginDto loginDto) {
        String pw = this.isThereUserById(loginDto.getId()).getPassword();

        System.out.println(pw);
        System.out.println(loginDto.getPassword());

        if(passwordEncoder.matches(loginDto.getPassword(), pw)) {
            System.out.println("ㅇㅇ");
            return true;
        } else {
            System.out.println("ㄴㄴ");
            return false;
        }
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
                () -> new IllegalArgumentException("없는 아이디입니다.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public String IdDuplicateVerification(String id) {
        if (isThereId(id)) {
           return "사용 불가능한 id 입니다.";
        } else return "사용 가능한 id 입니다.";
    }
}
