package com.muslimov.vlad.springonlinestore.controller;

import com.muslimov.vlad.springonlinestore.dto.JwtRequestDto;
import com.muslimov.vlad.springonlinestore.dto.JwtResponseDto;
import com.muslimov.vlad.springonlinestore.dto.UserDto;
import com.muslimov.vlad.springonlinestore.service.AuthService;
import com.muslimov.vlad.springonlinestore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth")
    @Operation(
        summary = "Аутентификация пользователя",
        description = "При успешной аутентификации пользователь получает токен"
    )
    public HttpEntity<JwtResponseDto> createAuthToken(@RequestBody JwtRequestDto jwtRequestDto) {
        return ResponseEntity.ok(authService.createAuthToken(jwtRequestDto));
    }

    @PostMapping("/registration")
    @Operation(summary = "Создание нового пользоваьеля")
    public HttpEntity<UserDto> createNewUser(@RequestBody UserDto UserDto) {
        return ResponseEntity.ok(userService.save(UserDto));
    }
}