package com.springboot.backend.jesus.users_backend.services;

import com.springboot.backend.jesus.users_backend.entities.User;
import java.util.List;

public interface UserService {

    User saveUser(User user);

    User findUserById(Long idUser);

    List<User> findAllUsers();

    void deleteUser(Long idUser);
}
