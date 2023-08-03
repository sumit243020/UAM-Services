package com.uamservice.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class PermissionDto {

	private Long permissionId;

	private String code;

	private String status;

	private String createdBy;

	private String lastUpdatedBy;

}
