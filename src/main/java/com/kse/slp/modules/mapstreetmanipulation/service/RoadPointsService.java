package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;

public interface RoadPointsService {
	List<RoadPoint> getList();
	public int saveARoadPoint(int rpCode, String rpLatLng, String provinceCode);
	void removePointbyCode(int code);
	public String updateRoadPointByCode(int code, String latLng);
}
