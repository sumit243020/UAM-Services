package com.auth.uam.controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.uam.enums.RolesKeyEnums;

;

@RestController
@RequestMapping("access")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccessAPI {

	
	
	@GetMapping("all")
	@PreAuthorize("hasAnyAuthority(@securityService.getPrivilege(#roleKey))")
	public ResponseEntity<String> all(@Value(AccessControllerRoles.GETALL) String roleKey) {

		return new ResponseEntity<>("get all API", HttpStatus.OK);
	}

	@PostMapping("save")
	@PreAuthorize("hasAnyAuthority(@securityService.getPrivilege(#roleKey))")
	public ResponseEntity<String> post(@Value(AccessControllerRoles.SAVE) String roleKey) {

		return new ResponseEntity<>("save post API", HttpStatus.OK);
	}
	
	@PutMapping("update")
	@PreAuthorize("hasAnyAuthority(@securityService.getPrivilege(#roleKey))")
	public ResponseEntity<String> put(@Value(AccessControllerRoles.UPDATE) String roleKey) {

		return new ResponseEntity<>("update put API", HttpStatus.OK);
	}
	
	@DeleteMapping("delete")
	@PreAuthorize("hasAnyAuthority(@securityService.getPrivilege(#roleKey))")
	public ResponseEntity<String> delete(@Value(AccessControllerRoles.DELETE) String roleKey) {

		return new ResponseEntity<>("delete  API", HttpStatus.OK);
	}

	@GetMapping("get-role-key")
//	public ResponseEntity<List<String>> getRoleKey() {
	public ResponseEntity<List<RolesKeyEnums>> getRoleKey() {
		
		
//		RolesKeyEnums enumValue = null ;
//		Object[] possibleValues = RolesKeyEnums.getDeclaringClass().getEnumConstants();
//		System.out.println(possibleValues);
		
		List<RolesKeyEnums> enumValues = Arrays.asList(RolesKeyEnums.values());
		List<String> list = new ArrayList<>();
//		list.add(GETALL);
//		list.add(SAVE);
//		list.add(UPDATE);
//		list.add(DELETE);
		return new ResponseEntity<>(enumValues, HttpStatus.OK);
		
	}
}
