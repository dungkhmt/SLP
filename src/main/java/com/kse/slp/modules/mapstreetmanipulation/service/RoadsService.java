package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.Road;

public interface RoadsService {
	public List<Road> getListByProvince(String proCode);
	public int saveARoad(String roadCode,String roadName,String RoadProvince,String RoadInterProvince,String RoadPoints, String RoadTypeCode,String RoadBidirectional,int RoadMaxSpeed,String RoadCreateUserID,String RoadCreateDateTime,String roadStatus );
	public Road loadARoadByRoadCode(String roadCode);
	public void updateARoad(String roadCode,Road road);
	public void updateARoad(String roadCode,String RoadPoints);
	public List<Road> getList();
	public List<Road> getListNotProccessed();
	public void updateStatusRoad(String roadCode, String status);
	public void removeARoad(String roadCode);
}
