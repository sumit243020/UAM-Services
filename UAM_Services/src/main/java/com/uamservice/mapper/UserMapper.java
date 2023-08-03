package com.uamservice.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uamservice.dto.PermissionDto;
import com.uamservice.dto.RoleDto;
import com.uamservice.dto.UserDto;
import com.uamservice.entity.Permission;
import com.uamservice.entity.Role;
import com.uamservice.entity.User;

@Service
public class UserMapper {
	public UserDto entityToDtoGetById(User user) {

		UserDto userDTO = new UserDto();

		userDTO.setUserId(user.getUserId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setUserName(user.getUserName());
		userDTO.setEmployeeId(user.getEmployeeId());

		if (user.getRole() != null) {
			List<RoleDto> setList = setRole(user.getRole());
			userDTO.setRoleDto(setList);
		}

		return userDTO;

	}

	private List<RoleDto> setRole(List<Role> role) {

		List<RoleDto> roleDtoList = new ArrayList<RoleDto>();

		for (Role role1 : role) {

			RoleDto newroleDto = new RoleDto();

			newroleDto.setRoleId(role1.getRoleId());
			newroleDto.setRoleName(role1.getRoleName());

			if (role1.getPermission() != null) {
				List<PermissionDto> permissionDto = setPermission(role1.getPermission());

				newroleDto.setPermissionDto(permissionDto);

			}
			roleDtoList.add(newroleDto);

		}

		return roleDtoList;
	}

	private List<PermissionDto> setPermission(List<Permission> permission) {

		List<PermissionDto> permissionDtoList = new ArrayList<PermissionDto>();
		for (Permission permission2 : permission) {
			PermissionDto newPermissionDto = new PermissionDto();

			newPermissionDto.setPermissionId(permission2.getPermissionId());
			newPermissionDto.setCode(permission2.getCode());

			permissionDtoList.add(newPermissionDto);

		}
		return permissionDtoList;
	}

	public User dtoToEntityUpdate(UserDto userDto, User existingUser) {

		LocalDateTime dateTime = LocalDateTime.now();
		existingUser.setUserId(userDto.getUserId());
		existingUser.setName(userDto.getName());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setPassword(userDto.getPassword());
		existingUser.setUserName(userDto.getUserName());
		existingUser.setEmployeeId(userDto.getEmployeeId());
		existingUser.setLastUpdateDate(dateTime);
		existingUser.setLastUpdateBy(userDto.getLastUpdateBy());

		List<Role> updateRoleList = updateRoleList(existingUser.getRole());

		existingUser.setRole(updateRoleList);
		return existingUser;

	}

	private List<Role> updateRoleList(List<Role> role) {

		LocalDateTime dateTime = LocalDateTime.now();
		
		List<Role> roleList = new ArrayList<>();

		for (Role role2 : role) {

			Role newRole = new Role();

			newRole.setRoleId(role2.getRoleId());
			newRole.setRoleName(role2.getRoleName());
			newRole.setCreatedBy(role2.getCreatedBy());
			newRole.setCreationDate(dateTime);
			newRole.setStartDate(dateTime);
			newRole.setIsDeleted(false);
			newRole.setLastUpdateDate(dateTime);
			newRole.setLastUpdateBy(role2.getLastUpdateBy());

		}
		return roleList;
	}

	public User dtoTOEntitySave(UserDto userDto) {

		LocalDateTime dateTime = LocalDateTime.now();

		User user = new User();

		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setUserName(userDto.getUserName());
		user.setEmployeeId(userDto.getEmployeeId());
		user.setCreationDate(dateTime);
		user.setCreatedBy(userDto.getCreatedBy());
		user.setCreationDate(dateTime);
		user.setIsDeleted(false);

		List<Role> setList = setList(userDto.getRoleDto());

		user.setRole(setList);

		return user;
	}

	public List<Role> setList(List<RoleDto> roleDto) {

		List<Role> roleList = new ArrayList<Role>();

		for (RoleDto roleListDto : roleDto) {

			Role newRole = new Role();

			newRole.setRoleId(roleListDto.getRoleId());

			roleList.add(newRole);
		}

		return roleList;

	}
}