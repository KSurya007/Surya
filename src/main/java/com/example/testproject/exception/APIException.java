package com.example.testproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class APIException extends RuntimeException {
	private String messege;

	public APIException(String messege) {
		super(messege);
		this.messege = messege;
	}
}
