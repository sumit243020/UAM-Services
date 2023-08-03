package com.uamservice.controller;



import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uamservice.dto.Status;
import com.uamservice.dto.UserDto;
import com.uamservice.projection.UserProjection;
import com.uamservice.service.UserService;
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;
	
	static Logger log =LogManager.getLogger(UserController.class);
	UUID loggerId = UUID.randomUUID();

	@GetMapping("get-all")
	public ResponseEntity<Page<UserProjection>> getAllUser(String name, Pageable pageable,String loggerId) {

		Page<UserProjection> userPage = userService.getAllUser(name, pageable,loggerId);
		log.info("Users List {}",userPage,loggerId);

		return new ResponseEntity<>(userPage, HttpStatus.OK);

	}

	@GetMapping("/getbyid/{userId}")
	public ResponseEntity<Status> getUser(@PathVariable("userId") Long userId,String loggerId) {

		Status status = userService.getUserByUserId(userId,loggerId);
		log.info("find user by Id: {} ",status,loggerId);
		
		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}

	@PostMapping("/create")
	public ResponseEntity<Status> saveUser(@RequestBody UserDto userDto,String loggerId) {

		Status status = userService.saveUser(userDto,loggerId);
		log.info("create user {}",status,loggerId);
		return new ResponseEntity<Status>(status, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<Status> updateUser(@RequestBody UserDto userDto,String loggerId ) {

		Status status = userService.updateUserByUserId(userDto,loggerId);
		log.info("update user info: {}",status,userDto);
		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}

	@RequestMapping("/delete/{userId}")
	public ResponseEntity<Status> softdeleteUser(@PathVariable("userId") Long userId,String loggerId) throws Exception {

		Status status = userService.softDelete(userId,loggerId);
		log.info("delete user info :{}",status,loggerId);
		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}
	
	 @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
	        return userService.confirmEmail(confirmationToken);
	    }

}