package com.jyotirmaya.user.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.jyotirmaya.user.entity.Users;
import com.jyotirmaya.user.exception.UserNotfoundException;
import com.jyotirmaya.user.repo.UserServiceRepository;

@Service
public class UserService  {

	
	@Autowired
	public UserServiceRepository userrepo;
	
	//Save user details to database
	public Users saveUser(Users user) {
		return userrepo.save(user);
	}
	
	//Save multiple users details to database
	public List<Users> saveAllUser(List<Users> user) {
		return userrepo.saveAll(user);
	}
	
	//Get all user details from database
	public List<Users> getUsers() {
		return userrepo.findAll();
	}
	
	//Get user details from database
	public Users getUsersById(Long userid) {
		return userrepo.findUsersByuserid(userid);
	}
	
	//Update user details to database
	public Users updateUser(Users user, Long userid) {
		
		Users findUsersByuserid = userrepo.findUsersByuserid(userid);
		
		if(findUsersByuserid == null) {
			throw new UserNotfoundException("User Not found");
		}
		
		else {
			findUsersByuserid.setUsername(user.getUsername());
			findUsersByuserid.setUseremail(user.getUseremail());
			findUsersByuserid.setUserdesignation(user.getUserdesignation());
			
			return userrepo.save(findUsersByuserid);
		}
	}
	
	//Get user details according to designation
	public List<Users> getUserDetailsaccordingToDesignation(String designation){
		return userrepo.findUsersBydesignation(designation);
	}

		//Delete users according to userid
		@Transactional
		public void deleteUserAccordingToUserId(Long userid){
			 userrepo.deleteUsersByuserid(userid);
		}

}
