package com.kse.slp.modules.onlinestores.modules.incomingarticles.validation;

import java.util.List;

public class mListIncomingArticlesForm {
	private List<mIncomingArticleValidation> lstIncomingArticles;

	public List<mIncomingArticleValidation> getLstIncomingArticles() {
		return lstIncomingArticles;
	}

	public void setLstIncomingArticles(List<mIncomingArticleValidation> lstIncomingArticles) {
		this.lstIncomingArticles = lstIncomingArticles;
	}

	public mListIncomingArticlesForm(List<mIncomingArticleValidation> lstIncomingArticles) {
		super();
		this.lstIncomingArticles = lstIncomingArticles;
	}

	public mListIncomingArticlesForm() {
		super();
		// TODO Auto-generated constructor stub
	}
}
