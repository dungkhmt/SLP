package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrderArticlesDAO;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrdersDAO;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
@Service("mOrderService")
public class mOrdersServiceImpl implements mOrdersService{
	@Autowired
	mOrdersDAO orderDAO;
	@Autowired 
	mOrderArticlesDAO orderArticlesDAO;
	@Override
	public int saveAOrder(String o_Code, String o_ClientCode, String o_OrderDate,
			String o_DueDate,String [] orderArticles) {
		mOrders o= new mOrders();
		o.setO_Code(o_Code);
		o.setO_ClientCode(o_ClientCode);
		o.setO_OrderDate(o_OrderDate);
		o.setO_DueDate(o_DueDate);
		int id= orderDAO.saveAOrder(o);
		
		mOrders m_o= orderDAO.getAOrderById(id);
		for(int i=0;i<orderArticles.length;i++)
		if (orderArticles[i]!=""){
			String s= orderArticles[i];
			System.out.println(s);
			String oA_Code= s.substring(0,s.indexOf(' '));
			s=s.substring(s.indexOf(' ')+1);
			String oA_Amount=s.substring(0,s.indexOf(' '));
			s=s.substring(s.indexOf(' ')+1);
			String oA_Price=s;
			
			System.out.println(s+" "+oA_Code+" "+oA_Amount+" "+oA_Price);
			mOrderArticles mOA= new mOrderArticles();
			mOA.setOA_Code(oA_Code);
			mOA.setOA_Price(Float.parseFloat(oA_Price));
			mOA.setOA_OrderCode(m_o.getO_Code());
			mOA.setOA_Amount(Integer.parseInt(oA_Amount) );
			mOA.setOA_Date(o_OrderDate);
			
			orderArticlesDAO.saveAOrderArticles(mOA);
		}
		return id;
	}

}
