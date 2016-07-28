package com.kse.slp.modules.onlinestores.modules.incomingarticles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.onlinestores.model.mArticlesCategory;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mSuppliers;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.service.mIncomingArticlesService;
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
	@Autowired
	private mIncomingArticlesService mIncomingArticlesService;
	
	@RequestMapping(value="/addArticles", method = RequestMethod.GET)
	public String addArticles(ModelMap model){
		
		List<mSuppliers> listSuppliers = mSuppliersService.getList();
		List<mArticlesCategory> lstArticlesCat = mArticlesCategoryService.getList();
		
		model.put("listSuppliers", listSuppliers);
		model.put("lstArticlesCat", lstArticlesCat);
		model.put("articleFormAdd", new mIncomingArticleValidation());
		model.put("lstArtForm", new mListIncomingArticlesForm());
		
		return "in.addArticles";
	}
	
	@RequestMapping(value="/saveIncomingArticles", method=RequestMethod.POST)
	public String saveIncomingArticles(@ModelAttribute("lstArtForm") mListIncomingArticlesForm lstArtForm, @ModelAttribute("articleFormAdd") mIncomingArticleValidation articleFormValid,BindingResult result){
		for(int i=0; i<lstArtForm.getLstIncomingArticles().size(); i++){
			mIncomingArticleValidation article = lstArtForm.getLstIncomingArticles().get(i);
			String article_code = article.getArticleCode();
			int article_amount = article.getAmount();
			float article_price = article.getPrice();
			String article_spcode = article.getSp_code();
			String article_date = article.getDate();
			mIncomingArticlesService.saveAIncomingArticle(article_code, article_amount, article_price, article_spcode, article_date);
		}
		return "in.addArticles";
	}
	
	public String name(){
		return "IncomingArticlesController";
	}
}
