package com.uamservice.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uamservice.dto.PermissionDto;
import com.uamservice.dto.RoleDto;
import com.uamservice.entity.Permission;
import com.uamservice.entity.Role;

@Service
public class RoleMapper {
	public RoleDto entityToDtoGetById(Role role) {

		RoleDto roleDto = new RoleDto();
//	    LocalDateTime dateTime = LocalDateTime.now();

		roleDto.setRoleId(role.getRoleId());
		roleDto.setRoleName(role.getRoleName());

		// permission get

		List<PermissionDto> setList = setList1(role.getPermission());

		roleDto.setPermissionDto(setList);

		return roleDto;
	}

	public List<PermissionDto> setList1(List<Permission> permission) {

		List<PermissionDto> permissionDtoList = new ArrayList<PermissionDto>();

		for (Permission permission1 : permission) {

			PermissionDto newpermissionDto = new PermissionDto();

			newpermissionDto.setPermissionId(permission1.getPermissionId());
			newpermissionDto.setCode(permission1.getCode());

			permissionDtoList.add(newpermissionDto);
		}

		return permissionDtoList;
	}

	public Role dtoToEntityUpdate(RoleDto roleDto, Role existingRole) {

		LocalDateTime dateTime = LocalDateTime.now();

		existingRole.setRoleId(roleDto.getRoleId());
		existingRole.setRoleName(roleDto.getRoleName());
		existingRole.setLastUpdateBy(roleDto.getLastUpdateBy());
		existingRole.setLastUpdateDate(dateTime);
		List<Permission> permissionList = setPermissionList(roleDto.getPermissionDto());
		existingRole.setPermission(permissionList);
		return existingRole;
	}

	private List<Permission> setPermissionList(List<PermissionDto> permissionDto) {
		List<Permission> permissionList = new ArrayList<Permission>();

		for (PermissionDto permissionDto2 : permissionDto) {

			Permission newPermission = new Permission();

			newPermission.setPermissionId(permissionDto2.getPermissionId());

			permissionList.add(newPermission);
		}

		return permissionList;
	}

	public Role dtoToEntitySave(RoleDto roleDto) {

		LocalDateTime dateTime = LocalDateTime.now();

		Role role = new Role();

		role.setRoleId(roleDto.getRoleId());
		role.setRoleName(roleDto.getRoleName());
		role.setCreatedBy(roleDto.getCreatedBy());
		role.setCreationDate(dateTime);
		role.setStartDate(dateTime);
		role.setIsDeleted(false);
		role.setLastUpdateDate(dateTime);
		role.setLastUpdateBy(roleDto.getLastUpdateBy());

//	method call
		List<Permission> setList = setList(roleDto.getPermissionDto());

		role.setPermission(setList);

		return role;
	}

	public List<Permission> setList(List<PermissionDto> permissionDto) {

		List<Permission> permissionList = new ArrayList<Permission>();

		for (PermissionDto permissionDto2 : permissionDto) {

			Permission newPermission = new Permission();

			newPermission.setPermissionId(permissionDto2.getPermissionId());

			permissionList.add(newPermission);
		}

		return permissionList;

	}

}