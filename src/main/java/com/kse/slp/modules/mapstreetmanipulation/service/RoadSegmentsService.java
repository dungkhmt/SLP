package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;

public interface RoadSegmentsService {
	List<RoadSegment> getList();
	public void saveARoadSegment(int code, int fromPoint, int toPoint, double distance, int speed, String bidi ); 
	public void deleteASegmentByCod(int code);
}
