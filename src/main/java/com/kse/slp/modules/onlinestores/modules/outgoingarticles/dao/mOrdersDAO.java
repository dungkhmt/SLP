package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mOrderDetail;

public interface mOrdersDAO {
	public int saveAnOrder(mOrders order);
	public mOrders getAnOrderById(int id);
	public List<mOrders> getList();
	public mOrders loadAnOrderbyOrderCode(String orderCode);
	public void updateAnOrder(mOrders order);
	public List<mOrderDetail> getListOrderDetail();	
	public List<String> getListDueDate();
	public List<mOrders> getListOrderByDueDate(String DueDate);
	public void updateStatus(String order_Code,String status);
}
