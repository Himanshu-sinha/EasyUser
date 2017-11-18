package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.model.User;

/**
 * Created by Himanshu Sinha.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
