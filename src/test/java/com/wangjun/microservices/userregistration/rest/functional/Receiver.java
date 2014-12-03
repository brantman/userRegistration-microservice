package com.wangjun.microservices.userregistration.rest.functional;

import com.wangjun.microservices.userregistration.core.events.UserRegisteredEvent;

public class Receiver {
	private StringBuffer messages = new StringBuffer();

	public StringBuffer getMessages() {
		return messages;
	}

	public void receiveMessage(UserRegisteredEvent userRegisteredEvent) {
		messages.append(userRegisteredEvent.toString());
		System.out.println("Registered email<" + userRegisteredEvent.getEmailAddress() + ">");
	}
}
