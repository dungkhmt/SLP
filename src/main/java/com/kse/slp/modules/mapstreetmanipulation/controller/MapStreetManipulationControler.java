package com.kse.slp.modules.mapstreetmanipulation.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.mapstreetmanipulation.service.ProvinceService;
import com.kse.slp.modules.mapstreetmanipulation.service.RoadsService;


@Controller("MapStreetManipulationControler")
@RequestMapping(value = {"/mapstreetmanipulation"})
public class MapStreetManipulationControler extends BaseWeb {
	private static final Logger log = Logger.getLogger(MapStreetManipulationControler.class);
	
	@Autowired
	RoadsService RoadsService;
	@Autowired
	ProvinceService ProvinceService;
	
	@RequestMapping(value="/editPoint",method=RequestMethod.GET)
	public String editPoint(){
		
	}
}
