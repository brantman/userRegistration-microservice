package com.wangjun.microservices.userregistration.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.wangjun.microservices.userregistration.core.domain.User;
import com.wangjun.microservices.userregistration.rest.controller.UserRegistrationController;

public class UserInfo extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -4698356285504472524L;
	
	private String userId;
	private String emailAddress;
	private String password;
	
	public UserInfo() {
		super();
	}

	public UserInfo(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", emailAddress=" + emailAddress
				+ ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	public User toUser() {
		return new User(userId, emailAddress, password);
	}
	
	public static UserInfo fromUser(User user){
		UserInfo userInfo = new UserInfo();
		
		userInfo.setUserId(user.getId());
		userInfo.setEmailAddress(user.getEmailAddress());

		userInfo.add(linkTo(UserRegistrationController.class).slash(user.getId()).withSelfRel());

		return userInfo;
	}
	
	
}
