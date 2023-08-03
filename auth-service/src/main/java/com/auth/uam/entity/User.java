package com.auth.uam.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private Long employeeId;

	private String userName;

	private String password;

	private String name;

	private String userType;

	private String email;

	private String createdBy;

	private LocalDateTime creationDate;

	private String lastUpdateBy;

	private LocalDateTime lastUpdateDate;

	private LocalDateTime lastUpdatePasswordDate;

	private Boolean isDeleted;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> role;

}
