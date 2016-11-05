package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;

public interface RoadPointsDAO {
	List<RoadPoint> getList();
	RoadPoint getRoadPointbyCode(int Code);
	public int saveARoadPoint(RoadPoint point);
	public void update(RoadPoint point);
}
