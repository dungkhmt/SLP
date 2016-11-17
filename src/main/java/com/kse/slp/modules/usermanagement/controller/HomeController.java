package com.kse.slp.modules.usermanagement.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.usermanagement.model.StaffCustomer;
import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.modules.usermanagement.service.StaffCustomerService;
import com.kse.slp.modules.usermanagement.service.UserService;


/**
 * Handles requests for the application home page.
 */
@Controller(value = "homeController")
@RequestMapping(value = {""})
public class HomeController extends BaseWeb {
	
    @Autowired
    private UserService userService;
    @Autowired
    private StaffCustomerService staffCustomerService;
    
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String memberList(Locale locale, Model model, HttpSession session) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (!username.equals("anonymousUser")){
		    User user = userService.getByUsername(username);
		    session.setAttribute("currentUser", user);		
		    //StaffCustomer cusCode=staffCustomerService.getCusCodeByUserName(username);
		    //session.setAttribute("CustomerCode", cusCode.getSTFCUS_CustomerCode());
		}
		//model.addAttribute("redirect", "member.html");
		return "home";
	}	
}
