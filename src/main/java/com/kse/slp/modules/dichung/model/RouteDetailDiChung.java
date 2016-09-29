package com.kse.slp.modules.dichung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblroutedetaildichung")
public class RouteDetailDiChung {
	@Id
	@GeneratedValue
	int RDDC_ID;
	String RDDC_RouteCode;
	String RDDC_TicketCode;
	int RDDC_Sequence;
	int RDDC_Group;
	String RDDC_Address;
	String RDDC_DistanceToNext;
	String RDDC_TravelTimeToNext;
	String 	RDDC_PickupDateTime;
	String RDDC_LatLng;
	
	public String getRDDC_PickupDateTime() {
		return RDDC_PickupDateTime;
	}
	public void setRDDC_PickupDateTime(String rDDC_PickupDateTime) {
		RDDC_PickupDateTime = rDDC_PickupDateTime;
	}
	public String getRDDC_LatLng() {
		return RDDC_LatLng;
	}
	public void setRDDC_LatLng(String rDDC_LatLng) {
		RDDC_LatLng = rDDC_LatLng;
	}
	public String getRDDC_Address() {
		return RDDC_Address;
	}
	public void setRDDC_Address(String rDDC_Address) {
		RDDC_Address = rDDC_Address;
	}
	public String getRDDC_DistanceToNext() {
		return RDDC_DistanceToNext;
	}
	public void setRDDC_DistanceToNext(String rDDC_DistanceToNext) {
		RDDC_DistanceToNext = rDDC_DistanceToNext;
	}
	public String getRDDC_TravelTimeToNext() {
		return RDDC_TravelTimeToNext;
	}
	public void setRDDC_TravelTimeToNext(String rDDC_TravelTimeToNext) {
		RDDC_TravelTimeToNext = rDDC_TravelTimeToNext;
	}
	public int getRDDC_ID() {
		return RDDC_ID;
	}
	public void setRDDC_ID(int rDDC_ID) {
		RDDC_ID = rDDC_ID;
	}
	public String getRDDC_RouteCode() {
		return RDDC_RouteCode;
	}
	public void setRDDC_RouteCode(String rDDC_RouteCode) {
		RDDC_RouteCode = rDDC_RouteCode;
	}
	public String getRDDC_TicketCode() {
		return RDDC_TicketCode;
	}
	public void setRDDC_TicketCode(String rDDC_TicketCode) {
		RDDC_TicketCode = rDDC_TicketCode;
	}
	public int getRDDC_Sequence() {
		return RDDC_Sequence;
	}
	public void setRDDC_Sequence(int rDDC_Sequence) {
		RDDC_Sequence = rDDC_Sequence;
	}
	public int getRDDC_Group() {
		return RDDC_Group;
	}
	public void setRDDC_Group(int rDDC_Group) {
		RDDC_Group = rDDC_Group;
	}
	
}
