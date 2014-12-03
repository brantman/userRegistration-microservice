/**
 * 
 */
package com.wangjun.microservices.userregistration.core.services;

import com.wangjun.microservices.userregistration.core.domain.User;


public interface UserRegistrationService {
	
	public User registerUser(User user) throws DuplicateEmailException;
	public User getUser(String userId);

}
