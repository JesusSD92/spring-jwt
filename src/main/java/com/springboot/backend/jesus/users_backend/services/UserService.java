package com.springboot.backend.jesus.users_backend.services;

import com.springboot.backend.jesus.users_backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findUserById(Long idUser);

    List<User> findAllUsers();

    Page<User> findAll(Pageable pageable);

    void deleteUser(Long idUser);
}
