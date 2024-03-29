package com.muslimov.vlad.springonlinestore.repository;

import com.muslimov.vlad.springonlinestore.exception.model.NotFoundException;
import com.muslimov.vlad.springonlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User  findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new NotFoundException("ользователь с id:" + id + " не найден!")
        );
    }

    Optional<User> findFirstByUsername(String name);
}
