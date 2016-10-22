package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.dao.RoadsDAO;
import com.kse.slp.modules.mapstreetmanipulation.model.Road;
@Service("RoadsService")
public class RoadsServiceImpl  implements RoadsService {

	@Autowired
	RoadsDAO RoadsDAO;
	
	@Override
	public List<String> getListByProvince(String proCode) {
		// TODO Auto-generated method stub
		return RoadsDAO.getListByProvince(proCode);
	}

	@Override
	public int saveARoad(String roadCode, String roadName, String roadProvince,
			String roadInterProvince, String roadPoints, String roadTypeCode,
			String roadBidirectional, int roadMaxSpeed,
			String roadCreateUserID, String roadCreateDateTime) {
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
		return RoadsDAO;
	}

}
