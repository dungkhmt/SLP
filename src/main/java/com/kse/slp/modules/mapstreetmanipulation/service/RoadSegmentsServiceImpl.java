package com.kse.slp.modules.mapstreetmanipulation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.mapstreetmanipulation.dao.RoadPointsDAO;
import com.kse.slp.modules.mapstreetmanipulation.dao.RoadSegmentsDAO;
import com.kse.slp.modules.mapstreetmanipulation.model.EditPointSegmentJsonRespone;
import com.kse.slp.modules.mapstreetmanipulation.model.Point;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;
@Service("RoadSegmentsService")
public class RoadSegmentsServiceImpl implements RoadSegmentsService {
	@Autowired
	RoadSegmentsDAO roadSegmentDAO;
	@Autowired
	RoadPointsDAO roadPointDAO;
	@Override
	public List<RoadSegment> getList() {
		// TODO Auto-generated method stub
		return roadSegmentDAO.getList();
	}
	@Override
	public void saveARoadSegment(int code, int fromPoint, int toPoint,
			double distance, int speed, String bidi,String roadCode) {
		// TODO Auto-generated method stub
		RoadSegment segment = new RoadSegment();
		segment.setRSEG_Bidirectional(bidi);
		segment.setRSEG_Code(code);
		segment.setRSEG_Distance(distance);
		segment.setRSEG_FromPoint(fromPoint);
		segment.setRSEG_Speed(speed);
		segment.setRSEG_ToPoint(toPoint);
		segment.setRSEG_RoadCode(roadCode);
		int id = roadSegmentDAO.saveASegment(segment);
		segment.setRSEG_Code(id);
		roadSegmentDAO.updateASegment(segment);
	}
	@Override
	public void deleteASegmentByCode(int code) {
		// TODO Auto-generated method stub
		roadSegmentDAO.deleteSegmentByCode(code);
	}
	@Override
	public EditPointSegmentJsonRespone getSegmentInRange(Point ne,
			Point sw) {
		// TODO Auto-generated method stub
		List<RoadSegment> lSg= roadSegmentDAO.getSegmentInRange(ne, sw);
		EditPointSegmentJsonRespone ePSJR= new EditPointSegmentJsonRespone();
		HashSet<Integer> set = new HashSet<Integer>();
		HashMap<Integer, Integer> mapRP=new HashMap<Integer, Integer>();
		List<RoadPoint> lRP= new ArrayList<RoadPoint>();
		for(int i=0;i<lSg.size();i++){
			RoadPoint rP= roadPointDAO.getRoadPointbyCode(lSg.get(i).getRSEG_FromPoint());
			RoadSegment rS=lSg.get(i);
			if(set.contains(rP.getRP_Code())){
				
				rS.setRSEG_FromPoint(mapRP.get(rP.getRP_Code()));
			} else {
				set.add(rP.getRP_Code());
				lRP.add(rP);
				mapRP.put(rP.getRP_Code(), lRP.size()-1);
				rS.setRSEG_FromPoint(lRP.size()-1);
			}
			
			rP= roadPointDAO.getRoadPointbyCode(lSg.get(i).getRSEG_ToPoint());
			
			if(set.contains(rP.getRP_Code())){
				rS.setRSEG_ToPoint(mapRP.get(rP.getRP_Code()));
				
			} else {
				set.add(rP.getRP_Code());
				lRP.add(rP);
				mapRP.put(rP.getRP_Code(), lRP.size()-1);
				rS.setRSEG_ToPoint(lRP.size()-1);
			}
			System.out.println(name()+mapRP);
		}
		ePSJR.setListRoadSegment(lSg);
		ePSJR.setListRoadPoint(lRP);
		return ePSJR;
	}
	String name(){
		return "RoadSegmentsService::";
	}
	@Override
	public List<RoadSegment> getListbyPoint(int code) {
		// TODO Auto-generated method stub
		return roadSegmentDAO.getListbyPoint(code);
	}
	@Override
	public void updateASegment(RoadSegment rS) {
		// TODO Auto-generated method stub
		roadSegmentDAO.updateASegment(rS);
	}
	@Override
	public RoadSegment getSegmentByCode(int code) {
		// TODO Auto-generated method stub
		return roadSegmentDAO.getSegmentByCode(code);
	}
	
}
