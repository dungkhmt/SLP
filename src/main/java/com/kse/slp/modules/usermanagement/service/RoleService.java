package com.kse.slp.modules.usermanagement.service;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.Role;


public interface RoleService {
	
	public List<Role> getList();
	public Role getByName(String name);

}
