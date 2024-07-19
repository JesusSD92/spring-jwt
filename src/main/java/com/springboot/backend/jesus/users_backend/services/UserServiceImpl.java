package com.springboot.backend.jesus.users_backend.services;

import com.springboot.backend.jesus.users_backend.entities.User;
import com.springboot.backend.jesus.users_backend.exceptions.UserNotFoundException;
import com.springboot.backend.jesus.users_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(Long idUser) {
        userRepository.deleteById(idUser);
    }
}
