package com.kse.slp.modules.mapstreetmanipulation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblroadtypes")
public class Roadtype {
	@Id
	@GeneratedValue
	int RoadTypeID;
	String RoadTypeCode;
	String RoadTypeName;
	public int getRoadTypeID() {
		return RoadTypeID;
	}
	public void setRoadTypeID(int roadTypeID) {
		RoadTypeID = roadTypeID;
	}
	public String getRoadTypeCode() {
		return RoadTypeCode;
	}
	public void setRoadTypeCode(String roadTypeCode) {
		RoadTypeCode = roadTypeCode;
	}
	public String getRoadTypeName() {
		return RoadTypeName;
	}
	public void setRoadTypeName(String roadTypeName) {
		RoadTypeName = roadTypeName;
	}
	
}
