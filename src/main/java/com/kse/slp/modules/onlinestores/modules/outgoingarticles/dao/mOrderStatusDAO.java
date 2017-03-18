package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderStatus;

public interface mOrderStatusDAO {
	
	public int saveOrderStatus(mOrderStatus status);
	public void editOrderStatus(int id);
	public void deleteOrderStatus(int id);
	public List<mOrderStatus> getList();
}
