package com.kse.slp.modules.containerdelivery.service;

import java.util.List;


import com.kse.slp.modules.containerdelivery.model.RequestBatchContainerDelivery;

public interface mRequestBatchContainerDeliveryService {
	public List<RequestBatchContainerDelivery> getList();
	public RequestBatchContainerDelivery getByCode(String code);
	public List<RequestBatchContainerDelivery> getList(String CustomerCode);
}
