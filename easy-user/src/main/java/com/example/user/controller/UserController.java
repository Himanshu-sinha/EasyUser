package com.example.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;

/**
 * Created by Himanshu Sinha.
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateNote(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setActive(userDetails.isActive());
		user.setBirthDate(userDetails.getBirthDate());
		user.setEmail(userDetails.getEmail());
		user.setfName(userDetails.getfName());
		user.setlName(userDetails.getlName());
		user.setPinCode(userDetails.getPinCode());
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteNote(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
}
