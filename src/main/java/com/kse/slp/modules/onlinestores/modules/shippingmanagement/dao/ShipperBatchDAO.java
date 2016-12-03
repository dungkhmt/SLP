package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.ShipperBatch;

public interface ShipperBatchDAO {
	public int saveAShipperBatch(ShipperBatch shipper);
	public void removeShipperBatch(String batchCode);
	public List<String> getShippersInBatch(String batch);
}
