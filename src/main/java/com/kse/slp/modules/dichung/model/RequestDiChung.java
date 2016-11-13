package com.kse.slp.modules.dichung.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblrequestdichung")
public class RequestDiChung implements Serializable {
	@Id
	@GeneratedValue
	int 	REQDC_ID;
	String REQDC_TicketCode;
	String REQDC_DepartTime;
	String REQDC_ChunkName;
	String REQDC_PickupAddress;
	String REQDC_DeliveryAddress;
	String REQDC_PickupPos;
	String REQDC_DeliveryPos;
	int 	REQDC_NumberPassengers;
	String REQDC_BatchCode;
	
	public String getREQDC_BatchCode() {
		return REQDC_BatchCode;
	}
	public void setREQDC_BatchCode(String rEQDC_BatchCode) {
		REQDC_BatchCode = rEQDC_BatchCode;
	}
	public int getREQDC_ID() {
		return REQDC_ID;
	}
	public void setREQDC_ID(int rEQDC_ID) {
		REQDC_ID = rEQDC_ID;
	}
	public String getREQDC_TicketCode() {
		return REQDC_TicketCode;
	}
	public void setREQDC_TicketCode(String rEQDC_TicketCode) {
		REQDC_TicketCode = rEQDC_TicketCode;
	}
	public String getREQDC_DepartTime() {
		return REQDC_DepartTime;
	}
	public void setREQDC_DepartTime(String rEQDC_DepartTime) {
		REQDC_DepartTime = rEQDC_DepartTime;
	}
	public String getREQDC_ChunkName() {
		return REQDC_ChunkName;
	}
	public void setREQDC_ChunkName(String rEQDC_ChunkName) {
		REQDC_ChunkName = rEQDC_ChunkName;
	}
	public String getREQDC_PickupAddress() {
		return REQDC_PickupAddress;
	}
	public void setREQDC_PickupAddress(String rEQDC_PickupAddress) {
		REQDC_PickupAddress = rEQDC_PickupAddress;
	}
	public String getREQDC_DeliveryAddress() {
		return REQDC_DeliveryAddress;
	}
	public void setREQDC_DeliveryAddress(String rEQDC_DeliveryAddress) {
		REQDC_DeliveryAddress = rEQDC_DeliveryAddress;
	}
	public int getREQDC_NumberPassengers() {
		return REQDC_NumberPassengers;
	}
	public void setREQDC_NumberPassengers(int rEQDC_NumberPassengers) {
		REQDC_NumberPassengers = rEQDC_NumberPassengers;
	}
	
	public String getREQDC_PickupPos() {
		return REQDC_PickupPos;
	}
	public void setREQDC_PickupPos(String rEQDC_PickupPos) {
		REQDC_PickupPos = rEQDC_PickupPos;
	}
	public String getREQDC_DeliveryPos() {
		return REQDC_DeliveryPos;
	}
	public void setREQDC_DeliveryPos(String rEQDC_DeliveryPos) {
		REQDC_DeliveryPos = rEQDC_DeliveryPos;
	}
	@Override
	public String toString() {
		return "RequestDiChung [REQDC_ID=" + REQDC_ID + ", REQDC_TicketCode="
				+ REQDC_TicketCode + ", REQDC_DepartTime=" + REQDC_DepartTime
				+ ", REQDC_ChunkName=" + REQDC_ChunkName
				+ ", REQDC_PickupAddress=" + REQDC_PickupAddress
				+ ", REQDC_DeliveryAddress=" + REQDC_DeliveryAddress
				+ ", REQDC_PickupPos=" + REQDC_PickupPos
				+ ", REQDC_DeliveryPos=" + REQDC_DeliveryPos
				+ ", REQDC_NumberPassengers=" + REQDC_NumberPassengers
				+ ", REQDC_BatchCode=" + REQDC_BatchCode + "]";
	}
	
	
}
