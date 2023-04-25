package com.technology.equipment.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.technology.equipment.rent.entity.User;
import com.technology.equipment.rent.service.UserService;
import com.technology.equipment.rent.utils.MessageUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "getUsers", notes = "Endpoint that allows obtaining a list of users registered to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.USERS_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.USERS_NOT_FOUND)})
	@GetMapping(path = "/users")
	public ResponseEntity<?> getUsers() {
		return userService.getUsers();
	}

	@ApiOperation(value = "getUser", notes = "Endpoint that allows obtaining an user registered to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.USER_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.USER_NOT_FOUND)})
	@GetMapping(path = "/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@ApiOperation(value = "getUserByUsername", notes = "Endpoint that allows obtaining an user by username registered to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.USER_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.USER_NOT_FOUND)})
	@GetMapping(path = "/users/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	@ApiOperation(value = "saveUser", notes = "Endpoint that allows record an user to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = MessageUtils.USER_CREATED)})
	@PostMapping(path = "/users")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@ApiOperation(value = "updateUser", notes = "Endpoint that allows updating an user to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.USER_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.USER_NOT_FOUND)})
	@PutMapping(path = "/users/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
		return userService.updateUser(user, id);
	}

	@ApiOperation(value = "deleteUser", notes = "Endpoint that allows deleting an user to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.USER_DELETED),
			@ApiResponse(code = 404, message = MessageUtils.USER_NOT_FOUND)})
	@DeleteMapping(path = "/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	@ApiOperation(value = "login", notes = "Endpoint that allows an user to login to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.USER_LOGIN),
			@ApiResponse(code = 404, message = MessageUtils.USER_NOT_FOUND)})
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		return userService.login(user);
	}

}
