package com.springboot.backend.jesus.users_backend.services;

import com.springboot.backend.jesus.users_backend.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findUserById(Long idUser);

    List<User> findAllUsers();

    void deleteUser(Long idUser);
}
