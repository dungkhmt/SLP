package com.kse.slp.modules.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.usermanagement.dao.FunctionDAO;
import com.kse.slp.modules.usermanagement.dao.UserFunctionsDAO;
import com.kse.slp.modules.usermanagement.model.UserFunctions;
@Service("UserFunctionsService")
public class UserFunctionsServiceImpl implements UserFunctionsService {
	@Autowired
	private FunctionDAO functionDAO;
	@Autowired 
	private UserFunctionsDAO userFunctionDAO;
	@Override
	public List<UserFunctions> loadFunctionsPermissionByUserList(String userName) {
		// TODO Auto-generated method stub
		return userFunctionDAO.loadFunctionsPermissionByUserList(userName);
	}
	@Override
	public UserFunctions loadFunctionsPermissionByCodeAndUser(
			String functionCode, String userName) {
		// TODO Auto-generated method stub
		return userFunctionDAO.loadFunctionsPermissionByCodeAndUser(functionCode, userName);
	}
	@Override
	public int saveAFunction(String userName,String functionCode) {
		if(!userName.equals("")&& !functionCode.equals("")){
			UserFunctions uf=new UserFunctions();
			String u=functionCode+"_"+userName;
			uf.setUSERFUNC_Code(u);
			uf.setUSERFUNC_FuncCode(functionCode);
			uf.setUSERFUNC_UserCode(userName);
			return userFunctionDAO.saveAFunction(uf);
		}
		return 0;
	}
	@Override
	public int removeAFunction(UserFunctions u) {
		// TODO Auto-generated method stub
		return userFunctionDAO.removeAFunction(u);
	}
	
	
}
