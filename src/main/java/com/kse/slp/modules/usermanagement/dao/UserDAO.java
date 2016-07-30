package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.User;


public interface UserDAO {
	
	public List<User> getList();
	public User getByID(int id);
	public User getByUsernameAndPassword(String username, String password);
	public User getByUsername(String username);
	public int save(User user);
	public int delete(User user);
	
	
}
