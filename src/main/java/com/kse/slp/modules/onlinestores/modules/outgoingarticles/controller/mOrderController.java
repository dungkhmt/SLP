package com.kse.slp.modules.onlinestores.modules.outgoingarticles.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.modules.onlinestores.outgoingarticles.validation.mOrderFormAdd;

@Controller("mOrderController")
@RequestMapping(value = {"/outgoingarticles"})
public class mOrderController {
	@RequestMapping(value = "/addAOrder", method = RequestMethod.GET)
	public String addAOrder(ModelMap model, HttpSession session){
		model.put("orderFormAdd", new mOrderFormAdd());
		return "outgoingarticles.addAOrder";
		
	}

}
