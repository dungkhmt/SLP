package com.kse.slp.modules.mapstreetmanipulation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.mapstreetmanipulation.model.Province;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadJsonRequest;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadType;
import com.kse.slp.modules.usermanagement.model.User;
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
		RoadJsonRequest r= gson.fromJson(road,RoadJsonRequest.class);
		return null;
	}
	@RequestMapping(value="/editPoint",method=RequestMethod.GET)
	public String editPoint(ModelMap model){
		List<Province> lstProvinces = ProvinceService.getListProvince();
		model.put("lstProvinces", lstProvinces);
		
		return "mapstreetmanipulation.editPoint";
	}
}
