package com.springboot.backend.jesus.users_backend.services;

import com.springboot.backend.jesus.users_backend.dtos.IUser;
import com.springboot.backend.jesus.users_backend.dtos.UserDto;
import com.springboot.backend.jesus.users_backend.entities.Role;
import com.springboot.backend.jesus.users_backend.entities.User;
import com.springboot.backend.jesus.users_backend.repositories.RoleRepository;
import com.springboot.backend.jesus.users_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(getRoles(user));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(UserDto user, Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User userDb = userOptional.get();
            userDb.setEmail(user.getEmail());
            userDb.setName(user.getName());
            userDb.setLastName(user.getLastName());
            userDb.setUsername(user.getUsername());
            userDb.setRoles(getRoles(user));

            return Optional.of(userRepository.save(userDb));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long idUser) {
        return userRepository.findById(idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void deleteUser(Long idUser) {
        userRepository.deleteById(idUser);
    }

    //Metodo auxiliar para obtener los roles del usuario
    private List<Role> getRoles(IUser user){

        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        optionalRoleUser.ifPresent(roles::add);
        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        return roles;
    }
}
