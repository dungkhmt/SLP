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
	int 	REQDC_NumberPassengers;
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
	
}
