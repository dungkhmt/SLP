package com.kse.slp.modules.mapstreetmanipulation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblroadpoints")
public class RoadPoint {
	@Id
	@GeneratedValue
	int RP_ID;
	int RP_Code;
	String RP_LatLng;
	String ProvinceCode;
	public int getRP_ID() {
		return RP_ID;
	}
	public void setRP_ID(int rP_ID) {
		RP_ID = rP_ID;
	}
	public int getRP_Code() {
		return RP_Code;
	}
	public void setRP_Code(int rP_Code) {
		RP_Code = rP_Code;
	}
	public String getRP_LatLng() {
		return RP_LatLng;
	}
	public void setRP_LatLng(String rP_LatLng) {
		RP_LatLng = rP_LatLng;
	}
	public String getProvinceCode() {
		return ProvinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		ProvinceCode = provinceCode;
	}
	@Override
	public String toString() {
		return "RoadPoints [RP_ID=" + RP_ID + ", RP_Code=" + RP_Code
				+ ", RP_LatLng=" + RP_LatLng + ", ProvinceCode=" + ProvinceCode
				+ "]";
	}
	
}
