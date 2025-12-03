package com.example.testproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testproject.payload.JwtAuthResponse;
import com.example.testproject.payload.LoginDto;
import com.example.testproject.payload.UserDto;
import com.example.testproject.security.JwtTokenProvider;
import com.example.testproject.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		String token = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthResponse(token));
	}

}
