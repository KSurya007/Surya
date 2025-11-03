package com.example.testproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {
	private String messege;

	public UserNotFound(String messege) {
		super(messege);
		this.messege = messege;
	}
}
