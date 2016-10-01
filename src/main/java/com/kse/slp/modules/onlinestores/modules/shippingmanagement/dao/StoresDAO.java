package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.Stores;

public interface StoresDAO {
	public List<Stores> getListStoreInBatch(String batchCode);
}
