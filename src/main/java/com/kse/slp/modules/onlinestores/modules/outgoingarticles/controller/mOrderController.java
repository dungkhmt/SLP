package com.kse.slp.modules.onlinestores.modules.outgoingarticles.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.validation.mOrderFormAdd;


@Controller("mOrderController")
@RequestMapping(value = {"/outgoingarticles"})
public class mOrderController extends BaseWeb{
	@RequestMapping(value = "/add-a-order", method = RequestMethod.GET)
	public String addAOrder(ModelMap model, HttpSession session){
		model.put("orderFormAdd", new mOrderFormAdd());
		return "outgoingarticles.addAOrder";
		
	}
	@RequestMapping(value = "/save-a-order", method = RequestMethod.POST)
	public String saveAOrder(ModelMap model,HttpServletRequest request, HttpSession session,@ModelAttribute("orderFormAdd") mOrderFormAdd orderForm,BindingResult result){
		System.out.print("This is "+orderForm.getOrderAdress());
		String[] orderArticles = request.getParameterValues("orderArticles");
		for(int i=0;i<orderArticles.length;i++){
			System.out.print(orderArticles[i]);
		}
		model.put("orderFormAdd", new mOrderFormAdd());
		if(result.hasErrors()){
			
		}else{
			
		}
		return "outgoingarticles.addAOrder";
		
	}
}
