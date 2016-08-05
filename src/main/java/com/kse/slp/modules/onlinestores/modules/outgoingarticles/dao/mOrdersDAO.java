package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;

public interface mOrdersDAO {
	public int saveAnOrder(mOrders order);
	public mOrders getAnOrderById(int id);
	public List<mOrders> getList();
	public mOrders loadAnOrderbyOrderCode(String orderCode);
	
	public void updateAnOrder(mOrders order);
}
