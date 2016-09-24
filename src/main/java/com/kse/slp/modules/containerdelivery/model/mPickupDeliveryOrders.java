package com.kse.slp.modules.containerdelivery.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="tblorderpickupdelivery")
public class mPickupDeliveryOrders implements Serializable {
	@Id
	@GeneratedValue
	int OPD_ID;
	String OPD_Code;
	String OPD_ClientCode;
	String OPD_RequestDateTime;
	String OPD_PickupAddress;
	double OPD_PickupLat;
	double OPD_PickupLng;
	String OPD_PickupPOICode;
	String OPD_EarlyPickupDateTime;
	String OPD_LatePickupDateTime;
	String OPD_DeliveryAddress;
	double OPD_DeliveryLat;
	double OPD_DeliveryLng;
	String OPD_DeliveryPOICode;
	String OPD_EarlyDeliveryDateTime;
	String OPD_LateDeliveryDateTime;
	int OPD_Volumn;
	String OPD_StatusCode;
	String OPD_RouteCode;
	String OPD_BatchCode;
	double OPD_Price;
	
	public String getOPD_BatchCode() {
		return OPD_BatchCode;
	}
	public void setOPD_BatchCode(String oPD_BatchCode) {
		OPD_BatchCode = oPD_BatchCode;
	}
	public int getOPD_ID() {
		return OPD_ID;
	}
	public void setOPD_ID(int oPD_ID) {
		OPD_ID = oPD_ID;
	}
	public String getOPD_Code() {
		return OPD_Code;
	}
	public void setOPD_Code(String oPD_Code) {
		OPD_Code = oPD_Code;
	}
	public String getOPD_ClientCode() {
		return OPD_ClientCode;
	}
	public void setOPD_ClientCode(String oPD_ClientCode) {
		OPD_ClientCode = oPD_ClientCode;
	}
	public String getOPD_RequestDateTime() {
		return OPD_RequestDateTime;
	}
	public void setOPD_RequestDateTime(String oPD_RequestDateTime) {
		OPD_RequestDateTime = oPD_RequestDateTime;
	}
	public String getOPD_PickupAddress() {
		return OPD_PickupAddress;
	}
	public void setOPD_PickupAddress(String oPD_PickupAddress) {
		OPD_PickupAddress = oPD_PickupAddress;
	}
	public double getOPD_PickupLat() {
		return OPD_PickupLat;
	}
	public void setOPD_PickupLat(double oPD_PickupLat) {
		OPD_PickupLat = oPD_PickupLat;
	}
	public double getOPD_PickupLng() {
		return OPD_PickupLng;
	}
	public void setOPD_PickupLng(double oPD_PickupLng) {
		OPD_PickupLng = oPD_PickupLng;
	}
	public String getOPD_PickupPOICode() {
		return OPD_PickupPOICode;
	}
	public void setOPD_PickupPOICode(String oPD_PickupPOICode) {
		OPD_PickupPOICode = oPD_PickupPOICode;
	}
	public String getOPD_EarlyPickupDateTime() {
		return OPD_EarlyPickupDateTime;
	}
	public void setOPD_EarlyPickupDateTime(String oPD_EarlyPickupDateTime) {
		OPD_EarlyPickupDateTime = oPD_EarlyPickupDateTime;
	}
	public String getOPD_LatePickupDateTime() {
		return OPD_LatePickupDateTime;
	}
	public void setOPD_LatePickupDateTime(String oPD_LatePickupDateTime) {
		OPD_LatePickupDateTime = oPD_LatePickupDateTime;
	}
	public String getOPD_DeliveryAddress() {
		return OPD_DeliveryAddress;
	}
	public void setOPD_DeliveryAddress(String oPD_DeliveryAddress) {
		OPD_DeliveryAddress = oPD_DeliveryAddress;
	}
	
	public double getOPD_DeliveryLat() {
		return OPD_DeliveryLat;
	}
	public void setOPD_DeliveryLat(double oPD_DeliveryLat) {
		OPD_DeliveryLat = oPD_DeliveryLat;
	}
	public double getOPD_DeliveryLng() {
		return OPD_DeliveryLng;
	}
	public void setOPD_DeliveryLng(double oPD_DeliveryLng) {
		OPD_DeliveryLng = oPD_DeliveryLng;
	}
	public String getOPD_DeliveryPOICode() {
		return OPD_DeliveryPOICode;
	}
	public void setOPD_DeliveryPOICode(String oPD_DeliveryPOICode) {
		OPD_DeliveryPOICode = oPD_DeliveryPOICode;
	}
	public String getOPD_EarlyDeliveryDateTime() {
		return OPD_EarlyDeliveryDateTime;
	}
	public void setOPD_EarlyDeliveryDateTime(String oPD_EarlyDeliveryDateTime) {
		OPD_EarlyDeliveryDateTime = oPD_EarlyDeliveryDateTime;
	}
	public String getOPD_LateDeliveryDateTime() {
		return OPD_LateDeliveryDateTime;
	}
	public void setOPD_LateDeliveryDateTime(String oPD_LateDeliveryDateTime) {
		OPD_LateDeliveryDateTime = oPD_LateDeliveryDateTime;
	}
	public int getOPD_Volumn() {
		return OPD_Volumn;
	}
	public void setOPD_Volumn(int oPD_Volumn) {
		OPD_Volumn = oPD_Volumn;
	}
	public String getOPD_StatusCode() {
		return OPD_StatusCode;
	}
	public void setOPD_StatusCode(String oPD_StatusCode) {
		OPD_StatusCode = oPD_StatusCode;
	}
	public String getOPD_RouteCode() {
		return OPD_RouteCode;
	}
	public void setOPD_RouteCode(String oPD_RouteCode) {
		OPD_RouteCode = oPD_RouteCode;
	}
	public double getOPD_Price() {
		return OPD_Price;
	}
	public void setOPD_Price(double oPD_Price) {
		OPD_Price = oPD_Price;
	}
	@Override
	public String toString() {
		return "mPickupDeliveryOrders [OPD_ID=" + OPD_ID + ", OPD_Code="
				+ OPD_Code + ", OPD_ClientCode=" + OPD_ClientCode
				+ ", OPD_RequestDateTime=" + OPD_RequestDateTime
				+ ", OPD_PickupAddress=" + OPD_PickupAddress
				+ ", OPD_PickupLat=" + OPD_PickupLat + ", OPD_PickupLng="
				+ OPD_PickupLng + ", OPD_PickupPOICode=" + OPD_PickupPOICode
				+ ", OPD_EarlyPickupDateTime=" + OPD_EarlyPickupDateTime
				+ ", OPD_LatePickupDateTime=" + OPD_LatePickupDateTime
				+ ", OPD_DeliveryAddress=" + OPD_DeliveryAddress
				+ ", OPD_DeliveryLat=" + OPD_DeliveryLat + ", OPD_DeliveryLng="
				+ OPD_DeliveryLng + ", OPD_DeliveryPOICode="
				+ OPD_DeliveryPOICode + ", OPD_EarlyDeliveryDateTime="
				+ OPD_EarlyDeliveryDateTime + ", OPD_LateDeliveryDateTime="
				+ OPD_LateDeliveryDateTime + ", OPD_Volumn=" + OPD_Volumn
				+ ", OPD_StatusCode=" + OPD_StatusCode + ", OPD_RouteCode="
				+ OPD_RouteCode + ", OPD_Price=" + OPD_Price + "]";
	}
	
}