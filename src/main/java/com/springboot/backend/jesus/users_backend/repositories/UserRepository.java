package com.springboot.backend.jesus.users_backend.repositories;

import com.springboot.backend.jesus.users_backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);
}
