package com.kse.slp.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.modules.dichung.controller.DiChungControler;
import com.kse.slp.modules.usermanagement.model.User;

@Controller("ManagerControler")
@RequestMapping(value = {"/manage"})
public class ManagerController {
	private static final Logger log = Logger.getLogger(ManagerController.class);
	@RequestMapping(value="",method=RequestMethod.GET)
	public String home(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "manage.home";
	}

}
