package com.auth.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.uam.entity.Permission;

public interface PermissionRepository   extends JpaRepository<Permission, Long> {



}
