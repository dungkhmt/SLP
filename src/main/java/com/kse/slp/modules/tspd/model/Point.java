package com.kse.slp.modules.tspd.model;

public class Point {
	private int ID;
	private double lat;
	private double lng;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public Point(int iD, double lat, double lng) {
		super();
		ID = iD;
		this.lat = lat;
		this.lng = lng;
	}
	public boolean equals(Point other){
		return other.getID() == this.ID;
	}
	@Override
	public String toString() {
		//return "Point [ID=" + ID + ", lat=" + lat + ", lng=" + lng + "]";
		return ""+ID;
	}
	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
