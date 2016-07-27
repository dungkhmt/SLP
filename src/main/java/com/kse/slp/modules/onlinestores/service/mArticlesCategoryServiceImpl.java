package com.kse.slp.modules.onlinestores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.dao.mArticlesCategoryDAO;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;

@Service("mArticlesCategoryService")
public class mArticlesCategoryServiceImpl implements mArticlesCategoryService{

	@Autowired
	private mArticlesCategoryDAO mArticlesCategoryDAO;
	
	@Override
	public List<mArticlesCategory> getList() {
		// TODO Auto-generated method stub
		return mArticlesCategoryDAO.getList();
	}

}
