package com.muslimov.vlad.springonlinestore.repository;

import com.muslimov.vlad.springonlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByName(String name);
}
