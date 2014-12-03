package com.wangjun.microservices.userregistration.rest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wangjun.microservices.userregistration.core.domain.User;

public class UserInfoTest {
	
	@Test
	public void shouldConvertToUser() {
		//Given
		UserInfo userInfo = new UserInfo("foo@bar.com", "secret");
		
		//When
		User user = userInfo.toUser();
		
		//Then
		assertEquals(user.getId(), userInfo.getUserId());
		assertEquals(user.getEmailAddress(), userInfo.getEmailAddress());
		assertEquals(user.getPassword(), userInfo.getPassword());
	}

	@Test
	public void shouldConvertFromUser() {
		//Given
		User user = new User("-1", "foo@bar.com", "secret");
		//support Hateoas
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		//When
		UserInfo userInfo = UserInfo.fromUser(user);
		
		//Then
		assertEquals(user.getId(), userInfo.getUserId());
		assertEquals(user.getEmailAddress(), userInfo.getEmailAddress());
		assertTrue(userInfo.getLink("self").getHref().endsWith("/users/registration/" + user.getId()));
	}
}
