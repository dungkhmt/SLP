package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.UserFunctions;

public interface UserFunctionsDAO {
	public List<UserFunctions> loadFunctionsPermissionByUserList(String userName);
	public UserFunctions loadFunctionsPermissionByCodeAndUser(String functionCode,String userName);
	public int saveAFunction(UserFunctions u);
	public int removeAFunction(UserFunctions u);

}
