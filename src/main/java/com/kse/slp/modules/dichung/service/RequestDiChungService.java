package com.kse.slp.modules.dichung.service;

public interface RequestDiChungService {
	public int saveARequest(String rEQDC_TicketCode,String rEQDC_DepartTime,String rEQDC_ChunkName,String rEQDC_PickupAddress,String rEQDC_DeliveryAddress,int 	rEQDC_NumberPassengers, String rEQDC_BatchCode);
}
