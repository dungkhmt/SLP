package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.Road;

public interface RoadsDAO {
	public List<Road> getListByProvince(String proCode);
	public int saveARoad(Road r);
	public void updateARoad(Road road);
	public Road loadARoadByRoadCode(String roadCode);
}
