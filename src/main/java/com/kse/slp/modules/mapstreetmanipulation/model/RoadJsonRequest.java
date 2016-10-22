package com.kse.slp.modules.mapstreetmanipulation.model;

import java.util.List;

public class RoadJsonRequest {
	String nameStreet;
	String roadType;
	String provice;
	String optionRoad;
	int maxSpeed;
	String ProvicesPass;
	List<Point> listPoint;
	
	public List<Point> getListPoint() {
		return listPoint;
	}
	public void setListPoint(List<Point> listPoint) {
		this.listPoint = listPoint;
	}
	public String getNameStreet() {
		return nameStreet;
	}
	public void setNameStreet(String nameStreet) {
		this.nameStreet = nameStreet;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	public String getOptionRoad() {
		return optionRoad;
	}
	public void setOptionRoad(String optionRoad) {
		this.optionRoad = optionRoad;
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public String getProvicesPass() {
		return ProvicesPass;
	}
	public void setProvicesPass(String provicesPass) {
		ProvicesPass = provicesPass;
	}
	@Override
	public String toString() {
		return "RoadJsonRequest [nameStreet=" + nameStreet + ", roadType="
				+ roadType + ", provice=" + provice + ", optionRoad="
				+ optionRoad + ", maxSpeed=" + maxSpeed + ", ProvicesPass="
				+ ProvicesPass + "]";
	}
	
}
