package com.kse.slp.modules.onlinestores.modules.incomingarticles.controller;

import java.util.ArrayList;
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
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mIncomingArticles;
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
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listIncommingArticles(ModelMap model){
		
		List<mIncomingArticles> inArtList = mIncomingArticlesService.getOrderedListByDate();
		
		List<String> inArtDateList = new ArrayList<String>();
		System.out.println(name()+"::listIncommingArticles");
		for(int i=0; i<inArtList.size(); i++){
			String artDate = inArtList.get(i).getIA_Date();
			//System.out.println("date of art "+(i+1)+": "+artDate);
			int indexCut = artDate.indexOf(" ");
			artDate = artDate.substring(0,indexCut);
			if(i==0){
				inArtDateList.add(artDate);
			}else{
				if(!artDate.equals(inArtDateList.get(inArtDateList.size()-1))){
					inArtDateList.add(artDate);
				}
			}
			
		}
		System.out.println(name()+":: inArtDateList"+inArtDateList.toString());
		model.put("inArtList", inArtList);
		model.put("inArtDateList", inArtDateList);
		
		return "in.listIncommingArticles";
	}
	
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
			System.out.println(name()+"::saveIncomingArticles--article "+i+" "+article.toString());
			String article_code = article.getArticleCode();
			int article_amount = article.getAmount();
			float article_price = article.getPrice();
			String article_spcode = article.getSp_code();
			String article_date = article.getDate();
			mIncomingArticlesService.saveAIncomingArticle(article_code, article_amount, article_price, article_spcode, article_date);
		}
		return "redirect:"+this.baseUrl+"/incomingArticles/list.html";
	}
	
	public String name(){
		return "IncomingArticlesController";
	}
}
