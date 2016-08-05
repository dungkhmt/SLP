package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mOrderDetail;

public interface mOrdersDAO {
	public int saveAOrder(mOrders order);
	public mOrders getAOrderById(int id);
	public List<mOrders> getList();
	public mOrders loadAOrderbyOrderCode(String orderCode);
	public void setDeliveredOrder(mOrders order);
	public List<mOrderDetail> getListOrderDetail();
}
