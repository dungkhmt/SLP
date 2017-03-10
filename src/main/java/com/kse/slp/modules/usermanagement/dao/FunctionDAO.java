package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.Function;


public interface FunctionDAO {
	public List<Function> loadFunctionsList();
	public List<Function> loadFunctionsParentHierachyList();
	public List<Function> loadFunctionsChildHierachyList();
	
}
