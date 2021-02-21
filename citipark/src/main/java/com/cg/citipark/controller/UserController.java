package com.cg.citipark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.citipark.beans.User;
import com.cg.citipark.service.MapValidationErrorService;
import com.cg.citipark.service.UserService;

@RestController	
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/api/adduser")
	public ResponseEntity<?> addUser(@RequestBody User user,BindingResult result)
	{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null) 
			return errorMap;
		User registerUser = userService.addUser(user);
		return new ResponseEntity<User>(registerUser, HttpStatus.OK);
	}
	
	@GetMapping("/user/getUserByEmail/{email}")
	public  ResponseEntity<?> getUserByEmail(@PathVariable String email)
	{
		
		return new ResponseEntity<User>(userService.getUserByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/user/getUserById/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable int userId) {
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@DeleteMapping("/user/deleteUserById/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) {
		boolean status = userService.deleteUserById(userId);
		if(status)
			return new ResponseEntity<String>("User with Id: "+userId+" deleted", HttpStatus.OK);
		return new ResponseEntity<String>("Failed", HttpStatus.OK);
	}
	
	@PutMapping("/user/update")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	

}
