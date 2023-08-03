package com.uamservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.uamservice.dto.Status;
import com.uamservice.dto.UserDto;
import com.uamservice.entity.User;
import com.uamservice.projection.UserProjection;

public interface UserService {

	Page<UserProjection> getAllUser(String name, Pageable pageable, String loggerId);

	Status getUserByUserId(Long userId, String loggerId);

	Status saveUser(UserDto userDto, String loggerId);

	Status updateUserByUserId(UserDto userDto, String loggerId);

	Status softDelete(Long userId, String loggerId);

	ResponseEntity<?> confirmEmail(String confirmationToken);
}
