package com.kse.slp.modules.mapstreetmanipulation.controller;

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
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.mapstreetmanipulation.model.Province;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadJsonRequest;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadType;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;
import com.kse.slp.modules.mapstreetmanipulation.service.ProvinceService;
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
		System.out.print(name()+road);
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
		System.out.println(name() + "::saveARoad, start save to DB, road name = " + r.getNameStreet());
		RoadsService.saveARoad(roadCode, roadName, roadProvince, roadInterProvince, roadPoints, roadTypeCode, RoadBidirectional, roadMaxSpeed, u.getUsername(), roadCreateDateTime);
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
	
	String name(){
		return "mapstreetmanipulation::";
	}
}
