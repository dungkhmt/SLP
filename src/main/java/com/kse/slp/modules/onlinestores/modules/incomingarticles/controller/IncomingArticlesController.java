package com.kse.slp.modules.onlinestores.incomingarticles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.modules.onlinestores.incomingarticles.model.mSuppliers;
import com.kse.slp.modules.onlinestores.incomingarticles.service.mSuppliersService;
import com.kse.slp.modules.onlinestores.incomingarticles.validation.mIncomingArticleValidation;

@Controller("incomingArticlesController")
@RequestMapping(value={"/incomingArticles"})
public class IncomingArticlesController {
	
	@Autowired
	private mSuppliersService mSuppliersService;
	
	@RequestMapping(value="/addArticles", method = RequestMethod.GET)
	public String addArticles(ModelMap model){
		
		List<mSuppliers> listSuppliers = mSuppliersService.getList();
		
		model.put("listSuppliers", listSuppliers);
		model.put("articleFormAdd", new mIncomingArticleValidation());
		
		return "in.addArticles";
	}
	
}
