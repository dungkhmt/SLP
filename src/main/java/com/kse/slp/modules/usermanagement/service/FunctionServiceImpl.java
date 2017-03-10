package com.kse.slp.modules.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.usermanagement.dao.FunctionDAO;
import com.kse.slp.modules.usermanagement.model.Function;
@Service("FunctionService")
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private FunctionDAO functionDAO;
	@Override
	public List<Function> loadFunctionsList() {
		// TODO Auto-generated method stub
		return functionDAO.loadFunctionsList();
	}

	@Override
	public List<Function> loadFunctionsParentHierachyList() {
		// TODO Auto-generated method stub
		return functionDAO.loadFunctionsParentHierachyList();
	}

	@Override
	public List<Function> loadFunctionsChildHierachyList() {
		// TODO Auto-generated method stub
		return functionDAO.loadFunctionsChildHierachyList();
	}

}
