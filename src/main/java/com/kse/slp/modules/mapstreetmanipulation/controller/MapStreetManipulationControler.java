package com.kse.slp.modules.mapstreetmanipulation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.mapstreetmanipulation.model.Province;
import com.kse.slp.modules.mapstreetmanipulation.model.Road;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.mapstreetmanipulation.model.EditPointSegmentJsonRespone;
import com.kse.slp.modules.mapstreetmanipulation.model.Province;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadJsonRequest;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadType;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;
import com.kse.slp.modules.utilities.gismap.Approximation;
import com.kse.slp.modules.utilities.gismap.Line;
import com.kse.slp.modules.utilities.gismap.Point;
import com.kse.slp.modules.utilities.gismap.TWO_SEGMENTS_RELATION;
import com.kse.slp.modules.mapstreetmanipulation.service.ProvinceService;
import com.kse.slp.modules.mapstreetmanipulation.service.RoadPointsService;
import com.kse.slp.modules.mapstreetmanipulation.service.RoadSegmentsService;
import com.kse.slp.modules.mapstreetmanipulation.service.RoadTypeService;
import com.kse.slp.modules.mapstreetmanipulation.service.RoadsService;


@Controller("MapStreetManipulationControler")
@RequestMapping(value = {"/mapstreetmanipulation"})
public class MapStreetManipulationControler extends BaseWeb {
	private static final Logger log = Logger.getLogger(MapStreetManipulationControler.class);
	
	@Autowired
	RoadsService RoadsService;
	@Autowired
	ProvinceService ProvinceService;
	@Autowired
	RoadTypeService roadTypeService;
	@Autowired
	RoadPointsService roadPointsService;
	@Autowired
	RoadSegmentsService roadSegmentsService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listPickupDelivery(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "mapstreetmanipulation.home";
	}
	
	@RequestMapping(value="/create-road",method=RequestMethod.GET)
	public String createRoad(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		List<RoadType> lRT= roadTypeService.getListRoadType();
		List<Province> lPrv=ProvinceService.getListProvince();
		model.put("listRoadType", lRT);
		model.put("listProvince", lPrv);
		return "mapstreetmanipulation.createroad";
	}
	
	@ResponseBody @RequestMapping(value="/save-A-Road", method=RequestMethod.POST)
	public boolean saveARoad(HttpSession session,@RequestBody String road){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		Gson gson= new Gson();
		//System.out.print(name()+road);
		RoadJsonRequest r= gson.fromJson(road,RoadJsonRequest.class);
		String roadCode= "ROAD"+GenerationDateTimeFormat.genDateTimeFormatStandardCurrently();
		String roadName= r.getNameStreet();
		String roadProvince=r.getProvice();
		String roadInterProvince=r.getProvicesPass();
		String roadTypeCode=r.getRoadType();
		int roadMaxSpeed=r.getMaxSpeed();
		String roadCreateDateTime= GenerationDateTimeFormat.genDateTimeFormatyyyy_MM_ddhhMMssCurrently();
		String roadPoints="";
		if(r.getListPoint() != null && r.getListPoint().size() > 0){
		for(int i=0;i< r.getListPoint().size()-1;i++){
			roadPoints+=r.getListPoint().get(i).getLat()+", "+r.getListPoint().get(i).getLng()+" : ";
		}
		roadPoints+=r.getListPoint().get(r.getListPoint().size()-1).getLat()+", "+r.getListPoint().get(r.getListPoint().size()-1).getLng();
		}
		
		String RoadBidirectional=r.getOptionRoad();
		//System.out.println(name() + "::saveARoad, start save to DB, road name = " + r.getNameStreet());
		RoadsService.saveARoad(roadCode, roadName, roadProvince, roadInterProvince, roadPoints, roadTypeCode, RoadBidirectional, roadMaxSpeed, u.getUsername(), roadCreateDateTime, "NOT_PROCCESSED");
		return true;
	}
	@RequestMapping(value="/editPoint",method=RequestMethod.GET)
	public String editPoint(ModelMap model){
		List<Province> lstProvinces = ProvinceService.getListProvince();
		model.put("lstProvinces", lstProvinces);
		
		return "mapstreetmanipulation.editPoint";
	}
	
	@RequestMapping(value="/getListStreetName/{code}")
	public @ResponseBody List<Road> getListStreetName(@PathVariable("code") String provinceCode){
		List<Road> roads = RoadsService.getListByProvince(provinceCode);
		return roads;
	}
	
	@RequestMapping(value="/updateRoad/{code}")
	public @ResponseBody String updateRoad(@PathVariable("code") String roadCode, @RequestBody String roadPoints){
		System.out.println(name()+"updateRoad---roadPoints"+roadPoints);
		RoadsService.updateARoad(roadCode, roadPoints);
		return "400";
	}	
	
	@RequestMapping(value = "/viewStreets")
	public String viewStreets(ModelMap model){
		List<Province> lstProvinces = ProvinceService.getListProvince();
		model.put("lstProvinces", lstProvinces);
		
		List<Road>  lstRoads = RoadsService.getList();
		model.put("lstRoads", lstRoads);
		
		String jsonRoads = new Gson().toJson(lstRoads);
		model.put("jsonRoads",jsonRoads);
		
		return "mapstreetmanipulation.viewStreets";
	}
	
	@RequestMapping(value="/findIntersectionPoints")
	public String findPoints(ModelMap model){
		List<Road> listRoads = RoadsService.getListNotProccessed();
		List<RoadPoint> lstRoadPoints = roadPointsService.getList();
		List<RoadSegment> lstRoadSegments = roadSegmentsService.getList();
		
		String jsonRoadPoints = new Gson().toJson(lstRoadPoints);
		String jsonRoadSegments = new Gson().toJson(lstRoadSegments);
		String jsonRoads = new Gson().toJson(listRoads);
		
		model.put("listRoads", listRoads);
		model.put("jsonRoadPoints",jsonRoadPoints);
		model.put("jsonRoadSegments",jsonRoadSegments);
		model.put("jsonRoads",jsonRoads);
		
		return "mapstreetmanipulation.findIntersectionPoints";
	}
	
	@RequestMapping(value="/findAndSaveIntersectionPoints")
	public @ResponseBody String findAndSaveIntersectionPoints(@RequestBody String roadsCode){
		count++;
		System.out.println(name()+"findAndSaveIntersectionPoints, roadsCode = " + roadsCode + ", count = " + count + ", dijkstra = " + dijkstra.solve(1, 9).toString());
		
		if(roadsCode == null || roadsCode.equals("")) return "400";
		String roadCodes[] = roadsCode.split(";");
		for(int i=0; i<roadCodes.length; i++){
			Road road = RoadsService.loadARoadByRoadCode(roadCodes[i]);
			RoadsService.updateStatusRoad(roadCodes[i], "PROCCESSED");
			String[] points = road.getRoadPoints().split(":");
			System.out.print("roadPoitns: ");
			for(int j = 0; j < points.length; j++){
				System.out.println(points[j]+", ");
			}

			//for(int j=0; j < points.length; j++){
				
			//}

			List<RoadPoint> roadPoints = roadPointsService.getList();
			List<RoadSegment> roadSegments = roadSegmentsService.getList();
			
			HashMap<Integer, RoadPoint> mID2Point = new HashMap<Integer, RoadPoint>();
			for(RoadPoint p: roadPoints){
				mID2Point.put(p.getRP_ID(), p);
			}
			for(RoadSegment s: roadSegments){
				if(mID2Point.get(s.getRSEG_FromPoint()) == null || mID2Point.get(s.getRSEG_ToPoint())==null){
					System.out.println(name() + "::findAndSaveIntersectionPoints, BUG at segment " + s.getRSEG_Code());
					System.exit(-1);
				}
			}
			
			if(roadPoints.size() == 0 || roadSegments.size() == 0){
				System.out.println("Map null insert roadCode="+roadCodes[i]+" save to tables");
				int idFrom = roadPointsService.saveARoadPoint(0, points[0], road.getRoadProvince());
				System.out.println(" idPoint[0]="+idFrom);
				for(int j=1; j< points.length; j++){
					if(points[j]== null || points[j].trim().equals("")) continue;
					
					int idTo = roadPointsService.saveARoadPoint(0, points[j], road.getRoadProvince());
					System.out.println("idPoint["+j+"]="+idTo);
					int index1 = points[j-1].indexOf(",");
					int index2 = points[j].indexOf(",");
					System.out.println("points["+(j-1)+"]="+points[j-1]+"  points["+j+"]="+points[j]);
					String[] s = points[j-1].split(",");
					
					double lat1 = Double.valueOf(s[0]);//Double.parseDouble(points[j-1].substring(0, index1));
					double long1 = Double.valueOf(s[1]);//Double.parseDouble(points[j-1].substring(index1+1,points[j-1].length()));
					s = points[j].split(",");
					double lat2 = Double.valueOf(s[0]);//Double.parseDouble(points[j].substring(0, index2));
					double long2 = Double.valueOf(s[1]);//Double.parseDouble(points[j].substring(index2+1, points[j].length()));
					
					Approximation ap = new Approximation();
					double distance = ap.computeDistanceHav(long1, lat1, long2, lat2);
					roadSegmentsService.saveARoadSegment(0, idFrom, idTo, distance, road.getRoadMaxSpeed(), road.getRoadBidirectional(), roadCodes[i]);
					idFrom = idTo;
				}
			}else{
				System.out.println("Map != null insert roadCode="+roadCodes[i]+" save to tables");

				System.out.println("road points[0]="+points[0]);

				int idFrom = roadPointsService.saveARoadPoint(0, points[0], road.getRoadProvince());
				System.out.println(" roadPoint idPoint[0]="+idFrom);
				String pointFromLatLng = points[0];
				for(int in = 1; in < points.length; in++){
					if(points[in]== null || points[in].trim().equals("")) continue;
					int indexCut = pointFromLatLng.indexOf(",");
					double lat = Double.parseDouble(pointFromLatLng.substring(0,indexCut));
					double lng = Double.parseDouble(pointFromLatLng.substring(indexCut+1, pointFromLatLng.length()));
					Point first = new Point(lat,lng);
					
					System.out.println("road points["+in+"]="+points[in]);

					if(points[in-1].trim().equals(points[in].trim())){
						System.out.println("BUG at line roadCode" + roadCodes[i]);
						System.exit(-1);
					}
					int idTo = roadPointsService.saveARoadPoint(0, points[in], road.getRoadProvince());
					System.out.println(" roadPoint idPoint["+in+"]="+idTo);
					int index1 = points[in-1].indexOf(",");
					int index2 = points[in].indexOf(",");
					
					double lat1 = Double.parseDouble(points[in-1].substring(0, index1));
					double long1 = Double.parseDouble(points[in-1].substring(index1+1,points[in-1].length()));
					double lat2 = Double.parseDouble(points[in].substring(0, index2));
					double long2 = Double.parseDouble(points[in].substring(index2+1, points[in].length()));
					Line l1 = new Line(long1, lat1, long2,lat2);
					
					List<Pair> intersectPoints = new ArrayList<Pair>();
					for(int j=0; j < roadSegments.size(); j++){
						RoadSegment segment = roadSegments.get(j);
						
						//find lat and lng of fromPoint and to point  
						int fromPoint = segment.getRSEG_FromPoint();
						int toPoint = segment.getRSEG_ToPoint();
						RoadPoint from = mID2Point.get(segment.getRSEG_FromPoint());
						RoadPoint to = mID2Point.get(segment.getRSEG_ToPoint());
						
						String fromPointLatLng = from.getRP_LatLng();
						String toPointLatLng = to.getRP_LatLng();
						
						//if(from == null || to == null){
							
						//}
						/*
						for(int k=0; k<roadPoints.size(); k++){
							RoadPoint point = roadPoints.get(k);
							if(point.getRP_Code() == fromPoint){
								fromPointLatLng = point.getRP_LatLng();
							}
							if(point.getRP_Code() == toPoint){
								toPointLatLng = point.getRP_LatLng();
							}
							if(fromPointLatLng != null && toPointLatLng != null){
								break;
							}
						}
						*/
						
						
						//get lat and lng
						int tmpIndex1 = fromPointLatLng.indexOf(",");
						int tmpIndex2 = toPointLatLng.indexOf(",");
						
						double fromPointLat = Double.parseDouble(fromPointLatLng.substring(0, tmpIndex1));
						double fromPointLng = Double.parseDouble(fromPointLatLng.substring(tmpIndex1+1, fromPointLatLng.length()));
						double toPointLat = Double.parseDouble(toPointLatLng.substring(0, tmpIndex2));
						double toPointLng = Double.parseDouble(toPointLatLng.substring(tmpIndex2+1, toPointLatLng.length())); 
						
						//
						Line l2 = new Line(fromPointLng, fromPointLat, toPointLng,toPointLat);
						Point p = new Point(1, 1);
						
						//find intersection point
						TWO_SEGMENTS_RELATION r = l1.intersectSegment(l2, p);
						if(r == TWO_SEGMENTS_RELATION.SEGMENT_INTERSECTIONAL){
							String pointLng = p.getdLat()+", "+p.getdLong();
							System.out.println("intersection Point = "+pointLng);
							int idPoint = roadPointsService.saveARoadPoint(0, pointLng , road.getRoadProvince());
							System.out.println(" intersectPoint idPoint="+idPoint);
							
							intersectPoints.add(new Pair(p, p.computeDistanceE(first), idPoint));
							
							Approximation ap = new Approximation();
							
							System.out.println("segment intersect "+segment.getRSEG_Code());
							roadSegmentsService.deleteASegmentByCode(segment.getRSEG_Code());
							
							double distance1 = ap.computeDistanceHav(fromPointLng, fromPointLat, p.getdLong(), p.getdLat()); 
							roadSegmentsService.saveARoadSegment(0, fromPoint, idPoint, distance1,segment.getRSEG_Speed(), segment.getRSEG_Bidirectional(), segment.getRSEG_RoadCode());
							
							double distance2 = ap.computeDistanceHav(p.getdLong(), p.getdLat(), toPointLng, toPointLat);
							roadSegmentsService.saveARoadSegment(0, idPoint, toPoint, distance2, segment.getRSEG_Speed(), segment.getRSEG_Bidirectional(), segment.getRSEG_RoadCode());
						}
					}
					
					
					if(intersectPoints.size() != 0){
						Collections.sort(intersectPoints, new Comparator<Pair>() {
							@Override
							public int compare(Pair p1, Pair p2){
								return p1.distanceFromFirst.compareTo(p2.distanceFromFirst);
							}
						});
						
						Pair p0 = intersectPoints.get(0);
						Approximation ap = new Approximation();
						double distance = ap.computeDistanceHav(p0.p.getdLong(), p0.p.getdLat(), lng, lat);
						roadSegmentsService.saveARoadSegment(0, idFrom, p0.id, distance, road.getRoadMaxSpeed(), road.getRoadBidirectional(), roadCodes[i]);
						for(int indexPoint = 1; indexPoint < intersectPoints.size(); indexPoint++){
							System.out.println("distance intersection point[" +indexPoint+"]: "+intersectPoints.get(indexPoint).distanceFromFirst);
							Pair pi = intersectPoints.get(indexPoint);
							double distancei = ap.computeDistanceHav(p0.p.getdLong(), p0.p.getdLat(), pi.p.getdLong(), pi.p.getdLat());
							roadSegmentsService.saveARoadSegment(0, p0.id, pi.id,distancei , road.getRoadMaxSpeed(), road.getRoadBidirectional(), roadCodes[i]);
							p0 = pi;
						}
						double distanceE = ap.computeDistanceHav(p0.p.getdLong(), p0.p.getdLat(), long2, lat2);
						roadSegmentsService.saveARoadSegment(0, p0.id, idTo, distanceE, road.getRoadMaxSpeed(), road.getRoadBidirectional(), roadCodes[i]);
					}else{
						Approximation ap = new Approximation();
						double distance = ap.computeDistanceHav(long1, lat1, long2, lat2);
						roadSegmentsService.saveARoadSegment(0, idFrom, idTo, distance,road.getRoadMaxSpeed(), road.getRoadBidirectional(), roadCodes[i]);
					}
					
					idFrom = idTo;
					pointFromLatLng = points[in];
				}
			}
		}
		return "400";	
	}
	
	class Pair{
		Point p;
		Double distanceFromFirst;
		int id;
		
		Pair(Point p, double distance,int id){
			this.p = p;
			this.distanceFromFirst = distance;
			this.id = id;
		}
		
		void setDistance(double distance){
			this.distanceFromFirst = distance;
		}
	}
	
	@RequestMapping(value="/edit-road-points",method=RequestMethod.GET)
	public String editroadpoints(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "mapstreetmanipulation.editroadpoints";
	}
	String corectString(String s){
		String lS[]= s.split(":");
		String re="";
		String last = "";
		for(int i=0;i<lS.length;i++){
			System.out.println(name()+re+"-- "+last+" "+lS[i]);
			if(lS[i].contains("NaN")|| (lS[i].trim().equals("") || (lS[i]==null))) continue;
			//if(lS[i].trim().equals(last.trim())) continue;
			if(re.equals("")){
				re=re+lS[i];
				last = lS[i];
				continue;
			}
			System.out.println(name()+"add");
			re=re+":"+lS[i];
			last = lS[i];
		}
		
		return re;
	}
	
	@RequestMapping(value="/fix")
	public void fixBug(){
		List<Road> lR= RoadsService.getList();
		for(int i=0;i<lR.size();i++){
			Road r= lR.get(i);
			String s=r.getRoadPoints();
			RoadsService.updateARoad(r.getRoadCode(), corectString(s));
		}
	}
	@RequestMapping(value="/get-point-segment-in-range",method=RequestMethod.POST)
	public  @ResponseBody EditPointSegmentJsonRespone getPointSegmentInRange(@RequestBody String jsonData,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		Gson gson= new Gson();
		ArrayList<com.kse.slp.modules.mapstreetmanipulation.model.Point> listPoint = (ArrayList<com.kse.slp.modules.mapstreetmanipulation.model.Point>) gson.fromJson(jsonData,new TypeToken<ArrayList<com.kse.slp.modules.mapstreetmanipulation.model.Point>>() {}.getType());
		EditPointSegmentJsonRespone ePJR = roadSegmentsService.getSegmentInRange(listPoint.get(0),listPoint.get(1));
		System.out.println(name()+ePJR);
		return ePJR;
	}
	
	@RequestMapping(value ="/merge-points")
	public @ResponseBody boolean mergePoints(@RequestBody String jsonData,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		Gson gson= new Gson();
		ArrayList<RoadPoint> lRP = (ArrayList<RoadPoint>) gson.fromJson(jsonData,new TypeToken<ArrayList<RoadPoint>>() {}.getType());
		System.out.println(name()+ lRP);
		int pM=lRP.get(0).getRP_Code();
		System.out.println(name()+"pM"+pM);
		for(int i=1;i<lRP.size();i++){
			System.out.println(name()+"zzz"+lRP.get(i).getRP_Code());
			List<RoadSegment> lRS= roadSegmentsService.getListbyPoint(lRP.get(i).getRP_Code());
			System.out.println(name()+"getListbyPoint size"+lRS.size());
			for(int j=0;j<lRS.size();j++){
				RoadSegment rS=lRS.get(j);
				System.out.println(name()+" rS "+rS);
				if(rS.getRSEG_FromPoint()==lRP.get(i).getRP_Code())  {
					rS.setRSEG_FromPoint(pM);
				} else  {
					rS.setRSEG_ToPoint(pM);
				}
				if(rS.getRSEG_FromPoint()==rS.getRSEG_ToPoint()){
					roadSegmentsService.deleteASegmentByCode(rS.getRSEG_Code());
					
				} else roadSegmentsService.updateASegment(rS);
			}
			roadPointsService.removePointbyCode(lRP.get(i).getRP_Code());
		}
		return true;
	}
	String name(){
		return "mapstreetmanipulation::";
	}
}
