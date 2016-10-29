package com.kse.slp.modules.utilities;

public class Point {
	private double lat;
	private double lng;
	
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
	
	public Point(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}
}
