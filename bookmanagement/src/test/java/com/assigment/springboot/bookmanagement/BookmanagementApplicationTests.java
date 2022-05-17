package com.assigment.springboot.bookmanagement;

import com.assigment.springboot.bookmanagement.dao.UserRepository;
import com.assigment.springboot.bookmanagement.entity.User;
import com.assigment.springboot.bookmanagement.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookmanagementApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void getUsersTest(){
		when(userRepository.findAll()).thenReturn(Stream.of(new User("manish","hello123","ADMIN"),new User("yash","hello123","CUSTOMER")).collect(Collectors.toList()));
		assertEquals(2,userService.findAll().size());
	}

}
