package com.springboot.basicauth.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.basicauth.entities.User;





@RestController
//@RequestMapping("/secure")
public class UserController {

	
	@RequestMapping(path="/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>>  listUser(){
		
			List<User> users = getUsers();
			users.forEach(user -> System.out.println("user : "+user.getName()+" ,  id: "+user.getId()));
			//users.forEach(System.out::println);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable(value = "id") String id) {
		
		return getUsers().stream().filter(user1 -> user1.getId().equals(id)).findFirst().orElse(null);
	  
	}
	

	  private List<User> getUsers() {
			User user = new User();
			user.setId("1");
			user.setEmail("abbas123@gmail.com");
			user.setName("Ghulam Abbas");
			user.setAddress("Sydeny, Aus");
			User user1 = new User();
			user1.setId("2");
			user1.setEmail("Musa@gmail.com");
			user1.setName("Muhammad Musa");
			user1.setAddress("Sydney, Aus");
			User user2 = new User();
			user2.setId("3");
			user2.setEmail("Rahim@gmail.com");
			user2.setName("Abdul Rahim");
			user2.setAddress("UK, Bermingham");
			User user3 = new User();
			user3.setId("4");
			user3.setEmail("Zeeshan@gmail.com");
			user3.setName("Zeeshan khan");
			user3.setAddress("Islamabad , PK");
			return Arrays.asList(user, user1, user2, user3);
		}

}
