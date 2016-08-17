package com.kse.slp.modules.containerdelivery.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;
import com.kse.slp.modules.containerdelivery.service.mPickupDeliveryOrdersService;
import com.kse.slp.modules.containerdelivery.validation.mOrderPickupDeliveryFormAdd;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mOrderFormAdd;
import com.kse.slp.modules.onlinestores.service.mArticlesCategoryService;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.utilities.GenerationDateTimeFormat;


@Controller("mPickupDeliveryController")
@RequestMapping(value = {"/containerdelivery"})
public class mPickupDeliveryController extends BaseWeb{
	private static final Logger log = Logger.getLogger(mPickupDeliveryController.class);
	@Autowired
	mPickupDeliveryOrdersService pickupDeliveryOrders;
	

	@RequestMapping(value="/list-pickupdelivery-order",method=RequestMethod.GET)
	public String listPickupDelivery(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		List<mPickupDeliveryOrders> list= pickupDeliveryOrders.getListOrderPickupDelivery();
		
		System.out.print(name()+ list);
		model.put("piDelist", list);
		log.info(u.getUsername());
		
		return "containerdelivery.listpickupdeliveryorder";
	}
	@RequestMapping(value = "/add-a-pickupdelivery-order", method = RequestMethod.GET)
	public String addAPickupDeliveryOrder(ModelMap model, HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		model.put("orderPickupDeliveryFormAdd", new mOrderPickupDeliveryFormAdd());
		log.info(u.getUsername());
		return "containerdelivery.addapickupdeliveryorder";
	}
	@RequestMapping(value = "/save-a-pickupdelivery-order", method = RequestMethod.POST)
	public String saveAPickupDeliveryOrder(ModelMap model, HttpSession session,@ModelAttribute("orderPickupDeliveryFormAdd") mOrderPickupDeliveryFormAdd orderForm,BindingResult result){
		User u=(User) session.getAttribute("currentUser");
		model.put("orderPickupDeliveryFormAdd", new mOrderPickupDeliveryFormAdd());
		if(result.hasErrors()){
			log.info(u.getUsername()+" FAIL");
		} else {
			String oPD_ClientCode=orderForm.getOrderClientCode();
			String oPD_PickupAddress=orderForm.getOrderPickupAddress();
			String oPD_DeliveryAddress=orderForm.getOrderDeliveryAddress();
			double oPD_PickupLat=orderForm.getOrderPickupLat();
			double oPD_PickupLng=orderForm.getOrderPickupLng();
			double oPD_DeliveryLat=orderForm.getOrderDeliveryLat();
			double oPD_DeliveryLng=orderForm.getOrderDeliveryLng();
			String oPD_EarlyPickupDateTime=orderForm.getOrderPickupDateTimeEarly();
			String oPD_LatePickupDateTime=orderForm.getOrderDelieveryDateTimeLate();
			String oPD_EarlyDeliveryDateTime=orderForm.getOrderDelieveryDateTimeEarly();
			String oPD_LateDeliveryDateTime=orderForm.getOrderDelieveryDateTimeLate();
			int oPD_Volumn=orderForm.getOrderVolumn();
			String oPD_RequestDateTime = GenerationDateTimeFormat.genDateTimeFormatyyyyMMddCurrently();
			pickupDeliveryOrders.saveAPickupDeliveryOrders(oPD_ClientCode, oPD_RequestDateTime, oPD_PickupAddress, oPD_PickupLat, oPD_PickupLng, oPD_EarlyPickupDateTime, oPD_LatePickupDateTime, oPD_DeliveryAddress, oPD_DeliveryLat, oPD_DeliveryLng, oPD_EarlyDeliveryDateTime, oPD_LateDeliveryDateTime, oPD_Volumn);
			log.info(u.getUsername()+" DONE");
			return "redirect:list-pickupdelivery-order";
		}
		return "redirect:add-a-pickupdelivery-order";
	}
	public String name(){
		return "mPickupDeliveryController::";
	}
}
