/**
 * 
 */
package com.wangjun.microservices.userregistration.config;

import static com.wangjun.microservices.userregistration.config.MessagingNames.exchangeName;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.wangjun.microservices.userregistration.core.services.UserRegistrationService;
import com.wangjun.microservices.userregistration.core.services.UserRegistrationServiceImpl;

/**
 * @author Jun
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.wangjun.microservices.userregistration"})
@EnableMongoRepositories(basePackages = "com.wangjun.microservices.userregistration.core.repository")
public class CoreConfig {

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	UserRegistrationService userRegistrationService() {
		return new UserRegistrationServiceImpl();
	}
}
