package com.kse.slp.modules.dichung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.dichung.dao.RequestDiChungDAO;
import com.kse.slp.modules.dichung.model.RequestDiChung;
@Service("RequestDiChungService")
public class RequestDiChungServiceImpl implements RequestDiChungService{
	@Autowired
	RequestDiChungDAO requestDiChungDAO;
	@Override
	public int saveARequest(String rEQDC_TicketCode, String rEQDC_DepartTime,
			String rEQDC_ChunkName, String rEQDC_PickupAddress,
			String rEQDC_DeliveryAddress, int rEQDC_NumberPassengers,String rEQDC_BatchCode) {
		// TODO Auto-generated method stub
		RequestDiChung r= new RequestDiChung();
		r.setREQDC_TicketCode(rEQDC_TicketCode);
		r.setREQDC_DepartTime(rEQDC_DepartTime);
		r.setREQDC_ChunkName(rEQDC_ChunkName);
		r.setREQDC_PickupAddress(rEQDC_PickupAddress);
		r.setREQDC_DeliveryAddress(rEQDC_DeliveryAddress);
		r.setREQDC_NumberPassengers(rEQDC_NumberPassengers);
		r.setREQDC_BatchCode(rEQDC_BatchCode);
		return requestDiChungDAO.saveARequest(r);
	}
	@Override
	public List<RequestDiChung> getListInBatch(String batchCode) {
		// TODO Auto-generated method stub
		return requestDiChungDAO.getListInBatch(batchCode);
	}
	@Override
	public void deleteRequestDiChungInBatch(String batchCode) {
		// TODO Auto-generated method stub
		requestDiChungDAO.deleteRequestDiChungInBatch(batchCode);
	}
	
}
