package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;

public interface RoadSegmentsDAO {
	List<RoadSegment> getList();
	public int saveASegment(RoadSegment segment);
	public void updateASegment(RoadSegment segment);
	public void deleteSegmentByCode(int code);
}
