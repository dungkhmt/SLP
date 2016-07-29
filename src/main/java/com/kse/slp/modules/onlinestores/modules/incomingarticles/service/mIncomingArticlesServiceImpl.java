package com.kse.slp.modules.onlinestores.modules.incomingarticles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.incomingarticles.dao.mIncomingArticlesDAO;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;

@Service("mIncomingArticlesService")
public class mIncomingArticlesServiceImpl implements mIncomingArticlesService{

	@Autowired
	private mIncomingArticlesDAO mIncomingArticlesDAO;
	
	@Override
	public int saveAIncomingArticle(String article_code, int article_amount, float article_price, String article_spcode,
			String article_date) {
		// TODO Auto-generated method stub
		
		mIncomingArticles article = new mIncomingArticles();
		article.setIA_ArticleCode(article_code);
		article.setIA_Amount(article_amount);
		article.setIA_Price(article_price);
		article.setIA_Supplier_Code(article_spcode);
		article.setIA_Date(article_date);
		
		return mIncomingArticlesDAO.saveAIncomingArticle(article);
	}

}
