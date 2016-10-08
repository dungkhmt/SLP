package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;

import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;





public interface mPickupDeliveryOrdersDAO {
	public List<mPickupDeliveryOrders> getList();
	public List<mPickupDeliveryOrders> getListInBatch(String batchCode);
	public int saveAnOrder(mPickupDeliveryOrders pickupDeliveryOrder );
	public mPickupDeliveryOrders loadAPickupDeliveryOrderbyCode(String pickupDeliveryOrderCode);
	public void updateAnOrder(mPickupDeliveryOrders pickupDelivery);
	public void deleteOrdersInBatch(String batchCode);

}
