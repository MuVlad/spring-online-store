package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.configuration.security.JwtTokenUtils;
import com.muslimov.vlad.springonlinestore.dto.JwtRequestDto;
import com.muslimov.vlad.springonlinestore.dto.JwtResponseDto;
import com.muslimov.vlad.springonlinestore.exception.model.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponseDto createAuthToken(JwtRequestDto jwtRequestDto) {

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequestDto.name(), jwtRequestDto.password())
            );
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Неверный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(jwtRequestDto.name());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponseDto(token);
    }
}