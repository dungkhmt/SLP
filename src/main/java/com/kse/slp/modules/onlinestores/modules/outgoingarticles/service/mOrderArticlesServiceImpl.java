package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrderArticlesDAO;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;

@Service("mOrderArticlesService")
public class mOrderArticlesServiceImpl implements mOrderArticlesService {
	@Autowired
	mOrderArticlesDAO orderArticleDAO;
	@Override
	public int saveAOrderArticles(String oA_Code, String oA_OrderCode,
			String oA_Amount, String oA_Date, float oA_Price) {
		// TODO Auto-generated method stub
		mOrderArticles oa= new mOrderArticles();
		oa.setOA_Code(oA_Code);
		oa.setOA_OrderCode(oA_OrderCode);
		oa.setOA_Amount(Integer.parseInt(oA_Amount));
		oa.setOA_Price(oA_Price);
		oa.setOA_Date(oA_Date);
		
		return orderArticleDAO.saveAOrderArticles(oa);
	}

}
