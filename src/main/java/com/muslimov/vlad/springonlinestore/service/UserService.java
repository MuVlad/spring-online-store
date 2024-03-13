package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.dto.UserDto;
import com.muslimov.vlad.springonlinestore.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto save(UserDto userDTO);

    void save(User user);

    List<UserDto> getAll();

    void delete(Long id);
}
