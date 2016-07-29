package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;

public interface mOrdersDAO {
	public int saveAOrder(mOrders order);
	public mOrders getAOrderById(int id);
}
