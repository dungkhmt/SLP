package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.ShipperBatch;

public interface ShipperBatchDAO {
	public int saveAShipperBatch(ShipperBatch shipper);
	public void removeShipperBatch(String batchCode);
}
