package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.Stores;

public interface StoresService {
	public List<Stores> getListStoreInBatch(String batchCode);
}
