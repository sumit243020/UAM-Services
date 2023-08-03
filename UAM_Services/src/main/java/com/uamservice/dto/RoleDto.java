package com.uamservice.dto;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class RoleDto {

	private Long roleId;

	private String roleName;

	private String isDefault;

	private String status;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private String createdBy;

	private LocalDateTime creationDate;

	private String lastUpdateBy;

	private Boolean isDeleted;

	private LocalDateTime lastUpdateDate;

	private List<PermissionDto> permissionDto;

	
}
