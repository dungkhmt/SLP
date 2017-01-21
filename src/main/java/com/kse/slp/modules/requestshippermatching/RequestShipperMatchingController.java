package com.kse.slp.modules.requestshippermatching;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.modules.usermanagement.model.User;


@Controller("RequestShipperMatchingController")
@RequestMapping(value = {"/requestshippermatching"})
public class RequestShipperMatchingController {
	private static final Logger log = Logger.getLogger(RequestShipperMatchingController.class);
	@RequestMapping(value="",method=RequestMethod.GET)
	public String home(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		System.out.println(name()+"home");
		return "requestshippermatching.home";
	}
	String name(){
		return "RequestShipperMatchingController::";
	}
}
