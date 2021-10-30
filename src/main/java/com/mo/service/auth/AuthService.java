package com.mo.service.auth;

import com.mo.domain.dto.auth.request.LoginDto;
import com.mo.domain.dto.auth.request.RegisterDto;
import com.mo.domain.dto.auth.res.LoginResDto;
import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {
    @Transactional
    void register(RegisterDto registerDto);

    @Transactional(readOnly = true)
    LoginResDto login(LoginDto loginDto);

    @Transactional(readOnly = true)
    boolean isThereId(String id);

    @Transactional(readOnly = true)
    User isThereUserById(String id);

    @Transactional(readOnly = true)
    String IdDuplicateVerification(String id);

    @Transactional(readOnly = true)
    User accessTokenDecoding(String token);
}
