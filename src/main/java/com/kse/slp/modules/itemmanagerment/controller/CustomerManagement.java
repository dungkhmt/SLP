package com.kse.slp.modules.itemmanagerment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.usermanagement.service.CustomerService;
import com.kse.slp.modules.usermanagement.model.Customer;

@Controller("CustomerManagement")
@RequestMapping(value="/customermanager")
public class CustomerManagement extends BaseWeb{
	
	@Autowired	
	CustomerService cusService;
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getList(ModelMap model){
		List<Customer> lstCustomer = cusService.getList();
		System.out.println(name());
		model.put("customer",lstCustomer);
		return "customermanager.list";
	}
	String name(){
		return "CustomerManagement:: ";
	}
	@RequestMapping(value="/add-a-customer", method = RequestMethod.GET)
	public String addCustomer(ModelMap model){
		System.out.println(name()+"addCustomer");
		model.put("cusForm", new Customer());
		return("customermanager.add");
	}
	@RequestMapping(value="/save-a-customer", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("cusForm") Customer cus, BindingResult result){
		System.out.println(name()+"saveACustomer");
		System.out.println(cus.toString());
		cusService.save(cus);
		return "redirect:/customermanager";
	}
	@RequestMapping(value="/edit-a-customer/{cusCode}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable("cusCode") String cusCode,ModelMap model){
		System.out.println(name()+"editCustomer::Customer code::"+cusCode);
		Customer cus = cusService.getByCode(cusCode);
		model.put("cusForm", new Customer());
		model.put("customer", cus);
		System.out.println(name()+"editCustomer::"+cusCode);
		return "customermanager.edit";
		
	}
	@RequestMapping(value="/edit-a-customer", method=RequestMethod.POST)
	public String editACustomer(@ModelAttribute("cusForm") Customer cus, BindingResult result){
		cusService.editACustomer(cus);
		return "redirect:/customermanager";
	}
	@RequestMapping(value="/del-a-customer/{cusCode}", method = RequestMethod.GET)
	public String delACustomer(@PathVariable("cusCode") String cusCode	){
		cusService.delACustomer(cusCode);
		System.out.println(name()+"deleteCustomer::"+cusCode);
		return "redirect:/customermanager";
	}
}
