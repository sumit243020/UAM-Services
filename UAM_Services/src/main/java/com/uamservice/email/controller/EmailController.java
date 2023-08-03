package com.uamservice.email.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uamservice.service.UserServiceImpl;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	  private UserServiceImpl userServiceImpl;
	
	UUID loggerId = UUID.randomUUID();
	
	 @PutMapping("/forgot-password")
	  public ResponseEntity<String>forgotPassword(@RequestParam String email,String loggerId, Model model){
		  
		  return new ResponseEntity<>(userServiceImpl.forgotPassword(email,loggerId,model),HttpStatus.OK);
	  }
	  
	  @PutMapping("/set-password")
	  public ResponseEntity<String> setPassword(@RequestParam String email, @RequestParam String newPassword){
		  
		  
		return new ResponseEntity<>(userServiceImpl.setPassword(email,newPassword),HttpStatus.OK);
		  
	  }

}
