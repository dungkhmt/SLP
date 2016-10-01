package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.StoresDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.Stores;

@Service("StoresService")
public class StoresServiceImpl implements StoresService {

	@Autowired
	StoresDAO StoresDAO;
	
	@Override
	public List<Stores> getListStoreInBatch(String batchCode) {
		// TODO Auto-generated method stub
		return StoresDAO.getListStoreInBatch(batchCode);
	}

}
