package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

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
	public int saveAOrder(String o_ClientCode, String o_OrderDate,
			String o_DueDate,String o_DeliveryAddress,float o_DeliveryLat,float o_DeliveryLng,String o_TimeEarly,String o_TimeLate,float o_Price, String [] orderArticles) {
		mOrders o= new mOrders();
		o.setO_Code(o_ClientCode+o_OrderDate+o_OrderDate);
		o.setO_ClientCode(o_ClientCode);
		o.setO_OrderDate(o_OrderDate);
		o.setO_DueDate(o_DueDate);
		o.setO_DeliveryAddress(o_DeliveryAddress);
		o.setO_DeliveryLat(o_DeliveryLat);
		o.setO_DeliveryLng(o_DeliveryLng);
		o.setO_TimeEarly(o_TimeEarly);
		o.setO_TimeLate(o_TimeLate);
		o.setO_Price(o_Price);
		int id= orderDAO.saveAOrder(o);
		
		mOrders m_o= orderDAO.getAOrderById(id);
		for(int i=0;i<orderArticles.length;i++)
		if (orderArticles[i]!=""){
			String s= orderArticles[i];
			
			String oA_Code= s.substring(0,s.indexOf(' '));
			s=s.substring(s.indexOf(' ')+1);
			String oA_Amount=s.substring(0,s.indexOf(' '));
			s=s.substring(s.indexOf(' ')+1);
			String oA_Price=s;
			
			
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
	@Override
	public List<mOrders> getList() {
		// TODO Auto-generated method stub
		return orderDAO.getList();
	}
	@Override
	public mOrders loadAOrderbyOrderCode(String orderCode) {
		// TODO Auto-generated method stub
		return orderDAO.loadAOrderbyOrderCode(orderCode);
	}
	@Override
	public void setDeliveredbyOrderCode(String orderCode) {
		// TODO Auto-generated method stub
		mOrders o= loadAOrderbyOrderCode(orderCode);
		o.setO_Delivered(1);
		orderDAO.setDeliveredOrder(o);
	}

}
