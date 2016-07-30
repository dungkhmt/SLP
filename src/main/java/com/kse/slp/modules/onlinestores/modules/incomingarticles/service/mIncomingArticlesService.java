package com.kse.slp.modules.onlinestores.modules.incomingarticles.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;

public interface mIncomingArticlesService {
	
	public List<mIncomingArticles> getList();
	public List<mIncomingArticles> getOrderedListByDate();
	public int saveAIncomingArticle(String article_code, int article_amount, float article_price,
			String article_spcode, String article_date);
}
