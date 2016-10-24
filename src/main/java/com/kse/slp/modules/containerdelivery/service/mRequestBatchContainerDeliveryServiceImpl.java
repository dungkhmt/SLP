package com.kse.slp.modules.containerdelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.containerdelivery.dao.mRequestBatchContainerDeliveryDAO;

import com.kse.slp.modules.containerdelivery.model.RequestBatchContainerDelivery;

@Service("mRequestBatchContainerDeliveryService")
public class mRequestBatchContainerDeliveryServiceImpl implements mRequestBatchContainerDeliveryService {
	@Autowired
	mRequestBatchContainerDeliveryDAO requestBatchDAO;
	@Override
	public List<RequestBatchContainerDelivery> getList() {
		// TODO Auto-generated method stub
		return requestBatchDAO.getList();
	}
	@Override
	public RequestBatchContainerDelivery getByCode(String code) {
		// TODO Auto-generated method stub
		return requestBatchDAO.getByCode(code);
	}
	@Override
	public List<RequestBatchContainerDelivery> getList(String CustomerCode) {
		// TODO Auto-generated method stub
		return requestBatchDAO.getList(CustomerCode);
	}

}
