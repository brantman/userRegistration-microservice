package com.wangjun.microservices.userregistration.core.services;

public class DuplicateEmailException extends RuntimeException {
	private static final long serialVersionUID = 174057769893208455L;

	public DuplicateEmailException(String message) {
		super(message);
	}

	public DuplicateEmailException(String message, Throwable cause) {
		super(message, cause);
	}


}
