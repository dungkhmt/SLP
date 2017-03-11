package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrderArticlesDAO;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrderStatusDAOImpl;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrdersDAO;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderStatus;

@Service("mOrderStatusService")
public class mOrderStatusServiceImpl implements mOrderStatusService{
	
	@Autowired
	mOrderStatusDAOImpl mOrderStatusDAO;
	
	@Override
	public int saveOrderStatus(mOrderStatus status) {
		//TODO
		return 0;
	};
	
	@Override
	public void editOrderStatus(int id) {
		//TODO
	};
	
	@Override
	public void deleteOrderStatus(int id) {
		//TODO
	};
	
	@Override
	public List<mOrderStatus> getList() {
		return mOrderStatusDAO.getList();
	};
}
