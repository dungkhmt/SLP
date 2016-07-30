package com.kse.slp.modules.usermanagement.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.usermanagement.dao.UserDAO;
import com.kse.slp.modules.usermanagement.model.Role;
import com.kse.slp.modules.usermanagement.model.User;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}	
	
	public List<User> getList(){
		return userDAO.getList();
	}
	public User getByID(int id){
		return userDAO.getByID(id);
	}
	public User getByUsernameAndPassword(String username, String password){
		return userDAO.getByUsernameAndPassword(username, password);
	}
	public User getByUsername(String username){
		return userDAO.getByUsername(username);
	}
	public int save(String username, String password, HashSet<Role> roles){
		return userDAO.save(new User(username, password, roles));
	}
	public int delete(int id){
		User user = userDAO.getByID(id);
		if(user != null)
			return userDAO.delete(user);
		return 0;
	}

	
	

}
