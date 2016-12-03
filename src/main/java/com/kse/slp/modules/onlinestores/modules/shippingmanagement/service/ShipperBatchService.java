package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import java.util.List;

public interface ShipperBatchService {
	public int saveAShipperBatch(String shipperCode, String BatchCode);
	public void deleteShipperBatch(String batchCode);
	public List<String> getShipperInBatch(String batchCode);
}
