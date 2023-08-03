package com.uamservice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.uamservice.dto.RoleDto;
import com.uamservice.dto.Status;
import com.uamservice.entity.Role;
import com.uamservice.mapper.RoleMapper;
import com.uamservice.projection.RoleProjection;
import com.uamservice.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleMapper roleMapper;

	@Override
	public Page<RoleProjection> getAllRole(String name, Pageable pageable) {

		Page<RoleProjection> get;
		if (ObjectUtils.isEmpty(name)) {
			get = roleRepository.findBy(pageable);
		} else {
			get = roleRepository.findByRoleIdAndIsDeleted("%" + name + "%", false, pageable);
		}
		return get;
	}

	@Override
	public Status getByRoleId(Long roleId) {
		Status status = new Status();

		Role existingRole = null;

		Optional<Role> findById = roleRepository.findByRoleIdAndIsDeleted(roleId, false);

		if (!findById.isPresent()) {

			status.setMessage("Record not found and ID : " + roleId);

			return status;

		}
		existingRole = findById.get();

		RoleDto entityToDtoGetById = roleMapper.entityToDtoGetById(existingRole);

		status.setData(entityToDtoGetById);
		status.setMessage("Success");

		return status;
	}

	@Override
	public Status saveRole(RoleDto roleDto) {
		Status status = new Status();
		Role role = roleMapper.dtoToEntitySave(roleDto);

		try {
			roleRepository.save(role);
			status.setMessage("Record saved successfully");
		} catch (Exception e) {
			status.setMessage("Failed to save record: " + e.getMessage());
		}

		return status;
	}

	@Override
	public Status updateRoleById(RoleDto roleDto) {

		Status status = new Status();
		Role existingRole = null;

		Optional<Role> findByid = roleRepository.findByRoleIdAndIsDeleted(roleDto.getRoleId(), false);

		if (findByid.isEmpty()) {

			status.setMessage("Record not found and ID : " + roleDto.getRoleId());

			return status;
		}

		existingRole = findByid.get();

		Role dtoToEntityUpdate = roleMapper.dtoToEntityUpdate(roleDto, existingRole);

		roleRepository.save(dtoToEntityUpdate);

		status.setMessage("Record Updated Successfully");

		return status;
	}

	@Override
	public Status softDelete(Long id, String lastUpdatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		Role existingRole = null;
		Status status = new Status();

		Optional<Role> findByRoleId = roleRepository.findById(id);

		if (!findByRoleId.isPresent()) {

			status.setMessage(("Role Details not found and ID : " + id));
			return status;

		}
		existingRole = findByRoleId.get();
		Boolean b = existingRole.getIsDeleted();

		if (Boolean.TRUE.equals(b)) {

			status.setMessage(("Customer Details already Deleted and ID : " + id));
			return status;
		}

		existingRole.setIsDeleted(true);
		existingRole.setLastUpdateBy(lastUpdatedBy);
		existingRole.setEndDate(dateTime);
		roleRepository.save(existingRole);
		status.setMessage("Record Deleted");
		return status;
	}

}
