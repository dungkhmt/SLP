package com.kse.slp.modules.onlinestores.modules.shippingmanagement.controller.ac;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.validation.mClientSearchTag;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.controller.ShippingController;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mPoiInRoute;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailService;
import com.kse.slp.modules.usermanagement.model.User;

@Controller("mAjaxShippingController")
@RequestMapping(value={"/ship"})
public class mAjaxShippingController {
	private static final Logger log = Logger.getLogger(mAjaxShippingController.class);
	@Autowired
	private mRouteDetailService mRouteDetailService;
	
	@Autowired
	private mOrdersService mOrdersService;
	
	@ResponseBody @RequestMapping(value="/loadRouteDetail", method = RequestMethod.POST)
	public  mPoiInRoute[] getTags(@RequestBody String jsonRouteCode,HttpSession session) {
		User u=(User) session.getAttribute("currentUser");
		if(u==null) return null;
		System.out.println(name()+" "+jsonRouteCode);
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(jsonRouteCode);
			String s= (String) json.get("routeCode");
			List<mRouteDetail> l= mRouteDetailService.loadRouteDetailbyRouteCode(s);
			System.out.println(name()+" "+l.size());
			
			mPoiInRoute[] mPIR= new mPoiInRoute[l.size()] ;
			for(int i=0;i<l.size();i++){
				mRouteDetail rd= l.get(i);
				mOrders o= mOrdersService.loadAnOrderbyOrderCode(rd.getRTD_OrderCode());
				mPoiInRoute r;
				System.out.print(name()+o.getO_Status_Code()+" "+Constants.ORDER_STATUS_DELIVERIED);
				if(o.getO_Status_Code().equals(Constants.ORDER_STATUS_DELIVERIED))
					r = new mPoiInRoute(o.getO_DeliveryLat(), o.getO_DeliveryLng(),o.getO_Code(),1);
				
				else if(o.getO_Status_Code().equals(Constants.ORDER_STATUS_ARRIVED_BUT_NOT_DELIVERIED))r= new mPoiInRoute(o.getO_DeliveryLat(), o.getO_DeliveryLng(),o.getO_Code(),2);
				else r= new mPoiInRoute(o.getO_DeliveryLat(), o.getO_DeliveryLng(),o.getO_Code(),0);
				System.out.println(name()+" "+o+" "+rd.getRTD_Sequence());
				mPIR[rd.getRTD_Sequence()]= r;
			}
			
			
			log.info(u.getUsername()+" DONE");
			return mPIR;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//System.out.println(u);
			log.info(u.getUsername()+" FAIL");
			return null;
		}
	}
	
	@ResponseBody @RequestMapping(value="/set-order-delivered", method = RequestMethod.POST)
	public boolean setOrderDelivered(HttpSession session ,@RequestBody String jsonOrderCode) {
		User u=(User) session.getAttribute("currentUser");
		if(u==null) return false;
		System.out.print(name()+" setOrderDelivered");
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(jsonOrderCode);
			String orderCode= (String) json.get("orderCode");
			String orderStatus=(String) json.get("status");
			
			mOrdersService.setStatusbyOrderCode(orderCode, orderStatus);
			
			log.info(u.getUsername()+" DONE");
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(u.getUsername()+" FAIL");
			return false;
		}
	}
	public String name(){
		return "mAjaxShippingController::";
	}
}
