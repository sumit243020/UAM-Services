package com.uamservice.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uamservice.entity.Permission;
import com.uamservice.projection.PermissionProjection;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Page<PermissionProjection> findBy(Pageable pageable);

	Page<PermissionProjection> findByCodeAndIsDeleted(String string, boolean b, Pageable pageable);

	Optional<Permission> findByPermissionIdAndIsDeleted(Long permissionId, boolean b);

}	