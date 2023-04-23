package com.technology.equipment.rent.tests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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
import com.technology.equipment.rent.service.UserService;
import com.technology.equipment.rent.utils.MessageUtils;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	private User user;
	private ResponseEntity<?> responseOK;
	private ResponseEntity<?> responseNotFound;
	
	@BeforeEach
	public void setUp() {
		user = new User(4L, "test", "test@test.com");
		responseOK = new ResponseEntity<>(user, HttpStatus.OK);
		responseNotFound = new ResponseEntity<>(MessageUtils.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void getUserByUsernameEquals() {
		when(userRepository.findByUsername("test")).thenReturn(user);
		assertEquals(user, responseOK.getBody());
		assertEquals(HttpStatus.OK, responseOK.getStatusCode());
	}
	
	@Test
	public void getUserByUsernameNotEquals() {
		when(userRepository.findByUsername("test2")).thenReturn(user);
		assertEquals(MessageUtils.USER_NOT_FOUND, responseNotFound.getBody());
		assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
	}

}
