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
