package com.kse.slp.modules.dichung.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.modules.containerdelivery.model.RequestBatch;
import com.kse.slp.modules.containerdelivery.model.mPickupDeliveryOrders;
import com.kse.slp.modules.containerdelivery.service.mRequestBatchService;
import com.kse.slp.modules.usermanagement.model.User;



@Controller("DiChungControler")
@RequestMapping(value = {"/dichung"})
public class DiChungControler {
	@Autowired
	mRequestBatchService requestBatchService;
	private static final Logger log = Logger.getLogger(DiChungControler.class);
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listPickupDelivery(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		return "dichung.home";
	}
	@RequestMapping(value="/add-dichungrequests-by-xls",method=RequestMethod.GET)
	public String addDiChungRequestsXls(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		List<RequestBatch> listBatch= requestBatchService.getList();
		model.put("listBatch", listBatch);
		return "dichung.adddichungrequestsbyxls";
	}
}
