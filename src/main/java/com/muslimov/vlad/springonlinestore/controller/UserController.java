package com.muslimov.vlad.springonlinestore.controller;

import com.muslimov.vlad.springonlinestore.dto.UserDto;
import com.muslimov.vlad.springonlinestore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Получение списка пользователей")
    public HttpEntity<List<UserDto>> getUsers() {

        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление клиента")
    public HttpEntity<?> deleteClient(@PathVariable("id") Long id) {

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}