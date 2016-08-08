package com.kse.slp.modules.onlinestores.modules.shippingmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.controller.IncomingArticlesController;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.lstJSONResquestCreateRoute;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mJSONRequestCreateRoute;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mJSONResponseToCreateRoute;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mOrderDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteUnderCreation;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRoutesService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;
import com.kse.slp.modules.usermanagement.model.User;

@Controller("ShippingController")
@RequestMapping(value={"/ship"})
public class ShippingController extends BaseWeb{
	private static final Logger log = Logger.getLogger(ShippingController.class);
	@Autowired
	private mShippersService mShippersService;
	
	@Autowired
	private mOrdersService mOrdersService;
	
	@Autowired
	private mRoutesService mRoutesService;
	
	@Autowired
	private mRouteDetailService mRouteDetailService;
	
	@RequestMapping(value="/createRoute")
	public String creatRouteToShip(ModelMap map,HttpSession session){
		
		//List<mShippers> listShippers = mShippersService.getList();
		//List<mOrders> listOrders = mOrdersService.getList();
		//List<mOrderDetail> listOrdersDetail = mOrdersService.getListOrderDetail();
		//String listOrdersJson = new Gson().toJson(listOrdersDetail);
		//String listShippersJson = new Gson().toJson(listShippers);
		//System.out.println(name()+"::creatRouteToShip--listOrdersDetail"+listOrdersJson);
		//System.out.println(name()+"::creatRouteToShip--listShippersJson"+listShippersJson);
		List<String> listOrderDueDate = mOrdersService.getListDueDate();
		System.out.println(name()+"::creatRouteToShip--listOrderDueDate: "+listOrderDueDate.toString());
		//map.put("nShippers", listShippers.size());
		//map.put("listOrdersDetail",listOrdersDetail);
		//map.put("listShippers", listShippers);
		//map.put("listOrdersJson", listOrdersJson);
		//map.put("listShippersJson", listShippersJson);
		map.put("listOrderDueDate", listOrderDueDate);

		
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "ship.createRoute";
	}
	
	@RequestMapping(value="/viewAssignedRoute", method = RequestMethod.POST)
	public @ResponseBody mJSONResponseToCreateRoute viewAssignedRoute(@RequestBody String date){
		System.out.println(name()+"::viewAssignedRoute--request text:"+date);
		List<mOrders> lstOrders = mOrdersService.getListOrderByDueDate(date);
		List<mRouteUnderCreation> lstRTUnCreation = mRoutesService.getLstRTUnderCreation();
		List<mShippers> lstShippers = mShippersService.getList();
	
		mJSONResponseToCreateRoute jsonReturn = new mJSONResponseToCreateRoute();
		jsonReturn.setLstOrders(lstOrders);
		jsonReturn.setLstRTUnCreation(lstRTUnCreation);
		jsonReturn.setLstShippers(lstShippers);
		
		return jsonReturn;
	}
	
	@RequestMapping(value="/saveRouteCreated/{method}", method = RequestMethod.POST)
	public @ResponseBody String saveRouteCreated (@PathVariable("method") String method, @RequestBody lstJSONResquestCreateRoute request){
		System.out.println(name()+"::saveRouteCreated");
		
		System.out.println(name()+"::saveRouteCreated--(method: "+method+")request data"+request.toString());
		List<mJSONRequestCreateRoute>  lstResRoute= request.getLstJSONresroute();
		
		if(method.equals("save")){
			System.out.println(name()+"::saveRouteCreated method = save");
			for(int i=0; i<lstResRoute.size(); i++){
				String route_Code = lstResRoute.get(i).getRoute_Code();
				String route_Shipper_Code = lstResRoute.get(i).getShipper_Code();
				String route_Start_Time = lstResRoute.get(i).getRoute_Start_Time();
				String[] orders_in_route = lstResRoute.get(i).getOrders_In_Route();
				for(int j=0; j<orders_in_route.length;j++){
					String orderCode = orders_in_route[i];
				}
			}
		}else if(method.equals("confirm")){
			System.out.println(name()+"::saveRouteCreated method = save");
			for(int i=0; i<lstResRoute.size(); i++){
				String route_Code = lstResRoute.get(i).getRoute_Code();
				String route_Shipper_Code = lstResRoute.get(i).getShipper_Code();
				String route_Start_Time = lstResRoute.get(i).getRoute_Start_Time();
				String[] orders_in_route = lstResRoute.get(i).getOrders_In_Route();
				for(int j=0; j<orders_in_route.length;j++){
					String orderCode = orders_in_route[i];
				}
			}
		}
		
		return "400";
	}
	
	@RequestMapping(value="/getRoutes")
	public String loadRouteShiper(ModelMap map,HttpSession session){
		User user  =(User) session.getAttribute("currentUser");
		mShippers shipper= mShippersService.loadShiperByUserName(user.getUsername());
		System.out.println(name()+"loadRouteShiper "+user.getUsername()+" "+shipper.getSHP_Code());
		List<mRoutes> listRoutes= mRoutesService.loadRoutebyShipperCode("3");
		
		map.put("listRoutes", listRoutes);
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "ship.getRoutes";
	}
	
	public String name(){
		return "ShippingController";
	}
	
}
