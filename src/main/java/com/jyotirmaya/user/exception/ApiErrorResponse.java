package com.jyotirmaya.user.exception;

import java.util.Date;

public class ApiErrorResponse {

	private Integer status;
	private String message;
	private Date date;
	
	public ApiErrorResponse(Integer status, String message, Date date) {
		super();
		this.status = status;
		this.message = message;
		this.date = date;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}