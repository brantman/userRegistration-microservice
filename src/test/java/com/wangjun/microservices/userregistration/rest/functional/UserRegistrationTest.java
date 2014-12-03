package com.wangjun.microservices.userregistration.rest.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.wangjun.microservices.userregistration.rest.domain.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestCoreConfig.class)
@WebAppConfiguration
@IntegrationTest
public class UserRegistrationTest {
	
	@Autowired
	private Receiver receiver;
	

	@Test
	public void shouldRegisterUser() {
		//Given
		String email = System.currentTimeMillis() + "-a-foo@bar.com";
		
		//When
		ResponseEntity<UserInfo> entity = registerUser(email);

		//Then
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		
		UserInfo registeredUser = entity.getBody();
		System.out.println ("The registered user ID is " + registeredUser.getUserId());
		
		String receivedMessages = receiver.getMessages().toString();
		assertTrue(receivedMessages + " expected to contain " + email, receivedMessages.indexOf(email) != -1);
	}
	
	@Test
	public void shouldRejectDuplicateUser() {
		//Given
		String email = System.currentTimeMillis() + "-b-foo@bar.com";
		
		//When
		ResponseEntity<UserInfo> entity = registerUser(email);

		//Then
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		
		try{
			registerUser(email);
			fail("expected user registration to fail because of duplicate email address.");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.CONFLICT, e.getStatusCode());
		}
		
	}
	
	@Test
	public void userShouldHaveCorrectHateoasLinks() {
		//Given
		String email = System.currentTimeMillis() + "-a-foo@bar.com";
		
		//When
		ResponseEntity<UserInfo> entity = registerUser(email);
		
		//Then
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		
		UserInfo registeredUser = entity.getBody();
		String registeredUserUrl = "/users/registration/" + registeredUser.getUserId();
		
		assertTrue(registeredUser.getLink("self").getHref().endsWith(registeredUserUrl));
	}
	
	private ResponseEntity<UserInfo> registerUser(String email) {
		String password = "secret";
		UserInfo userInfo = new UserInfo(email, password);
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForEntity("http://localhost:8080/users/registration", userInfo, UserInfo.class);
	}
}
