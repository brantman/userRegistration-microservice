package com.wangjun.microservices.userregistration.rest.functional;

import static com.wangjun.microservices.userregistration.config.MessagingNames.queueName;
import static com.wangjun.microservices.userregistration.config.MessagingNames.routingKey;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.wangjun.microservices.userregistration.config.CoreConfig;

@Configuration
@Import({CoreConfig.class})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.wangjun.microservices.userregistration.rest.functional"})
public class TestCoreConfig {

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	Receiver receiver() {
		return new Receiver();
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

}
