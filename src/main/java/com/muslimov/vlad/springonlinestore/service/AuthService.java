package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.dto.JwtRequestDto;
import com.muslimov.vlad.springonlinestore.dto.JwtResponseDto;

public interface AuthService {

    JwtResponseDto createAuthToken(JwtRequestDto jwtRequestDto);
}
