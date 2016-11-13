package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.mapstreetmanipulation.dao.RoadPointsDAO;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
@Service("RoadPointsService")
public class RoadPointsServiceImpl implements RoadPointsService {
	@Autowired
	RoadPointsDAO roadPointDAO;
	@Override
	public List<RoadPoint> getList() {
		// TODO Auto-generated method stub
		return roadPointDAO.getList();
	}
	@Override
	public int saveARoadPoint(int rpCode, String rpLatLng,
			String provinceCode) {
		// TODO Auto-generated method stub
		RoadPoint road = new RoadPoint();
		road.setRP_Code(rpCode);
		road.setProvinceCode(provinceCode);
		road.setRP_LatLng(rpLatLng);
		int id = roadPointDAO.saveARoadPoint(road);
		road.setRP_Code(id);
		roadPointDAO.update(road);
		return id;
	}
	@Override
	public void removePointbyCode(int code) {
		// TODO Auto-generated method stub
		roadPointDAO.removePointbyCode(code);
	}
	@Override
	public RoadPoint getRoadPointbyCode(int Code) {
		// TODO Auto-generated method stub
		return roadPointDAO.getRoadPointbyCode(Code);
	}

}
