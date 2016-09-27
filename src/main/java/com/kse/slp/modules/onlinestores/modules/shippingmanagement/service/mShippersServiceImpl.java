package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.mShippersDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;

@Service("mShippersService")
public class mShippersServiceImpl implements mShippersService{

	@Autowired
	private mShippersDAO mShippersDAO;
	
	@Override
	public List<mShippers> getList() {
		// TODO Auto-generated method stub
		return mShippersDAO.getList();
	}

	@Override
	public mShippers loadShiperByUserName(String userName) {
		// TODO Auto-generated method stub
		return mShippersDAO.loadShiperByUserName(userName);
	}

	@Override
	public void updateShipperCurrentLocation(double lat, double lng, String user,String lastDateTime) {
		// TODO Auto-generated method stub
		mShippers shipper= mShippersDAO.loadShiperByUserName(user);
		shipper.setSHP_CurrentLocation(lat + ", "+lng);
		shipper.setSHP_LastUpdateDateTime(lastDateTime);
		mShippersDAO.updateAShipper(shipper);
	}

	@Override
	public List<mShippers> getByCustomerCode(String cus_code) {
		// TODO Auto-generated method stub
		return mShippersDAO.getByCustomerCode(cus_code);
	}

	

	
}
