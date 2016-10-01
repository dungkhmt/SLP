package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.StoreBatch;

public interface StoreBatchService {
	public int saveAStoreBatch(String storeCode, String batchCode);
	public void deleteStoreBatch(String batchCode);
}
