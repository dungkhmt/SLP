package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;

public interface mShippersService {
	public List<mShippers> getList();
	public mShippers loadShiperByUserName(String userName);
	public void updateShipperCurrentLocation(double lat,double lng,String user,String lastDateTime);
}
