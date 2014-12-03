package com.wangjun.microservices.userregistration.config;

public class MessagingNames {
	public static final String queueName = "user-registered";
	public static final String routingKey = queueName;
	public static final String exchangeName = "user-registered-exchange";
}
