package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.Road;

public interface RoadsService {
	public List<Road> getListByProvince(String proCode);
	public int saveARoad(String roadCode,String roadName,String roadProvince,String roadInterProvince,String roadPoints, String roadTypeCode,String roadBidirectional,int roadMaxSpeed,String roadCreateUserID,String roadCreateDateTime );
}
