package com.auth.uam.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.uam.dto.AuthRequest;
import com.auth.uam.dto.AuthToken;
import com.auth.uam.entity.User;
import com.auth.uam.repository.UserRepository;
import com.auth.uam.security.service.JwtService;
import com.auth.uam.security.service.UserService;
import com.auth.uam.utils.SendMails;

@Service
public class LogInService {
	
	Logger logger = Logger.getLogger(LogInService.class.getName());
	
//	resetPasswordTokenMiniDiff
	
//	@Value("${resetPasswordTokenMiniDiff}")
//	private long resetPasswordTokenMiniDiff;
//	
//	@Value("${resetPasswordTokenUrl}")
//	private String resetPasswordTokenUrl;

	@Autowired
	JwtService jwtService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	
	
	@Autowired
	SendMails sendMails;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthToken getToken(AuthRequest authRequest) {
		Optional<User> findByUserName = userRepository.findByUserNameAndIsDeleted(authRequest.getUserName(), false);
		User user = findByUserName.get();

		AuthToken token = new AuthToken();
		token.setUserId(user.getUserId());
		token.setUserName(user.getUserName());
		token.setName(user.getName());
		token.setEmail(user.getEmail());
		String genrateToken = jwtService.genrateToken(authRequest.getUserName());
		token.setAccessToken(genrateToken);

		return token;
	}


	
}
