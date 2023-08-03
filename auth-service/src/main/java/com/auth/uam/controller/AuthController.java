package com.auth.uam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.uam.dto.AuthRequest;
import com.auth.uam.dto.AuthToken;
import com.auth.uam.dto.Status;
import com.auth.uam.entity.User;
import com.auth.uam.security.service.JwtService;
import com.auth.uam.security.service.UserService;
import com.auth.uam.service.LogInService;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	/* validate user for token */
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	LogInService logInService;
	
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping("save-user")
	public ResponseEntity<User> save(@RequestBody User user) {

		User savedUser = userService.create(user);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

	

	@PostMapping("accessToken")
	public ResponseEntity<?> authonticatAndGetToken(@RequestBody AuthRequest authRequest) {

		/* validate user for token */
		try {
			
		
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		if (authenticate.isAuthenticated()) {
			/* just an entity that can be authenticated */
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			AuthToken authToken = logInService.getToken(authRequest);
			return new ResponseEntity<>(authToken, HttpStatus.OK);
		} else {

			throw new UsernameNotFoundException("Invalid User Request!");
		}
		} catch (Exception e) {
			Status status = new Status();
			status.setMessage(e.getMessage());
			return new ResponseEntity<>(status, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@PostMapping("validate-token")
	public ResponseEntity<String> validateToken(@RequestParam String token) {

		jwtService.tokenValidate(token);
	
		return new ResponseEntity<>("token validate", HttpStatus.OK);
	}
}
