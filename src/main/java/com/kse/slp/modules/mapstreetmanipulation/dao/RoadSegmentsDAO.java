package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.Point;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;

public interface RoadSegmentsDAO {
	List<RoadSegment> getList();
	List<RoadSegment> getSegmentInRange(Point ne,Point sw);
	List<RoadSegment> getListbyPoint(int code);
	void updateASegment(RoadSegment rS);
}
