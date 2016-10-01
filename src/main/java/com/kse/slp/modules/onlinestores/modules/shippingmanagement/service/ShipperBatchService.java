package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

public interface ShipperBatchService {
	public int saveAShipperBatch(String shipperCode, String BatchCode);
	public void deleteShipperBatch(String batchCode);
}
