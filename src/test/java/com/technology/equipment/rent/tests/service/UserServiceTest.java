package com.technology.equipment.rent.tests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.technology.equipment.rent.entity.User;
import com.technology.equipment.rent.repository.UserRepository;
import com.technology.equipment.rent.service.impl.UserServiceImpl;
import com.technology.equipment.rent.utils.MessageUtils;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private User user;

	@BeforeEach
	void setUp() throws Exception {
		user = new User(4L, "test", "test@test.com");
	}
	
	@Test
	void testGetUsersNotFound() {
		when(userRepository.findAll()).thenReturn(null);
		ResponseEntity<?> response = userService.getUsers();
		assertEquals(MessageUtils.USERS_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userRepository).findAll();
	}
	
	@Test
	void testGetUsersFound() {
		when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
		ResponseEntity<?> response = userService.getUsers();
		assertEquals(Collections.singletonList(user), response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testGetUserFound() {
		when(userRepository.findById(4L)).thenReturn(Optional.of(user));
		ResponseEntity<?> response = userService.getUser(4L);
		assertEquals(user, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(userRepository).findById(4L);
	}
	
	@Test
	void testGetUserNotFound() {
		when(userRepository.findById(5L)).thenReturn(Optional.ofNullable(null));
		ResponseEntity<?> response = userService.getUser(5L);
		assertEquals(MessageUtils.USER_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userRepository).findById(5L);
	}

	@Test
	void testGetUserByUsernameFound() {
		when(userRepository.findByUsername("test")).thenReturn(user);
		ResponseEntity<?> response = userService.getUserByUsername("test");
		assertEquals(user, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(userRepository).findByUsername("test");
	}

	@Test
	void testGetUserByUsernameNotFound() {
		when(userRepository.findByUsername("test2")).thenReturn(null);
		ResponseEntity<?> response = userService.getUserByUsername("test2");
		assertEquals(MessageUtils.USER_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userRepository).findByUsername("test2");
	}
	
	@Test
	void testSaveUser() {
		when(userRepository.save(user)).thenReturn(user);
		ResponseEntity<?> response = userService.saveUser(user);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(userRepository).save(user);
	}
	
	@Test
	void testDeleteUserFound() {
		when(userRepository.existsById(4L)).thenReturn(true);
		ResponseEntity<?> response = userService.deleteUser(4L);
		assertEquals(MessageUtils.USER_DELETED, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(userRepository).existsById(4L);
	}
	
	@Test
	void testDeleteUserNotFound() {
		when(userRepository.existsById(5L)).thenReturn(false);
		ResponseEntity<?> response = userService.deleteUser(5L);
		assertEquals(MessageUtils.USER_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userRepository).existsById(5L);
	}

}
