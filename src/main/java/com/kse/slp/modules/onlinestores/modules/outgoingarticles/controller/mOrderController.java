package com.kse.slp.modules.onlinestores.modules.outgoingarticles.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderArticles;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.service.mOrdersService;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mOrderFormAdd;
import com.kse.slp.modules.onlinestores.service.mArticlesCategoryService;


@Controller("mOrderController")
@RequestMapping(value = {"/outgoingarticles"})
public class mOrderController extends BaseWeb{
	@Autowired
	mArticlesCategoryService articleService;
	@Autowired
	mOrdersService orderService;
	@RequestMapping(value = "/add-an-order", method = RequestMethod.GET)
	public String addAOrder(ModelMap model, HttpSession session){
		model.put("orderFormAdd", new mOrderFormAdd());
		List<mArticlesCategory> list= articleService.getList();
		//System.out.print("this is list"+list.size());
		model.put("listArticleCategory", list);
		for(int i=0;i<list.size();i++ )
			System.out.print(list.get(i).toString());
		return "outgoingarticles.addAOrder";
		//return "trash.outgoingarticles.add";
		
	}
	@RequestMapping(value = "/save-an-order", method = RequestMethod.POST)
	public String saveAOrder(ModelMap model,HttpServletRequest request, HttpSession session,@ModelAttribute("orderFormAdd") mOrderFormAdd orderForm,BindingResult result){
		//System.out.print("This is "+orderForm.getOrderAdress());
		String[] orderArticles = request.getParameterValues("orderArticles");
		for(int i=0;i<orderArticles.length;i++){
			System.out.print(orderArticles[i]);
		}
		model.put("orderFormAdd", new mOrderFormAdd());
		if(result.hasErrors()){
			
		}else{
			String code=orderForm.getOrderClientCode();
			String clientCode=orderForm.getOrderClientCode();
			String dueDate=orderForm.getOrderDate();
			float lng=orderForm.getOrderDeliveryLng();
			float lat= orderForm.getOrderDeliveryLat();
			String timeEarly= orderForm.getOrderTimeEarly();
			String timeLate=orderForm.getOrderTimeLate();
			String address=orderForm.getOrderAdress();
			Date date= new Date();
			Date currentDate = new Date();
			SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyy.MM.dd");
			String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
			String orderDate=sCurrentDate;
			orderService.saveAOrder( clientCode, orderDate, dueDate,address,lat,lng,timeEarly,timeLate,orderArticles);
		}
		return "outgoingarticles.addAOrder";
		
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listOutGoingArticles(ModelMap model){
		System.out.print("listIncommingArticles");
		List<mOrders> inArtList = orderService.getList();
		
		System.out.print(inArtList);
	
		model.put("outArtList", inArtList);
		
		
		return "outgoingarticles.list";
	}
}
