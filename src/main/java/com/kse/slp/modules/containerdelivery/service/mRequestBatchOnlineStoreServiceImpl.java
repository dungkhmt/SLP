package com.kse.slp.modules.containerdelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kse.slp.modules.containerdelivery.dao.mRequestBatchOnlineStoreDAO;

import com.kse.slp.modules.containerdelivery.model.RequestBatchOnlineStore;

@Service("mRequestBatchOnlineStoreService")
public class mRequestBatchOnlineStoreServiceImpl implements mRequestBatchOnlineStoreService {
	@Autowired
	mRequestBatchOnlineStoreDAO requestBatchDAO;
	@Override
	public List<RequestBatchOnlineStore> getList() {
		// TODO Auto-generated method stub
		return requestBatchDAO.getList();
	}
	@Override
	public RequestBatchOnlineStore getByCode(String code) {
		// TODO Auto-generated method stub
		return requestBatchDAO.getByCode(code);
	}
	@Override
	public List<RequestBatchOnlineStore> getList(String CustomerCode) {
		// TODO Auto-generated method stub
		return requestBatchDAO.getList(CustomerCode);
	}

}
