package com.kse.slp.modules.onlinestores.modules.incomingarticles.service;

public interface mIncomingArticlesService {
	public int saveAIncomingArticle(String article_code, int article_amount, float article_price,
			String article_spcode, String article_date);
}
