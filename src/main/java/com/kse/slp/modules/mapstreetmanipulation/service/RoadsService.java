package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.Road;

public interface RoadsService {
	public List<Road> getListByProvince(String proCode);
	public int saveARoad(String roadCode,String roadName,String RoadProvince,String RoadInterProvince,String RoadPoints, String RoadTypeCode,String RoadBidirectional,int RoadMaxSpeed,String RoadCreateUserID,String RoadCreateDateTime );
}
