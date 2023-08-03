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
public class UserDto {

	private Long userId;

	private String email;

	private String name;

	private Long employeeId;

	private String userName;

	private String password;

	private String createdBy;

	private LocalDateTime creationDate;

	private String lastUpdateBy;

	private LocalDateTime lastUpdateDate;

	private List<RoleDto> roleDto;
	
	
}
