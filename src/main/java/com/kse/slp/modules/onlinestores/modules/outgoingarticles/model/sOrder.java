package com.kse.slp.modules.onlinestores.modules.outgoingarticles.model;

import java.io.Serializable;


public class sOrder implements Serializable {
	private String date;
	private float total;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}

}
