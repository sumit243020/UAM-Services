package  com.auth.uam.entity;

import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.Column;
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
@Table(name = "ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	private String roleName;

	private String roleKey;

	private String isDefault;

	private String status;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private String createdBy;

	private LocalDateTime creationDate;

	private Long lastUpdateLogin;

	private String lastUpdateBy;

	private Boolean isDeleted;

	private LocalDateTime lastUpdateDate;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Permission> permission;


}
