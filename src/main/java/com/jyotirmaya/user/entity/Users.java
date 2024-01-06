package com.jyotirmaya.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="user_detail")
@SQLDelete(sql="Update user_detail set IS_DELETED =true where userid=?")
@Where(clause="IS_DELETED=false")
public class Users {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userid;
	private String username;
	private String useremail;
	private String userdesignation;
	private boolean isDeleted= false;
	
	public Users()
	{
		
	}
	
	public Users(Long userid, String username, String useremail, String userdesignation, boolean isDeleted) {
		super();
		this.userid = userid;
		this.username = username;
		this.useremail = useremail;
		this.userdesignation = userdesignation;
		this.isDeleted = isDeleted;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserdesignation() {
		return userdesignation;
	}
	public void setUserdesignation(String userdesignation) {
		this.userdesignation = userdesignation;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", useremail=" + useremail + ", userdesignation="
				+ userdesignation + ", isDeleted=" + isDeleted + "]";
	}
	
	
	
	
	
}
