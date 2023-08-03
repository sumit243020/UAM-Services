package com.uamservice.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uamservice.entity.Role;
import com.uamservice.projection.RoleProjection;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Page<RoleProjection> findBy(Pageable pageable);

	Optional<Role> findByRoleIdAndIsDeleted(Long roleId, boolean b);

	Page<RoleProjection> findByRoleIdAndIsDeleted(String string, boolean b, Pageable pageable);

}
