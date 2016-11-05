package com.kse.slp.modules.mapstreetmanipulation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblroadsegments")
public class RoadSegment {
	@Id
	@GeneratedValue
	int RSEG_ID;
	int RSEG_Code;
	int RSEG_FromPoint;
	int RSEG_ToPoint;
	double RSEG_Distance;
	int RSEG_Speed;
	String RSEG_Bidirectional;
	String RSEG_RoadCode;
	
	public String getRSEG_RoadCode() {
		return RSEG_RoadCode;
	}
	public void setRSEG_RoadCode(String rSEG_RoadCode) {
		RSEG_RoadCode = rSEG_RoadCode;
	}
	public int getRSEG_ID() {
		return RSEG_ID;
	}
	public void setRSEG_ID(int rSEG_ID) {
		RSEG_ID = rSEG_ID;
	}
	public int getRSEG_Code() {
		return RSEG_Code;
	}
	public void setRSEG_Code(int rSEG_Code) {
		RSEG_Code = rSEG_Code;
	}
	public int getRSEG_FromPoint() {
		return RSEG_FromPoint;
	}
	public void setRSEG_FromPoint(int rSEG_FromPoint) {
		RSEG_FromPoint = rSEG_FromPoint;
	}
	public int getRSEG_ToPoint() {
		return RSEG_ToPoint;
	}
	public void setRSEG_ToPoint(int rSEG_ToPoint) {
		RSEG_ToPoint = rSEG_ToPoint;
	}
	public double getRSEG_Distance() {
		return RSEG_Distance;
	}
	public void setRSEG_Distance(double rSEG_Distance) {
		RSEG_Distance = rSEG_Distance;
	}
	public int getRSEG_Speed() {
		return RSEG_Speed;
	}
	public void setRSEG_Speed(int rSEG_Speed) {
		RSEG_Speed = rSEG_Speed;
	}
	public String getRSEG_Bidirectional() {
		return RSEG_Bidirectional;
	}
	public void setRSEG_Bidirectional(String rSEG_Bidirectional) {
		RSEG_Bidirectional = rSEG_Bidirectional;
	}
	@Override
	public String toString() {
		return "RoadSegment [RSEG_ID=" + RSEG_ID + ", RSEG_Code=" + RSEG_Code
				+ ", RSEG_FromPoint=" + RSEG_FromPoint + ", RSEG_ToPoint="
				+ RSEG_ToPoint + ", RSEG_Distance=" + RSEG_Distance
				+ ", RSEG_Speed=" + RSEG_Speed + ", RSEG_Bidirectional="
				+ RSEG_Bidirectional + ", RSEG_RoadCode=" + RSEG_RoadCode + "]";
	}
	
	

}
