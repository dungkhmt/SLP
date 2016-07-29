package com.kse.slp.modules.onlinestores.modules.incomingarticles.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;

public interface mIncomingArticlesDAO {
	public List<mIncomingArticles> getList();
	public int saveAIncomingArticle(mIncomingArticles article);
}
