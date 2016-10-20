package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

public interface mOrderArticlesService {
	public int saveAOrderArticles(String oA_Code,String oA_OrderCode,String oA_Amount,String oA_Date,float oA_Price);
	public void deleteOrderArticles(String orderCode);
}
