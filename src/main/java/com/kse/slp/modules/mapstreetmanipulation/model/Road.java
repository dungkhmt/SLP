package com.kse.slp.modules.mapstreetmanipulation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblroads")
public class Road {
	@Id
	@GeneratedValue
	int RoadID;
	String RoadCode;
	String RoadName;
	String RoadProvince;
	String RoadInterProvince;
	String RoadPoints;
	String RoadTypeCode;
	String RoadBidirectional;
	int RoadMaxSpeed;
	String RoadCreateUserID;
	String RoadCreateDateTime;
	String RoadStatus;
	public int getRoadID() {
		return RoadID;
	}
	public void setRoadID(int roadID) {
		RoadID = roadID;
	}
	public String getRoadCode() {
		return RoadCode;
	}
	public void setRoadCode(String roadCode) {
		RoadCode = roadCode;
	}
	public String getRoadName() {
		return RoadName;
	}
	public void setRoadName(String roadName) {
		RoadName = roadName;
	}
	public String getRoadProvince() {
		return RoadProvince;
	}
	public void setRoadProvince(String roadProvince) {
		RoadProvince = roadProvince;
	}
	public String getRoadInterProvince() {
		return RoadInterProvince;
	}
	public void setRoadInterProvince(String roadInterProvince) {
		RoadInterProvince = roadInterProvince;
	}
	public String getRoadPoints() {
		return RoadPoints;
	}
	public void setRoadPoints(String roadPoints) {
		RoadPoints = roadPoints;
	}
	public String getRoadTypeCode() {
		return RoadTypeCode;
	}
	public void setRoadTypeCode(String roadTypeCode) {
		RoadTypeCode = roadTypeCode;
	}
	public String getRoadBidirectional() {
		return RoadBidirectional;
	}
	public void setRoadBidirectional(String roadBidirectional) {
		RoadBidirectional = roadBidirectional;
	}
	public int getRoadMaxSpeed() {
		return RoadMaxSpeed;
	}
	public void setRoadMaxSpeed(int roadMaxSpeed) {
		RoadMaxSpeed = roadMaxSpeed;
	}
	public String getRoadCreateUserID() {
		return RoadCreateUserID;
	}
	public void setRoadCreateUserID(String roadCreateUserID) {
		RoadCreateUserID = roadCreateUserID;
	}
	public String getRoadCreateDateTime() {
		return RoadCreateDateTime;
	}
	public void setRoadCreateDateTime(String roadCreateDateTime) {
		RoadCreateDateTime = roadCreateDateTime;
	}
	public String getRoadStatus() {
		return RoadStatus;
	}
	public void setRoadStatus(String roadStatus) {
		RoadStatus = roadStatus;
	}
	@Override
	public String toString() {
		return "Road [RoadID=" + RoadID + ", RoadCode=" + RoadCode
				+ ", RoadName=" + RoadName + ", RoadProvince=" + RoadProvince
				+ ", RoadInterProvince=" + RoadInterProvince + ", RoadPoints="
				+ RoadPoints + ", RoadTypeCode=" + RoadTypeCode
				+ ", RoadBidirectional=" + RoadBidirectional
				+ ", RoadMaxSpeed=" + RoadMaxSpeed + ", RoadCreateUserID="
				+ RoadCreateUserID + ", RoadCreateDateTime="
				+ RoadCreateDateTime + ", RoadStatus=" + RoadStatus + "]";
	}
	
}
