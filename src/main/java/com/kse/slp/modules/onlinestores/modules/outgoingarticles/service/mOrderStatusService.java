package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderStatus;

public interface mOrderStatusService {
	
	public int saveOrderStatus(mOrderStatus status);
	public void editOrderStatus(int id);
	public void deleteOrderStatus(int id);
	public List<mOrderStatus> getList();
	
}
