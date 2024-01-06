package com.jyotirmaya.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jyotirmaya.user.entity.Users;

@Repository
public interface UserServiceRepository extends JpaRepository<Users, Long> {

	public Users findUsersByuserid(Long userid);
	
	public void deleteUsersByuserid(Long userid);
	
	//Native SQL query to fetch users according to designation.
	@Query(value="Select userid, username, userdesignation, useremail, is_deleted from user_detail where is_deleted=false and userdesignation= :designation", 
			nativeQuery=true)
	public List<Users> findUsersBydesignation(@Param("designation")String designation);
	
}
