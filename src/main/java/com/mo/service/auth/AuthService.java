package com.mo.service.auth;

import com.mo.domain.dto.auth.request.RegisterDto;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {
    @Transactional
    void register(RegisterDto registerDto);
}
