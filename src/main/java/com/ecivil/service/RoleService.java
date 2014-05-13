package com.ecivil.service;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.Role;

public interface RoleService {
	public Role getRole(String name) throws DataAccessException;
	
	public Role getDefaultRole() throws DataAccessException;  
}
