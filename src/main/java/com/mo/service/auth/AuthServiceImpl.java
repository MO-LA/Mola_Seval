package com.mo.service.auth;

import com.mo.domain.dto.auth.request.RegisterDto;
import com.mo.domain.repository.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepo authRepository;

    private final PasswordEncoder passwordEncoder;

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
}
