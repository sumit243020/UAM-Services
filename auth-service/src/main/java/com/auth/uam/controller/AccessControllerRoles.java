package com.auth.uam.controller;

import java.util.ArrayList;
import java.util.List;

public class AccessControllerRoles {

	public static final String GETALL = "access-get-all";
	public static final String SAVE = "access-save";
	public static final String UPDATE = "access-update";
	public static final String DELETE = "access-delete";

	
	List<String> get(){
	List<String> list = new ArrayList<>();
	list.add(GETALL);
	list.add(SAVE);
	list.add(UPDATE);
	list.add(DELETE);
	return list;
	}
}
