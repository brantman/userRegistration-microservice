package com.wangjun.microservices.userregistration.core.services;

import static com.wangjun.microservices.userregistration.config.MessagingNames.exchangeName;
import static com.wangjun.microservices.userregistration.config.MessagingNames.routingKey;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.wangjun.microservices.userregistration.core.domain.User;
import com.wangjun.microservices.userregistration.core.events.UserRegisteredEvent;
import com.wangjun.microservices.userregistration.core.repository.UserRepository;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public User registerUser(User user) throws DuplicateEmailException {

		User registeredUser = null;
		try {
			registeredUser = userRepository.save(user);

			rabbitTemplate.convertAndSend(exchangeName,routingKey,new UserRegisteredEvent(registeredUser.getId(),
							registeredUser.getEmailAddress(), registeredUser.getPassword()));
		} catch (DuplicateKeyException e) {
			throw new DuplicateEmailException("duplicate email address", e);
		}

		return registeredUser;
	}

	@Override
	public User getUser(String userId) {
		return userRepository.findOne(userId);
	}

}
