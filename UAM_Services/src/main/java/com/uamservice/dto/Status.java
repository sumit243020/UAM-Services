package com.uamservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

	private int code;
	private String message;
	private Object data;
	private Long recordCount;
	private Long totalCount;
	private Long allRecordsCount;
	private String fileName;

}