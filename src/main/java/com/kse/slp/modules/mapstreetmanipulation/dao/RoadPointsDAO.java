package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;

public interface RoadPointsDAO {
	List<RoadPoint> getList();
	public int saveARoadPoint(RoadPoint point);
	public void update(RoadPoint point);
}
