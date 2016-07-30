package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.Role;


public interface RoleDAO {
	
	public List<Role> getList();
	public Role getByName(String name);
	
}
