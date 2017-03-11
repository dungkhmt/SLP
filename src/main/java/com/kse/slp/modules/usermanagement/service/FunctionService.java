package com.kse.slp.modules.usermanagement.service;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.Function;

public interface FunctionService {
	public List<Function> loadFunctionsList();
	public List<Function> loadFunctionsParentHierachyList();
	public List<Function> loadFunctionsChildHierachyList();
}
