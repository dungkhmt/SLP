package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.batchOnlineStore;

public interface batchOnlineStoreDAO {
	
	public batchOnlineStore getREQBAT_ID(int REQBAT_ID);
	public List<batchOnlineStore> getList();
	public List<batchOnlineStore> getList(String CustomerCode);
	public int addBatchOnlineStore(batchOnlineStore newBatch);
	public void updateBatchOnlineStore( int REQBAT_ID,String REQBAT_Description);
	public void deleteBatchOnlineStore(int batchCode);
}
