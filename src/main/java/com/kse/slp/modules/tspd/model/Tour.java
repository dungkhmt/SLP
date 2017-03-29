package com.kse.slp.modules.tspd.model;

import java.util.ArrayList;

public class Tour {
	private TruckTour TD;
	private ArrayList<DroneDelivery> DD;
	public TruckTour getTD() {
		return TD;
	}
	public void setTD(TruckTour tD) {
		TD = tD;
	}
	public ArrayList<DroneDelivery> getDD() {
		return DD;
	}
	public void setDD(ArrayList<DroneDelivery> dD) {
		DD = dD;
	}
	public Tour(TruckTour tD, ArrayList<DroneDelivery> dD) {
		super();
		TD = tD;
		DD = dD;
	}
	@Override
	public String toString() {
		return "Tour [TD=" + TD.toString() + ", DD=" + DD.toString() + "]";
	}
	
	
}
