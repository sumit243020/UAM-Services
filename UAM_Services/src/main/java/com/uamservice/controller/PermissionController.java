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

import com.uamservice.dto.PermissionDto;
import com.uamservice.dto.Status;
import com.uamservice.projection.PermissionProjection;
import com.uamservice.service.PermissionService;

@RestController
@RequestMapping("/permission")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService;
	
	//Get All Permission
	@GetMapping("/get-all")
	public ResponseEntity<Page<PermissionProjection>>getAllPermission(String code,Pageable pageable) {
		
		Page<PermissionProjection> permissionPage  = permissionService.getAllPermission(code,pageable);

			return new ResponseEntity<>(permissionPage, HttpStatus.OK);
	}

	// get permission by id 
	@GetMapping("/getbyid/{permissionId}")
	public ResponseEntity<Status> getPermissionById(@PathVariable("permissionId") Long permissionId){

		Status status = permissionService.getPermissionById(permissionId);
		
		System.out.println(status);
		
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	 	
		//Create Permission		
		@PostMapping("/create")
		public ResponseEntity<Status> savePermission(@RequestBody PermissionDto permissionDto){

			Status status = permissionService.savePermission(permissionDto);
			System.out.println(status);
			return new ResponseEntity<Status>(status, HttpStatus.CREATED);
		}
		
		//Update permission 
		@PutMapping("/update")
		public ResponseEntity<Status> updatePermission(@RequestBody PermissionDto permissionDto ) {
			
			Status status = permissionService. updatePermissionById(permissionDto); 
				
			System.out.println(status);
			
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
	
	//soft delete Permission 	
		@DeleteMapping("/delete/{permissionId}/{lastUpdatedBy}")
		public ResponseEntity<Status> softDelete(@PathVariable("permissionId") Long permissionId, @PathVariable("lastUpdatedBy") 
		String lastUpdatedBy)throws Exception{
			
			Status status = permissionService.softDelete(permissionId,lastUpdatedBy);
			return new ResponseEntity<Status>(status, HttpStatus.OK);

		}
		
	

	}
