package com.kse.slp.modules.onlinestores.modules.shippingmanagement.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;
import com.kse.slp.modules.containerdelivery.service.mPickupDeliveryOrdersService;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.controller.IncomingArticlesController;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.lstJSONResquestCreateRoute;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mJSONAndroidRouteList;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mJSONRequestCreateRoute;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mJSONResponseBoolean;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mJSONResponseToCreateRoute;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mOrderDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetailContainer;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteUnderCreation;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailContainerService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRoutesService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.usermanagement.service.UserService;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;

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
	
	@Autowired
	private mPickupDeliveryOrdersService mPickupDeliveryService;
	
	@Autowired 
	private UserService mUserService;
	
	@Autowired
	private mRouteDetailContainerService routeDetailContainerService;
	
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
		//System.out.println(name()+"::creatRouteToShip--listOrderDueDate: "+listOrderDueDate.toString());
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
		
		//System.out.println(name()+"::viewAssignedRoute--lstOrders: "+lstOrders.toString());
		
		mJSONResponseToCreateRoute jsonReturn = new mJSONResponseToCreateRoute();
		jsonReturn.setLstOrders(lstOrders);
		jsonReturn.setLstRTUnCreation(lstRTUnCreation);
		jsonReturn.setLstShippers(lstShippers);
		
		return jsonReturn;
	}
	
	@RequestMapping(value="/saveRouteCreated/{method}", method = RequestMethod.POST)
	public @ResponseBody String saveRouteCreated (@PathVariable("method") String method, @RequestBody lstJSONResquestCreateRoute request){
		//System.out.println(name()+"::saveRouteCreated");
		
		//System.out.println(name()+"::saveRouteCreated--(method: "+method+")request data"+request.toString());
		List<mJSONRequestCreateRoute>  lstResRoute= request.getLstJSONresroute();
		int checkSaveRoute=0;
		int checkSaveRouteDetail=0;
		for(int i=0; i<lstResRoute.size(); i++){
			String route_Code = lstResRoute.get(i).getRoute_Code();
			mRoutesService.removeRoutesByRouteCode(route_Code);
			mRouteDetailService.removeRoutesByRouteCode(route_Code);
			
			String route_Shipper_Code = lstResRoute.get(i).getShipper_Code();
			route_Code = route_Shipper_Code + GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently();
			String route_Start_Time = lstResRoute.get(i).getRoute_Start_Time();
				
			if(method.equals("save")){
				checkSaveRoute = mRoutesService.saveARoute(route_Code, route_Shipper_Code, route_Start_Time, Constants.ROUTE_STATUS_UNDER_CREATION);
			}else if(method.equals("confirm")){
				checkSaveRoute = mRoutesService.saveARoute(route_Code, route_Shipper_Code, route_Start_Time, Constants.ROUTE_STATUS_CONFIRMED);
			}
			
			String[] orders_in_route = lstResRoute.get(i).getOrders_In_Route();
			for(int j=0; j<orders_in_route.length;j++){
				String orderCode = orders_in_route[j];
				checkSaveRouteDetail = mRouteDetailService.saveARouteDetail(orderCode, route_Code, j);
				if(method.equals("confirm")){
					mOrdersService.updateStatus(orders_in_route[j], Constants.ORDER_STATUS_IN_ROUTE);
				}
			}
		}
		
		if(checkSaveRouteDetail == 0 || checkSaveRoute == 0){
			return "404";
		}else{
			return "400";
		}
	}
	
	@ResponseBody @RequestMapping(value="/confirm-container-route",method= RequestMethod.POST)
	public boolean confirmRoute(ModelMap model,HttpSession session,@RequestBody String routeShipperObject){
		User user  =(User) session.getAttribute("currentUser");
		System.out.println(name()+" "+routeShipperObject);
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(routeShipperObject);
			mRoutes routeO= mRoutesService.loadRoutesUnderCreationByShipperCode((String)json.get("shipperCode"));
			if(routeO!= null){
				mRoutesService.removeRoutesByRouteCode(routeO.getRoute_Code());
				routeDetailContainerService.deleteRoutesbyRouteCode(routeO.getRoute_Code());
			}
			String shipperCode=(String)json.get("shipperCode");
			String routeCode=shipperCode+GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently();
			String startDateTime=(String)json.get("dateTimeStart");
			mRoutesService.saveARoute(routeCode, shipperCode, startDateTime, Constants.ROUTE_STATUS_CONFIRMED);
			JSONArray listOrder=(JSONArray) json.get("orderList");
			for(int j=0;j<listOrder.size();j++){
				JSONObject orderRoute=(JSONObject) listOrder.get(j);
				System.out.print(name()+" "+(String)orderRoute.get("orderCode"));
				mPickupDeliveryOrders opd= mPickupDeliveryService.loadAPickupDeliveryOrderbyCode((String)orderRoute.get("orderCode"));
				
				
				if((Long)orderRoute.get("isPickup")==1)
					routeDetailContainerService.saveARouteDetailContainer(routeCode, (String)orderRoute.get("orderCode"), "PICKUP", j, Integer.parseInt( String.valueOf(orderRoute.get("quantity"))));
				else
					routeDetailContainerService.saveARouteDetailContainer(routeCode, (String)orderRoute.get("orderCode"), "DELIVERY", j, Integer.parseInt(String.valueOf(orderRoute.get("quantity"))));
				int q= routeDetailContainerService.loadQuantityOfOrderInRouteByOrderCode((String)orderRoute.get("orderCode"));
				if(q>= opd.getOPD_Volumn()) mPickupDeliveryService.updateStatus( (String)orderRoute.get("orderCode"), Constants.ORDER_STATUS_IN_ROUTE);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@ResponseBody @RequestMapping(value="/save-container-routes", method= RequestMethod.POST)
	public boolean saveRoutes(ModelMap model,HttpSession session,@RequestBody String route){
		User user  =(User) session.getAttribute("currentUser");
		JSONParser parser = new JSONParser();
		JSONArray json;
		try {
			json = (JSONArray) parser.parse(route);
			for(int i=0;i<json.size();i++){
				JSONObject o= (JSONObject) json.get(i);
				
				mRoutes routeO= mRoutesService.loadRoutesUnderCreationByShipperCode((String)o.get("shipperCode"));
				if(routeO!= null){
					mRoutesService.removeRoutesByRouteCode(routeO.getRoute_Code());
					routeDetailContainerService.deleteRoutesbyRouteCode(routeO.getRoute_Code());
				}
				String shipperCode=(String)o.get("shipperCode");
				String routeCode=shipperCode+GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently();
				String startDateTime=(String)o.get("dateTimeStart");
				mRoutesService.saveARoute(routeCode, shipperCode, startDateTime, Constants.ROUTE_STATUS_UNDER_CREATION);
				JSONArray listOrder=(JSONArray) o.get("orderList");
				for(int j=0;j<listOrder.size();j++){
					JSONObject orderRoute=(JSONObject) listOrder.get(j);
					if((Long)orderRoute.get("isPickup")==1)
					routeDetailContainerService.saveARouteDetailContainer(routeCode, (String)orderRoute.get("orderCode"), "PICKUP", j, Integer.parseInt( String.valueOf( orderRoute.get("quantity"))));
					else
						routeDetailContainerService.saveARouteDetailContainer(routeCode, (String)orderRoute.get("orderCode"), "DELIVERY", j, Integer.parseInt( String.valueOf(orderRoute.get("quantity"))));
				}
			}
			log.info(user.getUsername()+" DONE");
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name()+route+" ");
		log.info(user.getUsername()+" FAIL");
		return false;
	}
	
	@RequestMapping(value="/getRoutes")
	public String loadRouteShiper(ModelMap map,HttpSession session){
		User user  =(User) session.getAttribute("currentUser");
		mShippers shipper= mShippersService.loadShiperByUserName(user.getUsername());
		if(shipper==null) return null; 
		System.out.println(name()+"loadRouteShiper "+user.getUsername()+" "+shipper.getSHP_Code());
		List<mRoutes> listRoutes= mRoutesService.loadRoutebyShipperCode(shipper.getSHP_Code());
		
		map.put("listRoutes", listRoutes);
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "ship.getRoutes";
	}
	
	@RequestMapping(value="/create-pickupdelivery-route")
	public String createPickupDelivery(ModelMap model,HttpSession session){
		User u  =(User) session.getAttribute("currentUser");
		List<mShippers> listShipper= mShippersService.getList();
		List<mPickupDeliveryOrders> listPDOrder= mPickupDeliveryService.getListOrderPickupDelivery();
		JSONArray listPDO= new JSONArray();
		for(int i=0;i<listPDOrder.size();i++){
			JSONObject js= new JSONObject();
			mPickupDeliveryOrders mPDO= listPDOrder.get(i);
			int volumeInRoute=routeDetailContainerService.loadQuantityOfOrderInRouteByOrderCode(mPDO.getOPD_Code());
			js.put("OPD_Code", mPDO.getOPD_Code());
			js.put("OPD_ClientCode", mPDO.getOPD_ClientCode());
			js.put("OPD_PickupLat", mPDO.getOPD_PickupLat());
			js.put("OPD_PickupLng", mPDO.getOPD_PickupLng());
			js.put("OPD_EarlyPickupDateTime", mPDO.getOPD_EarlyPickupDateTime());
			js.put("OPD_LatePickupDateTime", mPDO.getOPD_LatePickupDateTime());
			js.put("OPD_DeliveryLat", mPDO.getOPD_DeliveryLat());
			js.put("OPD_DeliveryLng", mPDO.getOPD_DeliveryLng());
			js.put("OPD_EarlyDeliveryDateTime", mPDO.getOPD_EarlyDeliveryDateTime());
			js.put("OPD_LateDeliveryDateTime", mPDO.getOPD_LateDeliveryDateTime());
			js.put("OPD_Volumn",mPDO.getOPD_Volumn()-volumeInRoute);
			listPDO.add(js);
		}
		JSONArray listShipperJson= new JSONArray();
		for(int i=0;i<listShipper.size();i++){
			JSONObject js= new JSONObject();
			mShippers mShp= listShipper.get(i);
			js.put("SHP_Code", mShp.getSHP_Code());
			js.put("SHP_DepotLat", mShp.getSHP_DepotLat());
			js.put("SHP_DepotLng", mShp.getSHP_DepotLng());
			js.put("SHP_Capacity_1", mShp.getSHP_Capacity_1());
			js.put("SHP_currentQuantity", 0);
			listShipperJson.add(js);
		}
		JSONArray route= new JSONArray();
		JSONArray routeOldDateTimeStart= new JSONArray();
		for(int i=0;i<listShipper.size();i++){
			mRoutes routeShipper= mRoutesService.loadRoutesUnderCreationByShipperCode(listShipper.get(i).getSHP_Code());
			
			JSONArray routei= new JSONArray();
			if(routeShipper==null){
				route.add(routei);
				routeOldDateTimeStart.add(null);
				continue;
			}
			List<mRouteDetailContainer> listRouteDetail= routeDetailContainerService.loadRouteContainerDetailByRouteCode(routeShipper.getRoute_Code());
			routeOldDateTimeStart.add(routeShipper.getRoute_Start_DateTime());
			for(int j=0;j<listRouteDetail.size();j++){
				mRouteDetailContainer oDC=listRouteDetail.get(j);
				JSONObject order= new JSONObject();
				order.put("orderCode", oDC.getRTDC_OrderCode());
				if(oDC.getRTDC_Type().equals("PICKUP"))
					order.put("isPickup", 1);
				else order.put("isPickup", 0);
				order.put("quantity", oDC.getRTDC_Quantity());
				order.put("marker",null);
				routei.add(order);
			}
			route.add(routei);
		}
		
		model.put("routeOld", route);
		model.put("routeOldDateTimeStart", routeOldDateTimeStart);
		model.put("listShipper",listShipper);
		model.put("listPDOrder",listPDO);
		model.put("listShipperJson", listShipperJson);
		log.info(u.getUsername());
		return "ship.createpickupdeliveryroute";
	}
	
	public String name(){
		return "ShippingController";
	}
	
	@ResponseBody @RequestMapping(value="/get-route-android", method=RequestMethod.POST)
	public mJSONAndroidRouteList getRouteAndroi(HttpSession session){
		User u  =(User) session.getAttribute("currentUser");
		session.setAttribute("currentUser", u);
		List<mRoutes> listRoutes= mRoutesService.loadRoutebyShipperCode(u.getUsername());
		log.info(u.getUsername());
		mJSONAndroidRouteList jsonObject= new mJSONAndroidRouteList();
		jsonObject.setListRoute(listRoutes);
		return jsonObject;
	}
	
	@ResponseBody @RequestMapping(value="/login-android",method=RequestMethod.POST)
	public mJSONResponseBoolean loginAndroid(@RequestBody String jsonLoginCode,HttpSession session){
		
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(jsonLoginCode);
			String user= (String) json.get("username");
			String pass= (String) json.get("password");
			User u= mUserService.getByUsernameAndPassword(user, DigestUtils.md5Hex(pass));
			
			log.info(u.getUsername());
			
			if(u==null) {
				mJSONResponseBoolean response = new mJSONResponseBoolean();
				response.setResult(1);
				return response;
			}
			session.setAttribute("currentUser", u);
			mJSONResponseBoolean response = new mJSONResponseBoolean();
			response.setResult(0);
			return response;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("FAIL ");
		return null;
	}
	
	@RequestMapping(value="/test")
	public void testModules(){
		System.out.print(routeDetailContainerService.loadQuantityOfOrderInRouteByOrderCode("ORPD000007"));
		
	}
	
	@ResponseBody @RequestMapping(value="/update-shipper-location",method=RequestMethod.POST)
	public mJSONResponseBoolean updateShipperLocation(@RequestBody String location,HttpSession session){
		User u  =(User) session.getAttribute("currentUser");
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(location);
			String shipperCode = u.getUsername();
			System.out.println(name()+" "+json.get("latitude"));
			double lat= Double.parseDouble(String.valueOf(json.get("latitude")));
			double lng= Double.parseDouble(String.valueOf(json.get("longitude")));
			Long timeStamp=Long.parseLong(String.valueOf(json.get("timestamp")));
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(timeStamp);
			String timelast=""+calendar.get(Calendar.YEAR);
			timelast+="-"+calendar.get(Calendar.MONTH);
			timelast+="-"+calendar.get(Calendar.DATE);
			timelast+=" "+calendar.get(Calendar.HOUR);
			timelast+=":"+calendar.get(Calendar.MINUTE);
			
			mShippers shipper= mShippersService.loadShiperByUserName(shipperCode);
			if(shipper!=null){
				mShippersService.updateShipperCurrentLocation(lat, lng, shipperCode, timelast);
				mJSONResponseBoolean response = new mJSONResponseBoolean();
				response.setResult(0);
				return response;
			}
			log.info(shipperCode);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mJSONResponseBoolean response = new mJSONResponseBoolean();
		response.setResult(1);
		return response;
	}

	
}
