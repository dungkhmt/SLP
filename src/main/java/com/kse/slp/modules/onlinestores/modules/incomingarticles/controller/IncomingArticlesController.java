package com.kse.slp.modules.onlinestores.modules.incomingarticles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mSuppliers;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.service.mSuppliersService;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.validation.mIncomingArticleValidation;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.validation.mListIncomingArticlesForm;
import com.kse.slp.modules.onlinestores.service.mArticlesCategoryService;

@Controller("incomingArticlesController")
@RequestMapping(value={"/incomingArticles"})
public class IncomingArticlesController extends BaseWeb{
	
	@Autowired
	private mSuppliersService mSuppliersService;
	@Autowired
	private mArticlesCategoryService mArticlesCategoryService;
	
	@RequestMapping(value="/addArticles", method = RequestMethod.GET)
	public String addArticles(ModelMap model){
		
		List<mSuppliers> listSuppliers = mSuppliersService.getList();
		List<mArticlesCategory> lstArticlesCat = mArticlesCategoryService.getList();
		
		for(int i=0; i<listSuppliers.size(); i++){
			System.out.println(name()+"::addArticles--Supplier_Code"+listSuppliers.get(i).getSupplier_Code());
		}
		
		
		model.put("listSuppliers", listSuppliers);
		model.put("lstArticlesCat", lstArticlesCat);
		model.put("articleFormAdd", new mIncomingArticleValidation());
		model.put("lstArtForm", new mListIncomingArticlesForm());
		
		System.out.println(name()+"::addArticles--done");
		
		return "in.addArticles";
	}
	
	@RequestMapping(value="/saveIncomingArticles", method=RequestMethod.POST)
	public void saveIncomingArticles(@ModelAttribute("lstArtForm") mListIncomingArticlesForm lstArtForm){
		for(int i=0; i<lstArtForm.getLstIncomingArticles().size(); i++){
			System.out.println(name()+"::saveIncomingArticles"+lstArtForm.getLstIncomingArticles().get(i).toString());
		}
	}
	
	public String name(){
		return "IncomingArticlesController";
	}
	
}
