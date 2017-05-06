package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;

public interface mShippersService {
	public List<mShippers> getList();
	public mShippers loadShiperByUserName(String userName);
	public void updateShipperCurrentLocation(double lat,double lng,String user,String lastDateTime);
	public List<mShippers> getByCustomerCode(String cus_code);
	public List<mShippers> getListInBatch(String batchCode);
	public int save(String shpCode, String shpDepotAd, float shpDepLat, float shpLng,
			String shpCurrentLocation, String shpLastDate,
			String shpStatusCode, String shpVehicle, int shpCapacity1,
			int shpCapacity2, String shpCusCode, String shpUser);
	public int save(mShippers ship);
	public mShippers getByCode(String SHP_Code);
	public int editAShipper(mShippers ship);
	public int delAShipper(String shipCode);
}
