package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;

public interface mShippersDAO {
	public List<mShippers> getList();
	public mShippers loadShiperByUserName(String userName);
}
