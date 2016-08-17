package com.kse.slp.modules.containerdelivery.service;

import java.util.List;

import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;



public interface mPickupDeliveryOrdersService {
	public int saveAPickupDeliveryOrders(String oPD_ClientCode,String oPD_RequestDateTime,String oPD_PickupAddress,double oPD_PickupLat,double oPD_PickupLng,String oPD_EarlyPickupDateTime
			,String oPD_LatePickupDateTime,String oPD_DeliveryAddress,
			double oPD_DeliveryLat,double oPD_DeliveryLng,String oPD_EarlyDeliveryDateTime,String oPD_LateDeliveryDateTime,int oPD_Volumn);
	public List<mPickupDeliveryOrders> getListOrderPickupDelivery();
	
}
