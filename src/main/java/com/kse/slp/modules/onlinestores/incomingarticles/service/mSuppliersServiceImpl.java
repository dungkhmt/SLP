package com.kse.slp.modules.onlinestores.incomingarticles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.incomingarticles.dao.mSuppliersDAO;
import com.kse.slp.modules.onlinestores.incomingarticles.model.mSuppliers;

@Service("mSuppliersService")
public class mSuppliersServiceImpl implements mSuppliersService{

	@Autowired
	private mSuppliersDAO mSuppliersDAO;
	
	@Override
	public List<mSuppliers> getList() {
		// TODO Auto-generated method stub
		return mSuppliersDAO.getList();
	}
	
}
