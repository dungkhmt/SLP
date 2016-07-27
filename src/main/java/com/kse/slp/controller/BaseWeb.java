package com.kse.slp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseWeb {

    @Autowired
    private HttpServletRequest request;
    protected String baseUrl;
    protected String assetsUrl;
    
    public BaseWeb() {
		// TODO Auto-generated constructor stub
	}
    
    @ModelAttribute
    public void addGlobalAttr(ModelMap map) {
    
        switch (request.getRequestURI()) {
            case "/":
                baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - 1).toString();
                break;
            case "":
                baseUrl = request.getRequestURL().toString();
                break;
            default:
                baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
                break;
        }       
       
        System.out.println(name()+"::addGlobalAttr");
        
        assetsUrl = baseUrl + "/assets";
        map.put("baseUrl", baseUrl);
        map.put("assetsUrl", assetsUrl);
    }
    
    public String name(){
    	return "BaseWeb";
    }
}
