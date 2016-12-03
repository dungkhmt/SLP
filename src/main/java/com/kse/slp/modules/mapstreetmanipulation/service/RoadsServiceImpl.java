package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.dao.RoadsDAO;
import com.kse.slp.modules.mapstreetmanipulation.model.Road;

@Service("RoadsService")
public class RoadsServiceImpl implements RoadsService {

	@Autowired
	RoadsDAO RoadsDAO;
	
	@Override
	public List<Road> getListByProvince(String proCode) {
		// TODO Auto-generated method stub
		return RoadsDAO.getListByProvince(proCode);
	}

	@Override
	public int saveARoad(String roadCode, String roadName, String roadProvince,
			String roadInterProvince, String roadPoints, String roadTypeCode,
			String roadBidirectional, int roadMaxSpeed,
			String roadCreateUserID, String roadCreateDateTime,String roadStatus) {
		// TODO Auto-generated method stub
		Road r= new Road();
		r.setRoadName(roadName);
		r.setRoadCode(roadCode);
		r.setRoadProvince(roadProvince);
		r.setRoadPoints(roadPoints);
		r.setRoadInterProvince(roadInterProvince);
		r.setRoadTypeCode(roadTypeCode);
		r.setRoadBidirectional(roadBidirectional);
		r.setRoadMaxSpeed(roadMaxSpeed);
		r.setRoadCreateUserID(roadCreateUserID);
		r.setRoadCreateDateTime(roadCreateDateTime);
		r.setRoadStatus(roadStatus);
		return RoadsDAO.saveARoad(r);
	}

	@Override
	public Road loadARoadByRoadCode(String roadCode) {
		// TODO Auto-generated method stub
		return RoadsDAO.loadARoadByRoadCode(roadCode);
	}

	@Override
	public void updateARoad(String roadCode, Road r) {
		// TODO Auto-generated method stub
		Road road = RoadsDAO.loadARoadByRoadCode(roadCode);
		if(road != null){
			road.setRoadPoints(r.getRoadPoints());
			road.setRoadBidirectional(r.getRoadBidirectional());
			road.setRoadName(r.getRoadName());
			road.setRoadMaxSpeed(r.getRoadMaxSpeed());
			road.setRoadTypeCode(r.getRoadTypeCode());
			road.setRoadInterProvince(r.getRoadInterProvince());
			road.setRoadProvince(r.getRoadProvince());
			RoadsDAO.updateARoad(road);
		}
		
	}

	@Override
	public List<Road> getList() {
		// TODO Auto-generated method stub
		return RoadsDAO.getList();
	}

	@Override
	public List<Road> getListNotProccessed() {
		// TODO Auto-generated method stub
		return RoadsDAO.getListNotProccessed();
	}

	@Override
	public void updateStatusRoad(String roadCode, String status) {
		// TODO Auto-generated method stub
		Road road = RoadsDAO.loadARoadByRoadCode(roadCode);
		if(road != null){
			road.setRoadStatus(status);
			RoadsDAO.updateARoad(road);
		}
	}

	@Override
	public void updateARoad(String roadCode, String RoadPoints) {
		// TODO Auto-generated method stub
		Road road = RoadsDAO.loadARoadByRoadCode(roadCode);
		if(road != null){
			road.setRoadPoints(RoadPoints);
			RoadsDAO.updateARoad(road);
		}
	}

	@Override
	public void removeARoad(String roadCode) {
		// TODO Auto-generated method stub
		RoadsDAO.removeARoad(roadCode);
	}

}
