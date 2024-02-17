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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyotirmaya.user.entity.Users;
import com.jyotirmaya.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/user/")
public class UserServiceController {

	@Autowired
	public UserService userService;
	
	//Save user
	@Operation(summary="save user information",responses= {@ApiResponse(responseCode="201", description="save user information",
			content= { @Content(mediaType="application/json") })})
	@PostMapping("/saveUsers")
	public ResponseEntity<?> saveUsers(@RequestBody Users user ){
		
		 Users saveUser = userService.saveUser(user);
		 
		 return new ResponseEntity<>(saveUser,HttpStatus.CREATED);
	}
	
	@Operation(summary="save multiple user information",responses= {@ApiResponse(responseCode="201", description="save multiple user information",
			content= { @Content(mediaType="application/json") })})
	@PostMapping("/saveMutipleUsers")
	public ResponseEntity<?> saveMultipleUsers(@RequestBody List<Users> user ){
		
		List<Users> saveAllUser = userService.saveAllUser(user);
		 
		 return new ResponseEntity<>(saveAllUser,HttpStatus.CREATED);
	}
	
	//Get all the user details 
	@Operation(summary="get all active users information",responses= {@ApiResponse(responseCode="200", description="get all active users information",
			content= { @Content(mediaType="application/json") })})
	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers(){
		
		 List<Users> userDetails = userService.getUsers();
		 
		 return new ResponseEntity<>(userDetails,HttpStatus.OK);
	}
	
	//Get the user details by user id
	@Operation(summary="get active user's information according to userid",responses= {@ApiResponse(responseCode="200", description="get active user's information according to userid",
			content= { @Content(mediaType="application/json") })})
	@GetMapping("/getUsers/{userid}")
	public ResponseEntity<?> getUsers(@PathVariable("userid") Long userid){
		
		  Users usersById = userService.getUsersById(userid);
		 
		 return new ResponseEntity<>(usersById,HttpStatus.OK);
	}
	
	//Get the user details by designation
	@Operation(summary="get active user's information according to designationname",responses= {@ApiResponse(responseCode="200", description="get active user's information according to designationname",
			content= { @Content(mediaType="application/json") })})
	@GetMapping("/getUsersByDesignation/{designationname}")
	public ResponseEntity<?> getUsers(@PathVariable("designationname") String designationname){
		
		 List<Users> userDetailsaccordingToDesignation = userService.getUserDetailsaccordingToDesignation(designationname);
		 
		 return new ResponseEntity<>(userDetailsaccordingToDesignation,HttpStatus.OK);
	}
	
	//Update the user details
	@Operation(summary="update user's information according to userid",responses= {@ApiResponse(responseCode="200", description="update user's information according to userid",
			content= { @Content(mediaType="application/json") })})
	@PutMapping("/updateUsers/{userid}")
	public ResponseEntity<?> updateUsers(@RequestBody Users user, @PathVariable("userid") Long userid){
		
		  Users updateUser = userService.updateUser(user, userid);
		 
		 return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	//Delete the user
	@Operation(summary="delete user according to userid",responses= {@ApiResponse(responseCode="200", description="delete user according to userid",
			content= { @Content(mediaType="application/json") })})
	@DeleteMapping("/deleteUser/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable("userid") Long userid){
		
		 userService.deleteUserAccordingToUserId(userid);
		 
		 return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
	}
	
	//Find all users according to pageNumber, size & sorting
	@Operation(summary="get users by paageNo, pageSize & sorting",responses= {@ApiResponse(responseCode="200", description="get users by paageNo, pageSize & sorting",
			content= { @Content(mediaType="application/json") })})
	@GetMapping("/getUsersByPagination")
	public ResponseEntity<?> findAllUsersAccordingToPagination(@RequestParam("pageNo") String pageNo, @RequestParam("pageSize") String pageSize,
																	@RequestParam("sortBy") String sortBy){
		
		List<Users> allUsersAccordingToPagination = userService.getAllUsersAccordingToPagination(pageNo,pageSize,sortBy);
		
		return new ResponseEntity<>(allUsersAccordingToPagination,HttpStatus.OK);
		
	}
	
}
