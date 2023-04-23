package com.technology.equipment.rent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.equipment.rent.entity.User;
import com.technology.equipment.rent.repository.UserRepository;
import com.technology.equipment.rent.service.UserService;
import com.technology.equipment.rent.utils.MessageUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository usuarioRepository;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getUsers() {
		List<User> usersDB = (List<User>) usuarioRepository.findAll();

		if (usersDB == null || usersDB.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(usersDB, HttpStatus.OK);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getUser(Long id) {
		return usuarioRepository.findById(id).map((userDB) -> {
			return new ResponseEntity<>(userDB, HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		});
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getUserByUsername(String username) {
		User userDB = usuarioRepository.findByUsername(username);
		if (userDB == null) {
			return new ResponseEntity<>(MessageUtils.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(userDB, HttpStatus.OK);
		}
		
	}

	@Override
	@Transactional
	public ResponseEntity<?> saveUser(User user) {
		return new ResponseEntity<>(usuarioRepository.save(user), HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<?> updateUser(User user, Long id) {
		return usuarioRepository.findById(id).map((userDB) -> {
			updateUserProperties(user, userDB);
			return new ResponseEntity<>(usuarioRepository.save(userDB), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		});
	}

	@Override
	public ResponseEntity<?> deleteUser(Long id) {
		return usuarioRepository.findById(id).map((userDB) -> {
			usuarioRepository.delete(userDB);
			return new ResponseEntity<>(MessageUtils.USER_DELETED, HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(MessageUtils.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		});
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> login(User user) {
		User userDB = usuarioRepository.findByUsername(user.getUsername());

		if (userDB == null) {
			return new ResponseEntity<>(MessageUtils.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(userDB, HttpStatus.OK);
		}
	}
	
	private void updateUserProperties(User user, User userDB) {
		userDB.setUsername(user.getUsername());
		userDB.setEmail(user.getEmail());
	}

}
