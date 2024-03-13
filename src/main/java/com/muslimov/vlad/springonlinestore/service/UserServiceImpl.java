package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.dto.UserDto;
import com.muslimov.vlad.springonlinestore.exception.model.BadRequestException;
import com.muslimov.vlad.springonlinestore.exception.model.NotFoundException;
import com.muslimov.vlad.springonlinestore.mapper.UserMapper;
import com.muslimov.vlad.springonlinestore.model.Role;
import com.muslimov.vlad.springonlinestore.model.User;
import com.muslimov.vlad.springonlinestore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto save(UserDto userDto) {

        if (!Objects.equals(userDto.password(), userDto.matchingPassword())) {
            throw new BadRequestException("Пароли не совпадают");
        }

        final var user = userMapper.toEntity(userDto);
        user.setRole(Role.CLIENT);
        final var saveUser = userRepository.save(user);
        return userMapper.toDto(saveUser);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAll() {

        return userRepository
            .findAll()
            .stream()
            .map(userMapper::toDto)
            .toList();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByName(String name) {

        return userRepository.findFirstByName(name).orElseThrow(
            () -> new NotFoundException("Пользователь с именем:" + name + " не найден!")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByName(username);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
            user.getName(),
            user.getPassword(),
            roles
        );
    }
}