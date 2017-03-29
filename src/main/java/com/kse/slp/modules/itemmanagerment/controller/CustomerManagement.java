package com.kse.slp.modules.itemmanagerment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.usermanagement.model.Customer;
import com.kse.slp.modules.usermanagement.service.CustomerService;

@Controller("CustomerManagement")
@RequestMapping(value="/manage/customermanager")
public class CustomerManagement extends BaseWeb{
	
	@Autowired
	CustomerService cusService;
	
	@RequestMapping(value="")
	public String getList(){
		System.out.println(name()+"getList");
		return "customermanager.list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addCustomer(ModelMap model){
		System.out.println(name()+"addCustomer");
		model.put("customerForm", new Customer());
		
		return "customermanager.add";
	}
	
	@RequestMapping(value="/save-a-customer", method=RequestMethod.POST)
	public void saveACustomer(@ModelAttribute("customerForm") Customer cus, BindingResult result){
		System.out.println(name()+"saveACustomer");
		System.out.println(cus.toString());
		//String cusCode = "CUS0000"
		//cusService.save(cus.getCus_Code(), cus.getCus_Name(), cus.getCus_Phone(), cus.getCus_Address(), cus.getCus_Lat(), cus.getCus_Lng());
		cusService.save(cus);
	}
	
	public String name(){
		return "CustomerManagement::";
	}
}
