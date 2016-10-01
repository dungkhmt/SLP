package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.StoreBatchDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.StoreBatch;

@Service("StoreBatchService")
public class StoreBatchServiceImpl implements StoreBatchService {

	@Autowired
	StoreBatchDAO StoreBatchDAO;
	
	@Override
	public int saveAStoreBatch(String storeCode, String batchCode) {
		// TODO Auto-generated method stub
		StoreBatch store = new StoreBatch();
		store.setSTBAT_StoreCode(storeCode);
		store.setSTBAT_BatchCode(batchCode);
		
		int id = StoreBatchDAO.saveAStoreBatch(store);
		return id;
	}

	@Override
	public void deleteStoreBatch(String batchCode) {
		// TODO Auto-generated method stub
		StoreBatchDAO.deleteStoreBatch(batchCode);
	}

}
