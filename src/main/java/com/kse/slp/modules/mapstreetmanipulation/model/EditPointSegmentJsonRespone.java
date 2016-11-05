package com.kse.slp.modules.mapstreetmanipulation.model;

import java.util.List;

public class EditPointSegmentJsonRespone {
	@Override
	public String toString() {
		return "EditPointSegmentJsonRespone [listRoadPoint=" + listRoadPoint
				+ ", listRoadSegment=" + listRoadSegment + "]";
	}
	List<RoadPoint> listRoadPoint;
	List<RoadSegment> listRoadSegment;
	public List<RoadPoint> getListRoadPoint() {
		return listRoadPoint;
	}
	public void setListRoadPoint(List<RoadPoint> listRoadPoint) {
		this.listRoadPoint = listRoadPoint;
	}
	public List<RoadSegment> getListRoadSegment() {
		return listRoadSegment;
	}
	public void setListRoadSegment(List<RoadSegment> listRoadSegment) {
		this.listRoadSegment = listRoadSegment;
	}
	
}
