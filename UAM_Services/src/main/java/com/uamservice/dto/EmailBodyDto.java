package com.uamservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class EmailBodyDto {

	String name;
	String username;
	String password;
	String url;

}
