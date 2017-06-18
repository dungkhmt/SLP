package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;

public interface mShippersDAO {
	public List<mShippers> getList();
	public mShippers loadShiperByUserName(String userName);
	public void updateAShipper(mShippers shipper);
	public List<mShippers> getByCustomerCode(String cus_code);
	public List<mShippers> getListInBatch(String batchCode);
	int save(mShippers ship);
	public mShippers getByCode(String shp_Code);
	public int editAShipper(mShippers shipper);
	public int delAShipper(mShippers shipper);
	
}
