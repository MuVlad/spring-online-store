package com.muslimov.vlad.springonlinestore.dto;

public record UserDto(
    String username,
    String password,
    String matchingPassword,
    String email
) {
}
