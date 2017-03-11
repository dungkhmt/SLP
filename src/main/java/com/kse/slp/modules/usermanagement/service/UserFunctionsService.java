package com.kse.slp.modules.usermanagement.service;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.UserFunctions;

public interface UserFunctionsService {
	public List<UserFunctions> loadFunctionsPermissionByUserList(String userName);
	public UserFunctions loadFunctionsPermissionByCodeAndUser(String functionCode,String userName);
	public int saveAFunction(String userName,String functionCode);
	public int removeAFunction(UserFunctions u);
}
