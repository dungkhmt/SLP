package com.kse.slp.modules.mapstreetmanipulation.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.print.attribute.standard.Destination;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.dichung.model.FormAddFileExcel;
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
import com.kse.slp.modules.utilities.gismap.googlemaps.GoogleMapsQuery;
import com.kse.slp.modules.utilities.shortestpathalgorithms.Arc;
import com.kse.slp.modules.utilities.shortestpathalgorithms.DijkstraBinaryHeap;
import com.kse.slp.modules.utilities.shortestpathalgorithms.Itinerary;
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
	
	
	public static DijkstraBinaryHeap dijkstra = null;
	public static HashMap<Integer, RoadPoint> mID2Point = null;
	
	public static List<RoadPoint> points = null;
	public static List<RoadSegment> segments = null;
	public static List<Road> roads = null;
	public static HashMap<String, Road> mCode2Road = null;
	
	private void initDijkstra(){
		if(dijkstra == null){
			GoogleMapsQuery G = new GoogleMapsQuery();
			mID2Point = new HashMap<Integer, RoadPoint>();
			mCode2Road = new HashMap<String, Road>();
			
			points = roadPointsService.getList();
			segments = roadSegmentsService.getList();
			roads = RoadsService.getList();
			
			for(Road r: roads){
				mCode2Road.put(r.getRoadCode(), r);
			}
			ArrayList<Integer> V = new ArrayList<Integer>();
			
			for(RoadPoint p: points){
				//System.out.println(name() + "::initDojkstra, point " + p.getRP_LatLng());
				V.add(p.getRP_ID());
				mID2Point.put(p.getRP_ID(), p);
			}
			ArrayList<Arc> Arcs = new ArrayList<Arc>();
			for(RoadSegment s: segments){
				Road r = mCode2Road.get(s.getRSEG_RoadCode());
				
				int u = s.getRSEG_FromPoint();
				int v = s.getRSEG_ToPoint();
				RoadPoint pu = mID2Point.get(u);
				RoadPoint pv = mID2Point.get(v);
				String[] s_latlng = pu.getRP_LatLng().split(",");
				double lat1 = Double.valueOf(s_latlng[0]);
				double lng1 = Double.valueOf(s_latlng[1]);
				
				s_latlng = pv.getRP_LatLng().split(",");
				double lat2 = Double.valueOf(s_latlng[0]);
				double lng2 = Double.valueOf(s_latlng[1]);
				
				double w = G.computeDistanceHaversine(lat1, lng1, lat2, lng2);
				Arc a = new Arc(u,v,w);
				Arcs.add(a);
				
				//System.out.println(name() + "::initDijkstra " + r.getRoadBidirectional());
				
				if(r.getRoadBidirectional().equals("BIDIRECTIONAL")){
					//System.out.println(name() + "::initDijkstra, BIDIRECTIONAL");
					a = new Arc(v,u,w);
					Arcs.add(a);
				}
			}
			HashMap<Integer, ArrayList<Arc>> A = new HashMap<Integer, ArrayList<Arc>>();
			for(int v: V){
				A.put(v, new ArrayList<Arc>());
			}
		
			for(Arc a: Arcs){
				int u = a.begin;
				ArrayList<Arc> Au = A.get(u);
				//if(Au == null) Au = new ArrayList<Arc>();
				Au.add(a);
				//A.put(u, Au);
			}
			dijkstra = new DijkstraBinaryHeap(V, A);
			
		}
	}
	private String jsonPoint(String latlng){
		String[] s = latlng.split(",");
		double lat = Double.valueOf(s[0]);
		double lng = Double.valueOf(s[1]);
		return "{\"lat\":" + lat + ",\"lng\":" + lng + "}";
	}
	public RoadPoint[] getKNearestPoint(String latlng, List<RoadPoint> points, int K){
		RoadPoint[] a = new RoadPoint[points.size()];
		double[] d = new double[points.size()];
		int n = a.length;
		for(int i = 0;i < points.size(); i++){
			a[i] = points.get(i);
			d[i] = GoogleMapsQuery.distanceHaversine(a[i].getRP_LatLng(), latlng);
		}
		// sort
		for(int i = 0; i < n-1; i++){
			for(int j = i+1; j < n; j++){
				if(d[i] > d[j]){
					double tmpd = d[i]; d[i] = d[j]; d[j] = tmpd;
					RoadPoint tmp = a[i]; a[i] = a[j]; a[j] = tmp;
				}
			}
		}
		RoadPoint[] sa = new RoadPoint[K];
		for(int i = 0;i < K; i++)
			sa[i] = a[i];
		return sa;
	}
	//public String findPath(double lat_start, double lng_start, double lat_end, double lng_end){
	public String findPath(String latlng_start, String latlng_end){
		
		RoadPoint s = null;
		RoadPoint t = null;
		Itinerary p = null;
		/*
		double minDisS = Integer.MAX_VALUE;
		double minDisT = Integer.MAX_VALUE;
		for(RoadPoint v: points){
			double d = GoogleMapsQuery.distanceHaversine(latlng_start,v.getRP_LatLng());
			if(minDisS > d){
				minDisS = d; s = v;// v.getRP_ID();
			}
			
			d = GoogleMapsQuery.distanceHaversine(latlng_end,v.getRP_LatLng());
			if(minDisT > d){
				minDisT = d; t = v;//v.getRP_ID();
			}
		}
		
		//System.out.println(name() + "::findPath, found s= " + s.getRP_ID() + ", t = " + t.getRP_ID());
		p = dijkstra.solve(s.getRP_ID(), t.getRP_ID());
		*/
		RoadPoint[] S = getKNearestPoint(latlng_start, points, 100);
		RoadPoint[] T = getKNearestPoint(latlng_end, points, 100);
		
		for(int i = 0; i < S.length; i++){
			for(int j = 0; j < T.length; j++){
				s = S[i];
				t = T[j];
				p = dijkstra.solve(s.getRP_ID(), t.getRP_ID());
				System.out.print(name() + "::findPath points.sz = " + points.size() + ", s = " + s.getRP_ID() + ", t = " + t.getRP_ID());
				if(p == null) System.out.println(" path = null"); else System.out.println(" path = " + p.size());
					
				if(p != null && p.size() > 0){
					break;
				}
			}
			if(p != null && p.size() > 0){
				break;
			}
		}
		
		String[] st = s.getRP_LatLng().split(",");
		double s_lat = Double.valueOf(st[0]);
		double s_lng = Double.valueOf(st[1]);
		st = t.getRP_LatLng().split(",");
		double t_lat = Double.valueOf(st[0]);
		double t_lng = Double.valueOf(st[1]);
		
		if(p == null){
			//System.out.println(name() + "::findPath, found s= " + s.getRP_ID() + ", t = " + t.getRP_ID() + ", p = null");
			String json = "{";
			
			json += "\"source\":{\"lat\":" + s_lat + ",\"lng\":" + s_lng + "},\"destination\":{\"lat\":" + t_lat + ",\"lng\":" + t_lng + "}";
			
			
			json += ",\"points\":[]}";
			//System.out.println("return json = " + json);
			return json;
		}
		//System.out.println(name() + "::findPath, found s= " + s + ", t = " + t + ", path = " + p.toString());
		String json = "{";
		
		json += "\"source\":\"" + s.getRP_LatLng() + "\"" + ",\"destination\":\"" + t.getRP_LatLng() + "\"";
		
		
		json += ",\"points\":[";
		for(int i = 0; i < p.size(); i++){
			int x = p.get(i);
			RoadPoint px = mID2Point.get(x);
			json += jsonPoint(px.getRP_LatLng());// px.getRP_LatLng();
			if(i < p.size()-1) json += ",";
		}
		json += "]}";
		//System.out.println(name() + "::findPath, json = " + json);
		return json;
	}
	private void testDijkstra(){
		Itinerary I = null;
		for(int i: dijkstra.getVertices()){
			for(int j: dijkstra.getVertices())if(i != j){
				I = dijkstra.solve(i, j);
				if(I != null) break;
			}
			if(I != null) break;
		}
		//System.out.println(name() + "::testDijkstra, I = " + I.toString());
	}
	@RequestMapping(value="/directionhome", method = RequestMethod.GET)
	public String directionhome(ModelMap model, HttpSession session){
		
		User u = (User)session.getAttribute("currentUser");
		log.info("directionhome, user = " + u.getUsername());
		try{
			//System.out.println("run command");
			Runtime.getRuntime().exec("C:/Program Files/R/R-3.3.2/bin/Rscript.exe C:/DungPQ/Projects/EVN/example.r");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		model.put("formAdd", new FormAddFileExcel());
		return "mapstreetmanipulation.directionhome";
	}
	@RequestMapping(value="/uploadexcel", method = RequestMethod.POST)
	public String uploadexcel(ModelMap model, HttpSession session,@ModelAttribute("formAdd") FormAddFileExcel request){
		MultipartFile file = request.getOrdersFile();
		User u = (User)session.getAttribute("currentUser");
		log.info("directionhome, user = " + u.getUsername());
		try{
			System.out.println("upload excel");
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("C:/DungPQ/Projects/EVN/copy-data.csv")));
			stream.write(bytes);
			stream.close();
			//System.out.println("upload excel");
			//Runtime.getRuntime().exec("C:/Program Files/R/R-3.3.2/bin/Rscript.exe C:/DungPQ/Projects/EVN/example.r");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "mapstreetmanipulation.directionhome";
	}
	
	@ResponseBody
	@RequestMapping(value="/direction", method = RequestMethod.POST)
	public String direction(HttpSession session,
			@RequestParam(value="info", defaultValue="0")String info){
		
		initDijkstra();
		
		JSONParser parser = new JSONParser();
		try{
			JSONObject obj = (JSONObject) parser.parse(info);
			String src = (String)obj.get("source");
			String dest = (String)obj.get("destination");
			src = src.trim();
			src = src.substring(1,src.length()-1);
			dest = dest.trim();
			dest = dest.substring(1,dest.length()-1);
			//System.out.println(name() + "::direction, source = " + src + ", destination = " + dest);
			return findPath(src, dest);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return "{}";
	}
	
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
		//System.out.println(name()+"updateRoad---roadPoints"+roadPoints);
		RoadsService.updateARoad(roadCode, roadPoints);
		return "400";
	}	
	
	@RequestMapping(value = "/viewStreets")
	public String viewStreets(ModelMap model){
		initDijkstra();
		testDijkstra();
		
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
		//count++;
		//System.out.println(name()+"findAndSaveIntersectionPoints, roadsCode = " + roadsCode + ", count = " + count + ", dijkstra = " + dijkstra.solve(1, 9).toString());
		
		if(roadsCode == null || roadsCode.equals("")) return "400";
		String roadCodes[] = roadsCode.split(";");
		//System.out.println(name()+"findAndSaveIntersectionPoints:: number of road find interect: "+roadCodes.length);
		for(int i=0; i<roadCodes.length; i++){
			Road road = RoadsService.loadARoadByRoadCode(roadCodes[i]);
			RoadsService.updateStatusRoad(roadCodes[i], "PROCCESSED");
			String[] points = road.getRoadPoints().split(":");
			//System.out.println(roadCodes[i]+"  total point in road: "+points.length);
			//System.out.println();
			//System.out.print("roadPoints: ");
			//for(int j = 0; j < points.length; j++){
				//System.out.print(points[j]+", ");
			//}
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
					//System.out.println(name() + "::findAndSaveIntersectionPoints, BUG at segment " + s.getRSEG_Code());
					System.exit(-1);
				}
			}
			
			if(roadPoints.size() == 0 || roadSegments.size() == 0){
				//System.out.println("Map null insert roadCode="+roadCodes[i]+" save to tables");
				int idFrom = roadPointsService.saveARoadPoint(0, points[0].trim(), road.getRoadProvince());
				//System.out.println(" idPoint[0]="+idFrom);
				for(int j=1; j< points.length; j++){
					if(points[j]== null || points[j].trim().equals("")) continue;
					
					int idTo = roadPointsService.saveARoadPoint(0, points[j].trim(), road.getRoadProvince());
					//System.out.println("idPoint["+j+"]="+idTo);
					//int index1 = points[j-1].indexOf(",");
					//int index2 = points[j].indexOf(",");
					//System.out.println("points["+(j-1)+"]="+points[j-1]+"  points["+j+"]="+points[j]);
					String[] s = points[j-1].split(",");
					
					double lat1 = Double.valueOf(s[0].trim());//Double.parseDouble(points[j-1].substring(0, index1));
					double long1 = Double.valueOf(s[1].trim());//Double.parseDouble(points[j-1].substring(index1+1,points[j-1].length()));
					s = points[j].split(",");
					double lat2 = Double.valueOf(s[0].trim());//Double.parseDouble(points[j].substring(0, index2));
					double long2 = Double.valueOf(s[1].trim());//Double.parseDouble(points[j].substring(index2+1, points[j].length()));
					
					Approximation ap = new Approximation();
					double distance = ap.computeDistanceHav(long1, lat1, long2, lat2);
					roadSegmentsService.saveARoadSegment(0, idFrom, idTo, distance, road.getRoadMaxSpeed(), road.getRoadBidirectional(), roadCodes[i]);
					idFrom = idTo;
				}
			}else{
				//System.out.println("Map != null insert roadCode="+roadCodes[i]+" save to tables");

				//System.out.println("road points[0]="+points[0]);

				int idFrom = roadPointsService.saveARoadPoint(0, points[0], road.getRoadProvince());
				//System.out.println(" roadPoint idPoint[0]="+idFrom);
				String pointFromLatLng = points[0].trim();
				for(int in = 1; in < points.length; in++){
					if(points[in]== null || points[in].trim().equals("")) continue;
					int indexCut = pointFromLatLng.indexOf(",");
					double lat = Double.parseDouble(pointFromLatLng.substring(0,indexCut));
					double lng = Double.parseDouble(pointFromLatLng.substring(indexCut+1, pointFromLatLng.length()));
					Point first = new Point(lat,lng);
					
					//System.out.println("road points["+in+"]="+points[in]);

					if(points[in-1].trim().equals(points[in].trim())){
						//System.out.println("BUG at line roadCode" + roadCodes[i]);
						System.exit(-1);
					}
					int idTo = roadPointsService.saveARoadPoint(0, points[in].trim(), road.getRoadProvince());
					//System.out.println(" roadPoint idPoint["+in+"]="+idTo);
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
							//System.out.println("intersection Point = "+pointLng);
							int idPoint = roadPointsService.saveARoadPoint(0, pointLng , road.getRoadProvince());
							//System.out.println(" intersectPoint idPoint="+idPoint);
							
							intersectPoints.add(new Pair(p, p.computeDistanceE(first), idPoint));
							
							Approximation ap = new Approximation();
							
							//System.out.println("segment intersect "+segment.getRSEG_Code());
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
							//System.out.println("distance intersection point[" +indexPoint+"]: "+intersectPoints.get(indexPoint).distanceFromFirst);
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
		System.out.println(name()+"findAndSaveIntersectionPoints--done");
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
	
	@RequestMapping(value = "/findIntersectionSegment",method = RequestMethod.POST)
	public @ResponseBody String findeIntersectionSegment(@RequestBody String data){
		System.out.println(name()+"findIntersectionSegment:: data recive"+data);
		String[] codes = data.split(";");
		
		int segmentEditedCode = Integer.parseInt(codes[0].trim());
		int pointEditedCode = Integer.parseInt(codes[1].trim());
		
		String proviceCode = roadPointsService.updateRoadPointByCode(pointEditedCode, codes[2]);
		
		RoadSegment segmentEdited = roadSegmentsService.getSegmentByCode(segmentEditedCode);
		roadSegmentsService.deleteASegmentByCode(segmentEditedCode);
		
		List<RoadPoint>  listPoints = roadPointsService.getList();
		HashMap<Integer, RoadPoint> mapID2Point = new HashMap<Integer, RoadPoint>();
		for(RoadPoint p : listPoints){
			mapID2Point.put(p.getRP_Code(), p);
		}
		
		int fromPointCode;
		int toPointCode;
		if(segmentEdited.getRSEG_FromPoint() == pointEditedCode){
			fromPointCode = segmentEdited.getRSEG_ToPoint();
			toPointCode = segmentEdited.getRSEG_FromPoint();
		}else{
			fromPointCode = segmentEdited.getRSEG_FromPoint();
			toPointCode = segmentEdited.getRSEG_ToPoint();
		}
		
		List<RoadSegment> listSegments = roadSegmentsService.getList();
		
		String fromPointLatLng = mapID2Point.get(fromPointCode).getRP_LatLng();
		String s[] = fromPointLatLng.split(",");
		double lat1 = Double.parseDouble(s[0]);
		double lng1 = Double.parseDouble(s[1]);
		Point first = new Point(lat1, lng1);
		
		String toPointLatLng = mapID2Point.get(toPointCode).getRP_LatLng();
		s = toPointLatLng.split(",");
		double lat2 = Double.parseDouble(s[0]);
		double lng2 = Double.parseDouble(s[1]);
		
		Line l1 = new Line(lng1, lat1, lng2, lat2);
		List<Pair> intersectPoints = new ArrayList<Pair>();
		
		for(RoadSegment segment : listSegments){
			int fromPointOfSegment = segment.getRSEG_FromPoint();
			int toPointOfSegment = segment.getRSEG_ToPoint();
			
			String fromPointLatLng1 = mapID2Point.get(fromPointOfSegment).getRP_LatLng();
			String s1[] = fromPointLatLng1.split(",");
			double fromPointLat = Double.parseDouble(s1[0]);
			double fromPointLng = Double.parseDouble(s1[1]);
			
			String toPointLatLng1 = mapID2Point.get(toPointOfSegment).getRP_LatLng();
			s1 = toPointLatLng1.split(",");
			double toPointLat = Double.parseDouble(s1[0]);
			double toPointLng = Double.parseDouble(s1[1]);
			
			Line l2 = new Line(fromPointLng, fromPointLat, toPointLng, toPointLat);
			Point p = new Point(1, 1);
			TWO_SEGMENTS_RELATION r = l1.intersectSegment(l2, p);
			if(r == TWO_SEGMENTS_RELATION.SEGMENT_INTERSECTIONAL){
				String pointLng = p.getdLat()+", "+p.getdLong();
				//System.out.println("intersection Point = "+pointLng);
				int idPoint = roadPointsService.saveARoadPoint(0, pointLng , proviceCode);
				//System.out.println(" intersectPoint idPoint="+idPoint);
				
				intersectPoints.add(new Pair(p, p.computeDistanceE(first), idPoint));
				
				Approximation ap = new Approximation();
				
				//System.out.println("segment intersect "+segment.getRSEG_Code());
				roadSegmentsService.deleteASegmentByCode(segment.getRSEG_Code());
				
				double distance1 = ap.computeDistanceHav(fromPointLng, fromPointLat, p.getdLong(), p.getdLat()); 
				roadSegmentsService.saveARoadSegment(0, fromPointOfSegment, idPoint, distance1,segment.getRSEG_Speed(), segment.getRSEG_Bidirectional(), segment.getRSEG_RoadCode());
				
				double distance2 = ap.computeDistanceHav(p.getdLong(), p.getdLat(), toPointLng, toPointLat);
				roadSegmentsService.saveARoadSegment(0, idPoint, toPointOfSegment, distance2, segment.getRSEG_Speed(), segment.getRSEG_Bidirectional(), segment.getRSEG_RoadCode());
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
			double distance = ap.computeDistanceHav(p0.p.getdLong(), p0.p.getdLat(), lng1,lat1);
			roadSegmentsService.saveARoadSegment(0, fromPointCode, p0.id, distance, segmentEdited.getRSEG_Speed(), segmentEdited.getRSEG_Bidirectional(), segmentEdited.getRSEG_RoadCode());
			for(int indexPoint = 1; indexPoint < intersectPoints.size(); indexPoint++){
				//System.out.println("distance intersection point[" +indexPoint+"]: "+intersectPoints.get(indexPoint).distanceFromFirst);
				Pair pi = intersectPoints.get(indexPoint);
				double distancei = ap.computeDistanceHav(p0.p.getdLong(), p0.p.getdLat(), pi.p.getdLong(), pi.p.getdLat());
				roadSegmentsService.saveARoadSegment(0, p0.id, pi.id,distancei , segmentEdited.getRSEG_Speed(), segmentEdited.getRSEG_Bidirectional(), segmentEdited.getRSEG_RoadCode());
				p0 = pi;
			}
			double distanceE = ap.computeDistanceHav(p0.p.getdLong(), p0.p.getdLat(), lng2, lat2);
			roadSegmentsService.saveARoadSegment(0, p0.id, toPointCode, distanceE,  segmentEdited.getRSEG_Speed(), segmentEdited.getRSEG_Bidirectional(), segmentEdited.getRSEG_RoadCode());
		}else{
			Approximation ap = new Approximation();
			double distance = ap.computeDistanceHav(lng1, lat1, lng2, lat2);
			roadSegmentsService.saveARoadSegment(0, fromPointCode, toPointCode, distance,segmentEdited.getRSEG_Speed(), segmentEdited.getRSEG_Bidirectional(), segmentEdited.getRSEG_RoadCode());
		}
		return "400";
	}
	
	@RequestMapping(value="/edit-road-points",method=RequestMethod.GET)
	public String editroadpoints(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "mapstreetmanipulation.editroadpoints";
	}
	String corectString(String s){
		String lS[]= s.split(":");
		/*String re="";
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
		}*/
		String re = "";
		for(int i=lS.length-1; i>0; i--){
			re += (lS[i]+":");
		}
		re += lS[0];
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
	@RequestMapping(value="/get-point-segment-all",method=RequestMethod.POST)
	public  @ResponseBody EditPointSegmentJsonRespone getPointSegmentAll(@RequestBody String jsonData,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		Gson gson= new Gson();
		EditPointSegmentJsonRespone ePJR = new EditPointSegmentJsonRespone();
		
		List<RoadSegment> lSg= roadSegmentsService.getList();
		HashSet<Integer> set = new HashSet<Integer>();
		HashMap<Integer, Integer> mapRP=new HashMap<Integer, Integer>();
		List<RoadPoint> lRP= new ArrayList<RoadPoint>();
		for(int i=0;i<lSg.size();i++){
			RoadPoint rP= roadPointsService.getRoadPointbyCode(lSg.get(i).getRSEG_FromPoint());
			RoadSegment rS=lSg.get(i);
			if(set.contains(rP.getRP_Code())){
				
				rS.setRSEG_FromPoint(mapRP.get(rP.getRP_Code()));
			} else {
				set.add(rP.getRP_Code());
				lRP.add(rP);
				mapRP.put(rP.getRP_Code(), lRP.size()-1);
				rS.setRSEG_FromPoint(lRP.size()-1);
			}
			
			rP= roadPointsService.getRoadPointbyCode(lSg.get(i).getRSEG_ToPoint());
			
			if(set.contains(rP.getRP_Code())){
				rS.setRSEG_ToPoint(mapRP.get(rP.getRP_Code()));
				
			} else {
				set.add(rP.getRP_Code());
				lRP.add(rP);
				mapRP.put(rP.getRP_Code(), lRP.size()-1);
				rS.setRSEG_ToPoint(lRP.size()-1);
			}
			
		}
		ePJR.setListRoadSegment(lSg);
		ePJR.setListRoadPoint(lRP);
		
		return ePJR;
	}
	@RequestMapping(value ="/merge-points")
	public @ResponseBody boolean mergePoints(@RequestBody String jsonData,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		Gson gson= new Gson();
		ArrayList<RoadPoint> lRP = (ArrayList<RoadPoint>) gson.fromJson(jsonData,new TypeToken<ArrayList<RoadPoint>>() {}.getType());
		System.out.println(name()+"mergePoints::"+ lRP);
		int pM=lRP.get(0).getRP_Code();
		System.out.println(name()+"mergePoints::"+"pM"+pM);
		for(int i=1;i<lRP.size();i++){
			System.out.println(name()+"mergePoints::"+lRP.get(i).getRP_Code());
			List<RoadSegment> lRS= roadSegmentsService.getListbyPoint(lRP.get(i).getRP_Code());
			System.out.println(name()+"mergePoints::"+lRS.size());
			for(int j=0;j<lRS.size();j++){
				RoadSegment rS=lRS.get(j);
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
