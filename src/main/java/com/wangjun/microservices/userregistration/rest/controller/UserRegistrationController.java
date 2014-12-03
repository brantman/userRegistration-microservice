package com.wangjun.microservices.userregistration.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangjun.microservices.userregistration.core.domain.User;
import com.wangjun.microservices.userregistration.core.services.DuplicateEmailException;
import com.wangjun.microservices.userregistration.core.services.UserRegistrationService;
import com.wangjun.microservices.userregistration.rest.domain.UserInfo;

@RestController
@RequestMapping(value = "/users/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationService userRegistrationService;

	@RequestMapping(method = RequestMethod.POST)
	public UserInfo registerUser(@RequestBody UserInfo newUserInfo){
		
		User registeredUser = userRegistrationService.registerUser(newUserInfo.toUser()); 
		
		return UserInfo.fromUser(registeredUser);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public UserInfo getUser(@PathVariable String userId){
		
		User registeredUser = userRegistrationService.getUser(userId); 
		
		return UserInfo.fromUser(registeredUser);
	}

	@ExceptionHandler(DuplicateEmailException.class)
	ResponseEntity<String> duplicateEmailAddress(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

}
