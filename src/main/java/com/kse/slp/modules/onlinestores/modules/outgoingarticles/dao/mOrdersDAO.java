package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderDetail;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.sOrder;

public interface mOrdersDAO {
	public int saveAnOrder(mOrders order);
	public mOrders getAnOrderById(int id);
	public List<mOrders> getList();
	public mOrders loadAnOrderbyOrderCode(String orderCode);
	public void updateAnOrder(mOrders order);
	public List<mOrderDetail> getListOrderDetail(String status, String CUSCode);	
	public List<String> getListDueDate();
	public List<mOrders> getListOrderByDueDate(String DueDate);
	public List<mOrders> getListOrderByBatchCode(String batchCode);
	public void updateStatus(String order_Code,String status);
	public void updateOrderBatch( String O_Code, String O_BatchCode);
	public void deleteOrder(String batchCode);
	public List<sOrder> staticsOrders(String from, String to, String type, String status, String cus_Code);
	public List<mOrders> getListOrderByCUSCode(String cUSCode);
}
