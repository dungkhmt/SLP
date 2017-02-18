package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.batchOnlineStoreDAO;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.batchOnlineStore;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.utilities.CodeGenerationUtility;

@Service("batchOnlineStoreService")
public class batchOnlineStoreServiceImpl implements batchOnlineStoreService {
	
	@Autowired
	batchOnlineStoreDAO batchOnlineStoreDAO;
	
	@Override
	public List<batchOnlineStore> getList() {
		return batchOnlineStoreDAO.getList();
	};
	
	@Override
	public List<batchOnlineStore> getList(String CustomerCode) {
		return batchOnlineStoreDAO.getList(CustomerCode);
	};
	
	@Override
	public batchOnlineStore getByREQBAT_ID(int REQBAT_ID) {
		return batchOnlineStoreDAO.getREQBAT_ID(REQBAT_ID);
	};
	
	@Override
	public int save(batchOnlineStore newBatchOnlineStore) {
		int id = batchOnlineStoreDAO.addBatchOnlineStore(newBatchOnlineStore);
		return id;
	};
	
	@Override
	public void edit(int REQBAT_ID, String REQBAT_Description) {
		batchOnlineStoreDAO.updateBatchOnlineStore(REQBAT_ID, REQBAT_Description);
	};
	
	@Override
	public void delete(int REQBAT_ID) {
		batchOnlineStoreDAO.deleteBatchOnlineStore(REQBAT_ID);
	}
}
