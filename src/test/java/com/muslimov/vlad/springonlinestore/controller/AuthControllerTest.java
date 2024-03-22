package com.muslimov.vlad.springonlinestore.controller;

import com.muslimov.vlad.springonlinestore.BaseIntegrationTest;
import com.muslimov.vlad.springonlinestore.model.Role;
import com.muslimov.vlad.springonlinestore.model.User;
import com.muslimov.vlad.springonlinestore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends BaseIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @DisplayName("Регистрация нового пользователя")
    void createNewUser() throws Exception {
        //given
        userRepository.save(User.builder()
            .username("Test user")
            .password("123")
            .role(Role.CLIENT)
            .email("test@mail.ru")
            .bucket(null)
            .build()
        );

        String requestBody = """
            {
                "username": "Test user",
                "password": "123",
                "matchingPassword": "123",
                "email": "test@mail.ru"
              }
            """;

        String expectedBody = """
            {
                "username": "Test user",
                "password": "123",
                "matchingPassword": null,
                "email": "test@mail.ru"
              }
            """;

        //when
        mockMvc.perform(post("/api/v1/registration")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
            )
            //then
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(expectedBody)
            );
    }
}