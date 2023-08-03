package com.uamservice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uamservice.dto.PermissionDto;
import com.uamservice.dto.Status;
import com.uamservice.projection.PermissionProjection;



public interface PermissionService {

	Page<PermissionProjection> getAllPermission(String code,Pageable pageable);

	Status getPermissionById(Long permissionId);

	Status savePermission(PermissionDto permissionDto);

	Status updatePermissionById(PermissionDto permissionDto);

	Status softDelete(Long permissionId, String lastUpdatedBy) throws Exception;

}
