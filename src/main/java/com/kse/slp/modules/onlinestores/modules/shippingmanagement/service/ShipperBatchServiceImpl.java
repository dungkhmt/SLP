package com.kse.slp.modules.onlinestores.modules.shippingmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao.ShipperBatchDAO;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.ShipperBatch;

@Service("ShipperBatchService")
public class ShipperBatchServiceImpl implements ShipperBatchService {

	@Autowired
	ShipperBatchDAO ShipperBatchDAO;
	
	@Override
	public int saveAShipperBatch(String shipperCode, String BatchCode) {
		// TODO Auto-generated method stub
		ShipperBatch shipper = new ShipperBatch();
		shipper.setSHPBAT_ShipperCode(shipperCode);
		shipper.setSHPBAT_BatchCode(BatchCode);
		
		int id = ShipperBatchDAO.saveAShipperBatch(shipper);
		
		return id;
	}

	@Override
	public void deleteShipperBatch(String batchCode) {
		// TODO Auto-generated method stub
		ShipperBatchDAO.removeShipperBatch(batchCode);
	}

}
