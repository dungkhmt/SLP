package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.StoreBatch;

public interface StoreBatchDAO {
	public int saveAStoreBatch(StoreBatch store);
	public void deleteStoreBatch(String batchCode);
}
