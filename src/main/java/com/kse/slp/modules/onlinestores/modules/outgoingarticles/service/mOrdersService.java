package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;

public interface mOrdersService {
	public int saveAOrder(String code,String clientCode,String orderDate,String dueDate,String[] orderArticles);
	public List<mOrders> getList();
}
