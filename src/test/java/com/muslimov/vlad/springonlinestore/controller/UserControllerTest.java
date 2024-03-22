package com.muslimov.vlad.springonlinestore.controller;

import com.muslimov.vlad.springonlinestore.BaseIntegrationTest;
import com.muslimov.vlad.springonlinestore.model.Role;
import com.muslimov.vlad.springonlinestore.model.User;
import com.muslimov.vlad.springonlinestore.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Endpoint Users")
class UserControllerTest extends BaseIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Поиск всех пользователей")
    void getUsers() throws Exception {
        //given
        String expectedBody = """
            [
              {
                "username": "admin",
                "password": "$2a$10$QRWAbXveneW1d.EKZPnxV.D7hjqPfw9Ex4nJuJpLgiFCDFopflogC",
                "matchingPassword": null,
                "email": "mailadmin@mail.ru"
              },
              {
                "username": "client",
                "password": "$2a$10$QRWAbXveneW1d.EKZPnxV.D7hjqPfw9Ex4nJuJpLgiFCDFopflogC",
                "matchingPassword": null,
                "email": "mailclient@mail.ru"
              }
            ]
            """;
        //when
        mockMvc.perform(get("/api/v1/users")
                .with(
                    user("admin")
                        .password("pass")
                        .roles("ADMIN")
                )
            )
            //then
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(expectedBody)
            );
    }

    @Test
    void deleteUser() throws Exception {
        //given
        final var user = userRepository.save(User.builder()
            .username("Test user")
            .password("123")
            .role(Role.CLIENT)
            .email("test@mail.ru")
            .bucket(null)
            .build()
        );

        assertTrue(userRepository.findById(user.getId()).isPresent());

        //when
        mockMvc.perform(delete("/api/v1/users/" + user.getId())
                .with(
                    user("admin")
                        .password("pass")
                        .roles("ADMIN")
                )
            )
            //then
            .andExpect(status().isNoContent());

        assertFalse(userRepository.findById(user.getId()).isPresent());
    }
}