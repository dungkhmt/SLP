package com.kse.slp.modules.shippermanagerment.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.service.mShippersService;
import com.kse.slp.modules.usermanagement.model.Customer;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.usermanagement.service.CustomerService;
import com.kse.slp.modules.usermanagement.service.UserService;

@Controller("ShipperManagerment")
@RequestMapping(value = "shippermanagerment")
public class ShipperManagerment extends BaseWeb{

	@Autowired
	mShippersService mShipService;
	@Autowired
	CustomerService cusService;
	@Autowired
	UserService uService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getList(ModelMap model){
		List<mShippers> lstShipper = mShipService.getList();
		System.out.println(name());
		System.out.println(name()+"::getList::lstShipper="+lstShipper.toString());
		model.put("shipper", lstShipper);
		return "shippermanagerment.list";
		
	}
	String name() {
		// TODO Auto-generated method stub
		return "ShipperManagerment:: ";
	}
	@RequestMapping(value = "/add-a-shipper", method = RequestMethod.GET)
	public String addShipper(ModelMap model){
		List<Customer> lstCustomer = cusService.getList();
		List<User> lstUser = uService.getList();
		System.out.println(name()+"addAShipper");
		model.put("lstCustomer", lstCustomer);
		model.put("lstUser", lstUser);
		model.put("shipForm", new mShippers());
		return("shippermanagerment.add");
	}
	@RequestMapping(value = "/save-a-shipper", method = RequestMethod.POST)
	public String saveAShipper(@ModelAttribute("shipForm") mShippers ship, BindingResult result){
		System.out.println(name()+"saveAShipper");
		System.out.println(ship.toString());
		int check = mShipService.save(ship);
		return "redirect:/shippermanagerment";
	}
	@RequestMapping(value = "/edit-a-shipper/{shipCode}", method = RequestMethod.GET)
	public String editAShipper(@PathVariable("shipCode") String shipCode, ModelMap model){
		List<Customer> lstCustomer = cusService.getList();
		List<User> lstUser = uService.getList();
		mShippers ship = mShipService.getByCode(shipCode);
		model.put("lstCustomer", lstCustomer);
		model.put("lstUser", lstUser);
		model.put("shipForm", new mShippers());
		model.put("shipper", ship);
		System.out.println(name()+"Edit");
		return "shippermanagerment.edit";
	}
	@RequestMapping(value = "/edit-a-shipper", method = RequestMethod.POST)
	public String editAShipper(@ModelAttribute("shipForm") mShippers ship, BindingResult result){
		mShipService.editAShipper(ship);
		return "redirect:/shippermanagerment";
	}
	@RequestMapping(value = "/del-a-shipper/{shipCode}", method = RequestMethod.GET)
	public String delAShipper(@PathVariable("shipCode") String shipCode, ModelMap model){
		mShipService.delAShipper(shipCode);
		return "redirect:/shippermanagerment";
	}
}
