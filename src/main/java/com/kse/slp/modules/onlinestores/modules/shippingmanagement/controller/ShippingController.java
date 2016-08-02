package com.kse.slp.modules.onlinestores.modules.shippingmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;

@Controller("ShippingController")
@RequestMapping(value={"/ship"})
public class ShippingController extends BaseWeb{
	
	@Autowired
	private mShippersService mShippersService;
	
	@Autowired
	private mOrdersService mOrdersService;
	
	@RequestMapping(value="/createRoute")
	public String creatRouteToShip(ModelMap map){
		
		List<mShippers> listShippers = mShippersService.getList();
		List<mOrders> listOrders = mOrdersService.getList();
		String listOrdersJson = new Gson().toJson(listOrders);
		
		map.put("nShippers", listShippers.size());
		map.put("listShippers", listShippers);
		map.put("listOrdersJson", listOrdersJson);
		
		return "ship.createRoute";
	}
}
