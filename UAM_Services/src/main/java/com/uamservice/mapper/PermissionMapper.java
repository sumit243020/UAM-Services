package com.uamservice
.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.uamservice.dto.PermissionDto;
import com.uamservice.entity.Permission;

@Service
public class PermissionMapper {
	public PermissionDto entityToDtoGetById(Permission permission) {
		
		PermissionDto permissionDto = new PermissionDto();


		permissionDto.setPermissionId(permission.getPermissionId());
		permissionDto.setCode(permission.getCode());
		
		return permissionDto;
	}
	
public Permission dtoToEntityUpdate(PermissionDto permissionDto, Permission existingPermission) {
		
		LocalDateTime dateTime =  LocalDateTime.now();
		
		existingPermission.setPermissionId(permissionDto.getPermissionId());
		existingPermission.setCode(permissionDto.getCode());
//		existingPermission.setLastUpdatedBy(permissionDto.getLastUpdatedBy());
		existingPermission.setIsDeleted(false);
		existingPermission.setLastUpdatedDate(dateTime);
		
			return existingPermission;
			
	}

public Permission dtoToEntitySave(PermissionDto permissionDto) {
	
	LocalDateTime dateTime =  LocalDateTime.now();
	
	Permission permission = new Permission();
	
	permission.setPermissionId(permissionDto.getPermissionId());
	permission.setCode(permissionDto.getCode());
	permission.setStatus(permissionDto.getStatus());
	permission.setCreationDate(dateTime);
	permission.setCreatedBy(permissionDto.getCreatedBy());
	permission.setLastUpdatedBy(permissionDto.getLastUpdatedBy());
	permission.setLastUpdatedDate(dateTime);
	permission.setStatus(permissionDto.getStatus());

	permission.setIsDeleted(false);
	
	return permission;
	
}



}