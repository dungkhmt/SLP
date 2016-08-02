package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;

public interface mOrdersService {
	public int saveAOrder(String clientCode,String orderDate,String dueDate,String deliveryAddress,float lat,float lng,String timeEarly,String timeLate,String[] orderArticles);
	public List<mOrders> getList();
}
