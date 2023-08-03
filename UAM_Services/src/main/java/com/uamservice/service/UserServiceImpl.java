package com.uamservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uamservice.dto.EmailBodyDto;
import com.uamservice.dto.Status;
import com.uamservice.dto.UserDto;
import com.uamservice.email.entity.ConfirmationToken;
import com.uamservice.email.service.EmailService;
import com.uamservice.email.utils.EmailUtil;
import com.uamservice.email.utils.OtpUtil;
import com.uamservice.entity.Mail;
import com.uamservice.entity.Permission;
import com.uamservice.entity.Role;
import com.uamservice.entity.User;
import com.uamservice.mapper.UserMapper;
import com.uamservice.projection.UserProjection;
import com.uamservice.repository.ConfirmationTokenRepository;
import com.uamservice.repository.RoleRepository;
import com.uamservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MailService mailService;

	@Value("${baseurl}")
	private String baseUrl;

	@Autowired
	private Environment env;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	EmailService emailService;

	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	static Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public Page<UserProjection> getAllUser(String name, Pageable pageable, String loggerId) {

		Page<UserProjection> get;

		if (ObjectUtils.isEmpty(name)) {
			get = userRepository.findBy(pageable);
			log.info("All user info :{}", get, loggerId);
		} else {
			get = userRepository.findByNameLikeIgnoreCaseAndIsDeleted("%" + name + "%", false, pageable);
			log.info("All user info :{}", get, loggerId);
		}

		return get;
	}

	@Override
	public Status getUserByUserId(Long userId, String loggerId) {
		Status status = new Status();
		User existingUser = null;

		Optional<User> findById = userRepository.findByUserIdAndIsDeleted(userId, false);
		if (!findById.isPresent()) {
			status.setMessage("User not found and ID : " + userId + " " + userId);
			log.error("User not found and ID : {}", userId, loggerId);
			return status;
		}

		existingUser = findById.get();

		List<Role> roleList = existingUser.getRole();

		for (Role role : roleList) {

			String newRole = role.getRoleName();

			String[] roleName = new String[] { newRole };

			Set<String> uniqueRole = new HashSet<>();

			Collections.addAll(uniqueRole, roleName);

			String[] uniqueRoleList = uniqueRole.toArray(new String[0]);

			List<String> newuniqueRoleList = Arrays.asList(uniqueRoleList);

			log.info("Roles assign to User:{}", newuniqueRoleList, loggerId);

			if (role.getPermission() != null) {

				List<Permission> permissionList = setPermission(role.getPermission());

				log.info("Permissions of User:{}", permissionList, loggerId);
			}
		}

		UserDto entityToDtoGetById = userMapper.entityToDtoGetById(existingUser);
		status.setData(entityToDtoGetById);
		status.setMessage("Success");
		log.info("Getting user by Id:", entityToDtoGetById, loggerId);
		return status;
	}

	private List<Permission> setPermission(List<Permission> permission) {

		List<Permission> permissionList = new ArrayList<>();

		for (Permission permission2 : permission) {

			String permissionCode = permission2.getCode();

			String[] permissions = new String[] { permissionCode };

			Set<String> uniquePermission = new HashSet<>();

			Collections.addAll(uniquePermission, permissions);

			String[] uniquePermissionList = uniquePermission.toArray(new String[0]);

			System.out.print(Arrays.toString(uniquePermissionList));
			log.info("permission list of user:{}", Arrays.toString(uniquePermissionList));

		}
		return permissionList;
	}

	@Override
	public Status saveUser(UserDto userDto, String loggerId) {

		Status status = new Status();

		User user = userMapper.dtoTOEntitySave(userDto);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (userRepository.existsByEmail(user.getEmail())) {
			status.setMessage("User with email already exists.");
		}
		userRepository.save(user);
		ConfirmationToken confirmationToken = new ConfirmationToken(user);

		confirmationTokenRepository.save(confirmationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setText("To confirm your account, please click here : "
				+ "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
		emailService.sendEmail(mailMessage);

		System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
		log.info("confirmation token: "+ confirmationToken.getConfirmationToken());

		log.info("user created successfully:{}", user);
		status.setMessage("User Created successfully. Plaease confirm your email: ");

		return status;
	}

	@Override
	public Status updateUserByUserId(UserDto userDto, String loggerId) {

		Status status = new Status();

		User existingVendorDetails = null;

		Optional<User> findById = userRepository.findByUserIdAndIsDeleted(userDto.getUserId(), false);

		if (!findById.isPresent()) {
			status.setMessage("User not found and ID : " + userDto.getUserId());
			log.error("User not found and ID : {}", userDto.getUserId(), loggerId);
			return status;
		}

		existingVendorDetails = findById.get();

		User dtoToEntityUpdate = userMapper.dtoToEntityUpdate(userDto, existingVendorDetails);

		userRepository.save(dtoToEntityUpdate);

		log.info("User information updated successfully:{}", dtoToEntityUpdate, loggerId);
		status.setMessage("User Updated Successfully");

		return status;

	}

//delete
	@Override
	public Status softDelete(Long userId, String loggerId) {

		Status status = new Status();

		Optional<User> validateUser = userRepository.findById(userId);

		if (validateUser.isEmpty()) {
			status.setMessage("User not found : " + userId);
			log.info("User not found for the Id:{}", userId, loggerId);
			return status;

		} else {

			userRepository.softDelete(userId);
			log.info("User deleted successfully for id :{}", userId, loggerId);
			status.setMessage("user deleted");
			return status;
		}

	}

//forgot Password
	public String forgotPassword(String email,String loggerId,Model model) {
		Status status = new Status();
		User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found for given email: " + email));

		status.setMessage("User Not found for given email:");
				log.error("User Not found for given email:{}");
		try {
			emailUtil.sendPasswordEmail(email,loggerId);
			return "Please check your email to set new Password";
		} catch (MessagingException e) {

			throw new RuntimeException("Unable to send set password Email: ");
		}
		
	}

// set new  password 
	public String setPassword(String email, String newPassword) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		user.setPassword(newPassword);
		userRepository.save(user);
		return "New Password Set successfully login with new password ";
	}

	// email confirmation
	@Override
	public ResponseEntity<?> confirmEmail(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
			user.setIsDeleted(false);
			userRepository.save(user);
			return ResponseEntity.ok("Email verified successfully!");
		}
		return ResponseEntity.badRequest().body("Error: Couldn't verify email");
	}
}