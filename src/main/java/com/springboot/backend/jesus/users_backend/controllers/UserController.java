package com.springboot.backend.jesus.users_backend.controllers;

import com.springboot.backend.jesus.users_backend.entities.User;
import com.springboot.backend.jesus.users_backend.repositories.UserRepository;
import com.springboot.backend.jesus.users_backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    //CRUD
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id){
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return validateErrors(result);
        }
        User userCreated = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()){
            return validateErrors(result);
        }
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User userDb = userOptional.get();
            userDb.setEmail(user.getEmail());
            userDb.setName(user.getName());
            userDb.setLastName(user.getLastName());
            userDb.setUsername(user.getUsername());
            userDb.setPassword(user.getPassword());
            return ResponseEntity.ok(userService.saveUser(userDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findUserById(id);
        if(userOptional.isPresent()){
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Método auxiliar para validar errores en los endpoints.
    private ResponseEntity<?> validateErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
