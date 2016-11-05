package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.EditPointSegmentJsonRespone;
import com.kse.slp.modules.mapstreetmanipulation.model.Point;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;

public interface RoadSegmentsService {
	List<RoadSegment> getList();
	EditPointSegmentJsonRespone getSegmentInRange(Point ne,Point sw);
	List<RoadSegment> getListbyPoint(int code);
	void updateASegment(RoadSegment rS);
	public void saveARoadSegment(int code, int fromPoint, int toPoint, double distance, int speed, String bidi ); 
	public void saveARoadSegment(int code, int fromPoint, int toPoint, double distance, int speed, String bidi,String roadCode ); 
	public void deleteASegmentByCode(int code);
}
