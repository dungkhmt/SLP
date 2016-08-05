package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;

public interface mOrdersService {
	public int saveAnOrder(String clientCode,String orderDate,String dueDate,String deliveryAddress,float lat,float lng,String timeEarly,String timeLate,float o_Price,String[] orderArticles);
	public List<mOrders> getList();
	public mOrders loadAnOrderbyOrderCode(String orderCode);
	public void setDeliveredbyOrderCode(String orderCode);
}
