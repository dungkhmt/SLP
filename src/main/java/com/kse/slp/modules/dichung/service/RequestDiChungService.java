package com.kse.slp.modules.dichung.service;

import java.util.List;

import com.kse.slp.modules.dichung.model.RequestDiChung;

public interface RequestDiChungService {
	public int saveARequest(String rEQDC_TicketCode,String rEQDC_DepartTime,String rEQDC_ChunkName,String rEQDC_PickupAddress,String rEQDC_DeliveryAddress,int 	rEQDC_NumberPassengers, String rEQDC_BatchCode);
	public List<RequestDiChung> getListInBatch(String batchCode);
	public void deleteRequestDiChungInBatch(String batchCode);
}
