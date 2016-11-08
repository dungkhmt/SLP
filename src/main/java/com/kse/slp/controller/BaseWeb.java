package com.kse.slp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
import com.kse.slp.modules.mapstreetmanipulation.service.RoadPointsService;
import com.kse.slp.modules.mapstreetmanipulation.service.RoadPointsServiceImpl;
import com.kse.slp.modules.utilities.shortestpathalgorithms.Arc;
import com.kse.slp.modules.utilities.shortestpathalgorithms.DijkstraBinaryHeap;
import com.kse.slp.modules.utilities.shortestpathalgorithms.Itinerary;

public class BaseWeb {

    @Autowired
    private HttpServletRequest request;
    protected String baseUrl;
    protected String assetsUrl;
    
    //public static DijkstraBinaryHeap dijkstra = null;
    public static int count = 0;
    
    public BaseWeb() {
		// TODO Auto-generated constructor stub
    	/*
    	if(dijkstra == null){
    		// load map from database
    		//RoadPointsService roadPointService = new RoadPointsServiceImpl();
    		//List<RoadPoint> points = roadPointService.getList();
    		//for(RoadPoint p:  points){
    		//	System.out.println(p.getRP_LatLng());
    		//}
    		ArrayList<Integer> V = new ArrayList<Integer>();
    		HashMap<Integer, ArrayList<Arc>> A = new HashMap<Integer, ArrayList<Arc>>();
    		
    		V.add(1);
    		V.add(3);
    		V.add(5);
    		V.add(7);
    		V.add(9);
    		
    		Arc a1 = new Arc(1,3,10.0);
    		Arc a2 = new Arc(1,5,3.0);
    		Arc a3 = new Arc(1,9,8.0);
    		Arc a4 = new Arc(3, 9, 1.0);
    		Arc a5 = new Arc(3,1,7.0);
    		Arc a6 = new Arc(5,9,1.0);
    		Arc a7 = new Arc(9,7,2.0);
    		
    		ArrayList<Arc> A1 = new ArrayList<Arc>();
    		A1.add(a1); A1.add(a2); A1.add(a3);
    		
    		ArrayList<Arc> A3 = new ArrayList<Arc>();
    		A3.add(a4); A3.add(a5);
    		
    		ArrayList<Arc> A5 = new ArrayList<Arc>();
    		A5.add(a6);
    		
    		ArrayList<Arc> A7 = new ArrayList<Arc>();
    		
    		ArrayList<Arc> A9 = new ArrayList<Arc>();
    		A9.add(a7);
    		
    		A.put(1, A1);
    		A.put(3, A3);
    		A.put(5, A5);
    		A.put(7, A7);
    		A.put(9, A9);
    		
    		dijkstra = new DijkstraBinaryHeap(V, A);
    		Itinerary I = dijkstra.solve(1, 9);
    		for(int i = 0; i < I.size(); i++){
    			System.out.print(I.get(i) + " ");
    		}

    	}
    	*/
    	
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
       
        
        
        assetsUrl = baseUrl + "/assets";
        map.put("baseUrl", baseUrl);
        map.put("assetsUrl", assetsUrl);
    }
    
   
}
