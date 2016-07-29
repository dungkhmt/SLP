package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

public interface mOrdersService {
	public int saveAOrder(String code,String clientCode,String orderDate,String dueDate,String[] orderArticles);
}
