package com.kse.slp.modules.containerdelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.containerdelivery.dao.mPickupDeliveryOrdersDAO;
import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.utilities.CodeGenerationUtility;

@Service("mPickupDeliveryOrdersService")
public class mPickupDeliveryOrdersServiceImpl implements mPickupDeliveryOrdersService {
	@Autowired
	mPickupDeliveryOrdersDAO pickupDeliveryOrdersDAO;


	@Override
	public List<mPickupDeliveryOrders> getListOrderPickupDelivery() {
		// TODO Auto-generated method stub
		return pickupDeliveryOrdersDAO.getList();
	}

	@Override
	public int saveAPickupDeliveryOrders(String oPD_ClientCode,
			String oPD_RequestDateTime, String oPD_PickupAddress,
			double oPD_PickupLat, double oPD_PickupLng,
			String oPD_EarlyPickupDateTime, String oPD_LatePickupDateTime,
			String oPD_DeliveryAddress, double oPD_DeliveryLat,
			double oPD_DeliveryLng, String oPD_EarlyDeliveryDateTime,
			String oPD_LateDeliveryDateTime, int oPD_Volumn) {
		mPickupDeliveryOrders oPD= new mPickupDeliveryOrders();
		oPD.setOPD_Code(oPD_ClientCode);
		oPD.setOPD_ClientCode(oPD_ClientCode);
		oPD.setOPD_RequestDateTime(oPD_RequestDateTime);
		oPD.setOPD_PickupAddress(oPD_PickupAddress);
		oPD.setOPD_PickupLat(oPD_PickupLat);
		oPD.setOPD_PickupLng(oPD_PickupLng);
		oPD.setOPD_EarlyPickupDateTime(oPD_EarlyPickupDateTime);
		oPD.setOPD_LatePickupDateTime(oPD_LatePickupDateTime);
		oPD.setOPD_DeliveryAddress(oPD_DeliveryAddress);
		oPD.setOPD_DeliveryLat(oPD_DeliveryLat);
		oPD.setOPD_DeliveryLng(oPD_DeliveryLng);
		oPD.setOPD_EarlyDeliveryDateTime(oPD_EarlyDeliveryDateTime);
		oPD.setOPD_LateDeliveryDateTime(oPD_LateDeliveryDateTime);
		oPD.setOPD_Volumn(oPD_Volumn);
		oPD.setOPD_StatusCode(Constants.ORDER_STATUS_NOT_IN_ROUTE);
		int id= pickupDeliveryOrdersDAO.saveAnOrder(oPD);
		oPD.setOPD_Code("ORPD"+CodeGenerationUtility.genOrderCode(id));
		pickupDeliveryOrdersDAO.updateAnOrder(oPD);
		return id;
	}

	@Override
	public void updateStatus(String orderCode, String status) {
		// TODO Auto-generated method stub
		mPickupDeliveryOrders o= pickupDeliveryOrdersDAO.loadAPickupDeliveryOrderbyCode(orderCode);
		o.setOPD_StatusCode(status);
		pickupDeliveryOrdersDAO.updateAnOrder(o);
		
	}
}
