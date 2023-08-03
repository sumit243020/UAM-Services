package com.uamservice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.uamservice.dto.PermissionDto;
import com.uamservice.dto.Status;
import com.uamservice.entity.Permission;
import com.uamservice.mapper.PermissionMapper;
import com.uamservice.projection.PermissionProjection;
import com.uamservice.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionRepository permissionRepository;
	
	
	@Autowired
	PermissionMapper permissionMapper;

	
	
	@Override
	public Page<PermissionProjection> getAllPermission(String code,Pageable pageable) {
		
		Page<PermissionProjection> get;
		if (ObjectUtils.isEmpty(code)){
			get = permissionRepository.findBy(pageable);
		} else {
			get = permissionRepository.findByCodeAndIsDeleted("%" + code + "%", false, pageable);
		}
		return get;
	}

	@Override
	public Status getPermissionById(Long PermissionId) {
		
		Status status = new Status();

		Permission existingPermission = null;
		Optional<Permission> findById = permissionRepository.findByPermissionIdAndIsDeleted(PermissionId, false);
		
		if(!findById.isPresent()) {
			status.setMessage("Permission not found and Id: "+PermissionId);
			return status;
		}

		existingPermission = findById.get();
		PermissionDto 	entityToDtoGetById = permissionMapper.entityToDtoGetById(existingPermission);
		
		status.setData(entityToDtoGetById);
		status.setMessage("Succes");
		return status;
}
	
	@Override
	public Status savePermission(PermissionDto permissionDto) {
		
		Status status = new Status();
		try {
		Permission permission = permissionMapper.dtoToEntitySave(permissionDto);
		
		permissionRepository.save(permission);
		status.setMessage("Permission saved Successfully");
		}catch (Exception e) {
			
			status.setMessage("Error creating Permission: " + e.getMessage());
		}

		return status;
	}
	
	
	@Override
	public Status updatePermissionById(PermissionDto permissionDto) {
		
		Status status = new Status();
		
		Permission existingPermission = null;
			
		Optional<Permission> findById = permissionRepository.findByPermissionIdAndIsDeleted(permissionDto.getPermissionId(),false);
		
		if(findById.isEmpty()) {
			status.setMessage("Permission not found and ID : "+permissionDto.getPermissionId());
			return status;
		}
		
		existingPermission = findById.get();
		
		Permission dtoToEntityUpdate = permissionMapper.dtoToEntityUpdate(permissionDto, existingPermission);
		
		permissionRepository.save(dtoToEntityUpdate);
		status.setMessage("Permission Updated Successfully");
		
		return status;
	}

	@Override
	public Status softDelete(Long permissionId, String lastUpdatedBy) throws Exception{
		
		LocalDateTime dateTime = LocalDateTime.now();
		Permission existingPermission = null;
		Status status = new Status();
		
		Optional<Permission> findByPermissionId = permissionRepository.findById(permissionId);
		
		if(findByPermissionId.isEmpty()) {
			status.setMessage("Permission details not found and Id: " +permissionId);
			return status;
			
		}
		existingPermission = findByPermissionId.get();	
		Boolean b = existingPermission.getIsDeleted();
		
		if(Boolean.TRUE.equals(b)){
			 status.setMessage("Permission is alreday inactive and id: "+permissionId);
			 return status;
			}
		
		existingPermission.setIsDeleted(true);
		existingPermission.setLastUpdatedBy(lastUpdatedBy);
//		existingPermission.setEndDate(dateTime);
		permissionRepository.save(existingPermission);
		status.setMessage("Permission Deleted");

		return status;

	}
		
}

	

