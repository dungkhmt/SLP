package com.kse.slp.modules.onlinestores.modules.shippingmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mOrderDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRoutesService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;
import com.kse.slp.modules.usermanagement.model.User;

@Controller("ShippingController")
@RequestMapping(value={"/ship"})
public class ShippingController extends BaseWeb{
	
	@Autowired
	private mShippersService mShippersService;
	
	@Autowired
	private mOrdersService mOrdersService;
	
	@Autowired
	private mRoutesService mRoutesService;
	
	@Autowired
	private mRouteDetailService mRouteDetailService;
	
	@RequestMapping(value="/createRoute")
	public String creatRouteToShip(ModelMap map){
		
		List<mShippers> listShippers = mShippersService.getList();
		//List<mOrders> listOrders = mOrdersService.getList();
		List<mOrderDetail> listOrdersDetail = mOrdersService.getListOrderDetail();
		String listOrdersJson = new Gson().toJson(listOrdersDetail);
		String listShippersJson = new Gson().toJson(listShippers);
		System.out.println(name()+"::creatRouteToShip--listOrdersDetail"+listOrdersJson);
		System.out.println(name()+"::creatRouteToShip--listShippersJson"+listShippersJson);
		
		//map.put("nShippers", listShippers.size());
		//map.put("listOrdersDetail",listOrdersDetail);
		//map.put("listShippers", listShippers);
		map.put("listOrdersJson", listOrdersJson);
		map.put("listShippersJson", listShippersJson);
		
		return "ship.createRoute";
	}
	
	@RequestMapping(value="/getRoutes")
	public String loadRouteShiper(ModelMap map,HttpSession session){
		User user  =(User) session.getAttribute("currentUser");
		mShippers shipper= mShippersService.loadShiperByUserName(user.getUsername());
		System.out.println(name()+"loadRouteShiper "+user.getUsername()+" "+shipper.getSHP_Code());
		List<mRoutes> listRoutes= mRoutesService.loadRoutebyShipperCode("3");
		
		map.put("listRoutes", listRoutes);
		
		return "ship.getRoutes";
	}
	
	public String name(){
		return "ShippingController";
	}
	
}
