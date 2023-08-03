package com.uamservice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uamservice.dto.RoleDto;
import com.uamservice.dto.Status;
import com.uamservice.projection.RoleProjection;

//import java.util.List;


public interface RoleService {

	Page<RoleProjection> getAllRole(String name,Pageable pageable);

	Status getByRoleId(Long roleId);

	Status saveRole(RoleDto roleDto);

	Status updateRoleById(RoleDto roleDto);

	Status softDelete(Long id, String lastUpdatedBy);

}
