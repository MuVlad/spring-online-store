package com.muslimov.vlad.springonlinestore.mapper;

import com.muslimov.vlad.springonlinestore.dto.UserDto;
import com.muslimov.vlad.springonlinestore.model.Role;
import com.muslimov.vlad.springonlinestore.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    UserMapper userMapper = new UserMapperImpl();

    @Test
    void toDto() {
        //given
        User user = User.builder()
            .id(100L)
            .username("Test User")
            .email("Test email")
            .password("pass")
            .role(Role.CLIENT)
            .bucket(null)
            .build();

        UserDto expected = new UserDto(
            "Test User",
            "pass",
            null,
            "Test email"
        );

        //when
        final var actual = userMapper.toDto(user);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void toEntity() {
        //given
        User expected = User.builder()
            .username("Test User")
            .email("Test email")
            .password("pass")
            .build();

        UserDto userDto = new UserDto(
            "Test User",
            "123",
            "123",
            "Test email"
        );

        //when
        final var actual = userMapper.toEntity(userDto);

        //then
        assertEquals(expected, actual);
    }
}