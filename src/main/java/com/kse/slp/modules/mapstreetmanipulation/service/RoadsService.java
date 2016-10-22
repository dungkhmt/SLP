package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

public interface RoadsService {
	public List<String> getListByProvince(String proCode);
	public int saveARoad(String roadCode,String roadName,String RoadProvince,String RoadInterProvince,String RoadPoints, String RoadTypeCode,String RoadBidirectional,int RoadMaxSpeed,String RoadCreateUserID,String RoadCreateDateTime );
}
