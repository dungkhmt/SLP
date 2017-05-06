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

	@Override
	public List<mShippers> getListInBatch(String batchCode) {
		// TODO Auto-generated method stub
		return mShippersDAO.getListInBatch(batchCode);
	}

	@Override
	public int save(String shpCode, String shpDepotAd, float shpDepLat,
			float shpLng, String shpCurrentLocation, String shpLastDate,
			String shpStatusCode, String shpVehicle, int shpCapacity1,
			int shpCapacity2, String shpCusCode, String shpUser) {
			mShippers ship = new mShippers();
			return mShippersDAO.save(ship);
	}

	@Override
	public int save(mShippers ship) {
		return mShippersDAO.save(ship);
	}

	@Override
	public mShippers getByCode(String SHP_Code) {
		return mShippersDAO.getByCode(SHP_Code);
	}

	@Override
	public int editAShipper(mShippers ship) {
		mShippers shipper = mShippersDAO.getByCode(ship.getSHP_Code());
		shipper.setSHP_DepotAddress(ship.getSHP_DepotAddress());
		shipper.setSHP_DepotLat(ship.getSHP_DepotLat());
		shipper.setSHP_DepotLng(ship.getSHP_DepotLng());
		shipper.setSHP_CurrentLocation(ship.getSHP_CurrentLocation());
		shipper.setSHP_LastUpdateDateTime(ship.getSHP_LastUpdateDateTime());
		shipper.setSHP_StatusCode(ship.getSHP_StatusCode());
		shipper.setSHP_VehicleType(ship.getSHP_VehicleType());
		shipper.setSHP_Capacity_1(ship.getSHP_Capacity_1());
		shipper.setSHP_Capacity_2(ship.getSHP_Capacity_2());
		shipper.setSHP_Customer_Code(ship.getSHP_Customer_Code());
		shipper.setSHP_User_Name(ship.getSHP_User_Name());
		return mShippersDAO.editAShipper(shipper);
	}

	@Override
	public int delAShipper(String shipCode) {
		// TODO Auto-generated method stub
		mShippers shipper = mShippersDAO.getByCode(shipCode);
		return mShippersDAO.delAShipper(shipper);
	}
	
	

	
}
