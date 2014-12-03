package com.wangjun.microservices.userregistration.rest.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.wangjun.microservices.userregistration.core.domain.User;
import com.wangjun.microservices.userregistration.core.services.UserRegistrationService;

public class UserRegistrationControllerTest {
	
	MockMvc mockMvc;
	
	@InjectMocks
	UserRegistrationController controller;
	
	@Mock
	UserRegistrationService userRegistrationService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).setMessageConverters(
				new MappingJackson2HttpMessageConverter()).build();
	}
	
	@Test
	public void shouldRegisterUserJson() throws Exception {
		given(userRegistrationService.registerUser(any(User.class))).willReturn(
				new User("-1","foo@bar.com","secret"));
		
		this.mockMvc.perform(
				post("/users/registration")
				.content(newUserInfoJson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId").value("-1"))
				.andExpect(jsonPath("$.emailAddress").value("foo@bar.com"))
				.andExpect(jsonPath("$.password").value(isNull()))
				.andExpect(jsonPath("$.links[?(@.rel == self)].href").value("http://localhost/users/registration/-1"));
	}
	
	private static String newUserInfoJson() {
		return "{ \"emailAddress\": \"foo@bar.com\", \"password\": \"secret\" }";
	}

}
