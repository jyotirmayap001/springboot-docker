package com.jyotirmaya.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyotirmaya.user.entity.Users;
import com.jyotirmaya.user.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/user/")
public class UserServiceController {

	@Autowired
	public UserService userService;
	
	//Save user
	@PostMapping("/saveUsers")
	public ResponseEntity<?> saveUsers(@RequestBody Users user ){
		
		 Users saveUser = userService.saveUser(user);
		 
		 return new ResponseEntity<>(saveUser,HttpStatus.CREATED);
	}
	
	@PostMapping("/saveMutipleUsers")
	public ResponseEntity<?> saveMultipleUsers(@RequestBody List<Users> user ){
		
		List<Users> saveAllUser = userService.saveAllUser(user);
		 
		 return new ResponseEntity<>(saveAllUser,HttpStatus.CREATED);
	}
	
	//Get all the user details 
	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers(){
		
		 List<Users> userDetails = userService.getUsers();
		 
		 return new ResponseEntity<>(userDetails,HttpStatus.OK);
	}
	
	//Get the user details by user id
	@GetMapping("/getUsers/{userid}")
	public ResponseEntity<?> getUsers(@PathVariable("userid") Long userid){
		
		  Users usersById = userService.getUsersById(userid);
		 
		 return new ResponseEntity<>(usersById,HttpStatus.OK);
	}
	
	//Get the user details by designation
	@GetMapping("/getUsersByDesignation/{designationname}")
	public ResponseEntity<?> getUsers(@PathVariable("designationname") String designationname){
		
		 List<Users> userDetailsaccordingToDesignation = userService.getUserDetailsaccordingToDesignation(designationname);
		 
		 return new ResponseEntity<>(userDetailsaccordingToDesignation,HttpStatus.OK);
	}
	
	//Update the user details
	@PutMapping("/updateUsers/{userid}")
	public ResponseEntity<?> updateUsers(@RequestBody Users user, @PathVariable("userid") Long userid){
		
		  Users updateUser = userService.updateUser(user, userid);
		 
		 return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	//Delete the user
	@DeleteMapping("/deleteUser/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable("userid") Long userid){
		
		 userService.deleteUserAccordingToUserId(userid);
		 
		 return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
	}
}
