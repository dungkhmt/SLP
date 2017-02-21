package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.sOrder;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mOrderDetail;

public interface mOrdersService {
	public String saveAnOrder(String clientCode,String orderDate,String dueDate,String deliveryAddress,float lat,float lng,String timeEarly,String timeLate,float o_Price,String[] orderArticles,String o_BatchCode);
	public List<mOrders> getList();
	public mOrders loadAnOrderbyOrderCode(String orderCode);
	public void setStatusbyOrderCode(String orderCode,String status);
	public List<mOrderDetail> getListOrderDetail();
	public List<String> getListDueDate();
	public List<mOrders> getListOrderByDueDate(String DueDate);
	public List<mOrders> getListOrderByBatchCode(String batchCode);
	public List<sOrder> getstaticsOrders(String from, String to, String type, String status, String cus_Code);
	public void updateStatus(String order_Code, String status);
	public void deleteOrder(String batchCode);
	public void updateAnOrder(mOrders order);
}
