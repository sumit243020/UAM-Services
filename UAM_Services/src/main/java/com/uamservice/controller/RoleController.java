package com.uamservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uamservice.dto.RoleDto;
import com.uamservice.dto.Status;
import com.uamservice.projection.RoleProjection;
import com.uamservice.service.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

	@Autowired
	RoleService roleService;

//get All Roles		
	@GetMapping("/get-all")
	public ResponseEntity<Page<RoleProjection>> getAllRoleDetails(String name, Pageable pageable) {
		Page<RoleProjection> rolePage = roleService.getAllRole(name, pageable);

		System.out.println(rolePage);
		return new ResponseEntity<>(rolePage, HttpStatus.OK);

	}

// get role by id 	
	@GetMapping("/getbyid/{roleId}")
	public ResponseEntity<Status> getRoleById(@PathVariable("roleId") Long roleId) {
		Status status = roleService.getByRoleId(roleId);
		System.out.println(status);
		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}

	// save Role
	@PostMapping("/create")
	public ResponseEntity<Status> saveRole(@RequestBody RoleDto roleDto) {

		Status status = roleService.saveRole(roleDto);
		System.out.println(status);
		return new ResponseEntity<Status>(status, HttpStatus.CREATED);

	}

// update Role by id 	
	@PutMapping("/update")
	public ResponseEntity<Status> updateRole(@RequestBody RoleDto roleDto) {

		Status status = roleService.updateRoleById(roleDto);

		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}

// soft delete role 	
	@DeleteMapping("/delete/{id}/{lastUpdatedBy}")
	public ResponseEntity<Status> deleteRole(@PathVariable("id") Long id, @PathVariable String lastUpdatedBy) {
		Status status = roleService.softDelete(id, lastUpdatedBy);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}