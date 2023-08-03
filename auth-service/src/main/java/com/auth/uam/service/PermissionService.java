package com.auth.uam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.uam.entity.Permission;
import com.auth.uam.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	PermissionRepository permissionRepository;

	

	public Optional<Permission> getById(Long id) {

		return permissionRepository.findById(id);
	}

	public Permission savePermission(Permission permission) {

		return permissionRepository.save(permission);
	}

	public List<Permission> getList() {
		return permissionRepository.findAll();
	}

	
}
