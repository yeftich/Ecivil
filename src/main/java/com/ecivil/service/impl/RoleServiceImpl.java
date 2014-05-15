package com.ecivil.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.Role;
import com.ecivil.repository.RoleDao;
import com.ecivil.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDAO;

	@Override
	@Transactional(readOnly = true)
	public Role getRole(String name) {
		return roleDAO.getRole(name);
	}




}