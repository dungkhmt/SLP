package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrderArticlesDAO;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrdersDAO;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderDetail;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.sOrder;
import com.kse.slp.modules.utilities.CodeGenerationUtility;

@Service("mOrderService")
public class mOrdersServiceImpl implements mOrdersService{
	@Autowired
	mOrdersDAO orderDAO;
	@Autowired 
	mOrderArticlesDAO orderArticlesDAO;
	@Override
	public String saveAnOrder(String o_ClientCode, String o_OrderDate,
			String o_DueDate,String o_DeliveryAddress,float o_DeliveryLat,float o_DeliveryLng,String o_TimeEarly,String o_TimeLate,float o_Price, String [] orderArticles,String o_BatchCode) {
		mOrders o= new mOrders();
		o.setO_Code(o_ClientCode);
		o.setO_ClientCode(o_ClientCode);
		o.setO_OrderDate(o_OrderDate);
		o.setO_DueDate(o_DueDate);
		o.setO_DeliveryAddress(o_DeliveryAddress);
		o.setO_DeliveryLat(o_DeliveryLat);
		o.setO_DeliveryLng(o_DeliveryLng);
		o.setO_TimeEarly(o_TimeEarly);
		o.setO_TimeLate(o_TimeLate);
		o.setO_Price(o_Price);
		o.setO_BatchCode(o_BatchCode);
		o.setO_Status_Code(Constants.ORDER_STATUS_NOT_IN_ROUTE);
		int id= orderDAO.saveAnOrder(o);
		String O_Code = "OR"+CodeGenerationUtility.genOrderCode(id);
		o.setO_Code(O_Code);
		orderDAO.updateAnOrder(o);
		mOrders m_o= orderDAO.getAnOrderById(id);
		if(orderArticles != null){
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
		}
		return O_Code;
	}
	@Override
	public List<mOrders> getList() {
		// TODO Auto-generated method stub
		return orderDAO.getList();
	}
	@Override
	public mOrders loadAnOrderbyOrderCode(String orderCode) {
		// TODO Auto-generated method stub
		return orderDAO.loadAnOrderbyOrderCode(orderCode);
	}
	@Override
	public void setStatusbyOrderCode(String orderCode,String status) {
		// TODO Auto-generated method stub
		System.out.println(name()+" "+orderCode+" "+status);
		mOrders o= loadAnOrderbyOrderCode(orderCode);
		if(status.equals(Constants.ORDER_STATUS_ARRIVED_BUT_NOT_DELIVERIED)){
			o.setO_Status_Code(Constants.ORDER_STATUS_ARRIVED_BUT_NOT_DELIVERIED);
			orderDAO.updateAnOrder(o);
		} else if(status.equals(Constants.ORDER_STATUS_DELIVERIED)){
			o.setO_Status_Code(Constants.ORDER_STATUS_DELIVERIED);
			orderDAO.updateAnOrder(o);
		}
	}
	@Override
	public List<mOrderDetail> getListOrderDetail(String status, String C_Code) {
		// TODO Auto-generated method stub
		return orderDAO.getListOrderDetail(status, C_Code);
	}
	@Override
	public List<String> getListDueDate() {
		// TODO Auto-generated method stub
		return orderDAO.getListDueDate();
	}
	@Override
	public List<mOrders> getListOrderByDueDate(String DueDate) {
		// TODO Auto-generated method stub
		return orderDAO.getListOrderByDueDate(DueDate);
	}
	
	@Override
	public List<sOrder> getstaticsOrders(String from, String to, String type, String status, String cus_Code) {
		return orderDAO.staticsOrders(from, to, type, status, cus_Code);
	};
	
	
	@Override
	public void updateStatus(String order_Code, String status) {
		// TODO Auto-generated method stub
		orderDAO.updateStatus(order_Code, status);
	}
	@Override
	public void updateOrderBatch( String O_Code, String O_BatchCode) {
		orderDAO.updateOrderBatch(O_Code, O_BatchCode);
	};
	String name(){
		return "mOrdersServiceImpl";
	}
	@Override
	public List<mOrders> getListOrderByBatchCode(String batchCode) {
		// TODO Auto-generated method stub
		return orderDAO.getListOrderByBatchCode(batchCode);
	}
	
	@Override
	public List<mOrders> getListOrderByCUSCode(String CUSCode) {
		return orderDAO.getListOrderByCUSCode(CUSCode);
	}
	
	@Override
	public void deleteOrder(String batchCode) {
		// TODO Auto-generated method stub
		orderDAO.deleteOrder(batchCode);
	}
	@Override
	public void updateAnOrder(mOrders order) {
		// TODO Auto-generated method stub
		orderDAO.updateAnOrder(order);
	}
}
