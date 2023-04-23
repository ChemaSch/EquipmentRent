package com.technology.equipment.rent.service;

import org.springframework.http.ResponseEntity;

import com.technology.equipment.rent.entity.User;

public interface UserService {
	
	public ResponseEntity<?> getUsers();
	
	public ResponseEntity<?> getUser(Long id);
	
	public ResponseEntity<?> getUserByUsername(String username);
	
	public ResponseEntity<?> saveUser(User user);
	
	public ResponseEntity<?> updateUser(User user, Long id);
	
	public ResponseEntity<?> deleteUser(Long id);
	
	public ResponseEntity<?> login(User user);

}
