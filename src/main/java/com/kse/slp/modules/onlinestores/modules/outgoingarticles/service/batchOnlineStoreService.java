package com.kse.slp.modules.onlinestores.modules.outgoingarticles.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.batchOnlineStore;

public interface batchOnlineStoreService {
	public List<batchOnlineStore> getList();
	public List<batchOnlineStore> getList(String CustomerCode);
	public batchOnlineStore getByREQBAT_ID(int REQBAT_ID);
	public int save(batchOnlineStore newBatchOnlineStore);
	public void edit(int REQBAT_ID, String REQBAT_Description);
	public void delete(int REQBAT_ID);
}
