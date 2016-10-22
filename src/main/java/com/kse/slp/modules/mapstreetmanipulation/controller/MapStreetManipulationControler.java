package com.kse.slp.modules.mapstreetmanipulation.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kse.slp.controller.BaseWeb;


@Controller("MapStreetManipulationControler")
@RequestMapping(value = {"/mapstreetmanipulation"})
public class MapStreetManipulationControler extends BaseWeb {
	private static final Logger log = Logger.getLogger(MapStreetManipulationControler.class);
	
}
