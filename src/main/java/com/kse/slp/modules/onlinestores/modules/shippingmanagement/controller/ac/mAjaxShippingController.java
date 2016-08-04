package com.kse.slp.modules.onlinestores.modules.shippingmanagement.controller.ac;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.validation.mClientSearchTag;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mPoiInRoute;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mRouteDetailService;

@Controller("mAjaxShippingController")
@RequestMapping(value={"/ship"})
public class mAjaxShippingController {
	@Autowired
	private mRouteDetailService mRouteDetailService;
	
	@Autowired
	private mOrdersService mOrdersService;
	
	@ResponseBody @RequestMapping(value="/loadRouteDetail", method = RequestMethod.POST)
	public  mPoiInRoute[] getTags(@RequestBody String jsonRouteCode) {
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
				mOrders o= mOrdersService.loadAOrderbyOrderCode(rd.getRTD_OrderCode());
				mPoiInRoute r= new mPoiInRoute(o.getO_DeliveryLat(), o.getO_DeliveryLng(),o.getO_Code());
				System.out.println(name()+" "+o+" "+rd.getRTD_Sequence());
				mPIR[Integer.parseInt(rd.getRTD_Sequence())]= r;
			}
			
			return mPIR;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody @RequestMapping(value="/set-order-delivered", method = RequestMethod.POST)
	public boolean setOrderDelivered(@RequestBody String jsonOrderCode) {
		System.out.print(name()+" setOrderDelivered");
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(jsonOrderCode);
			String orderCode= (String) json.get("orderCode");
			mOrdersService.setDeliveredbyOrderCode(orderCode);
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public String name(){
		return "mAjaxShippingController::";
	}
}
