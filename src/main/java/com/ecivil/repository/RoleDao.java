package com.ecivil.repository;

import org.springframework.dao.DataAccessException;

import com.ecivil.model.Role;

public interface RoleDao {
	public Role getRole(int id) throws DataAccessException;
}
