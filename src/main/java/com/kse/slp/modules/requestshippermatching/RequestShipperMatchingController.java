package com.kse.slp.modules.requestshippermatching;

import java.io.InputStream;
import java.io.StringWriter;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.FormulaShifter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.api.requestshippermatching.model.RequestShipperMatchingSolution;
import com.kse.slp.modules.dichung.model.FormInterCityRequest;
import com.kse.slp.modules.dichung.model.SharedLongTripSolution;
import com.kse.slp.modules.requestshippermatching.model.FormRequestShipperMatchingInputJson;
import com.kse.slp.modules.usermanagement.model.User;


@Controller("RequestShipperMatchingController")
@RequestMapping(value = {"/requestshippermatching"})
public class RequestShipperMatchingController extends BaseWeb {
	private static final Logger log = Logger.getLogger(RequestShipperMatchingController.class);
	@RequestMapping(value="",method=RequestMethod.GET)
	public String home(ModelMap model,HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		System.out.println(name()+"home");
		return "requestshippermatching.home";
	}
	@RequestMapping(value = "/upload-shipper-matching-request", method = RequestMethod.GET)
	public String uploadShipperMatchingRequest(ModelMap model, HttpSession session){
		User u=(User) session.getAttribute("currentUser");
		log.info(u.getUsername());
		model.put("formShipperMatchingRequest", new FormRequestShipperMatchingInputJson());
		return "requestshippermatching.uploadshippermatchingrequest";
		
	}
	@RequestMapping(value="/solve-shipper-matching-requests", method=RequestMethod.POST)
	public String solveShipperMatchingRequests(ModelMap model, @ModelAttribute("formShipperMatchingRequest") FormRequestShipperMatchingInputJson requests){
		System.out.println(name() + "::solveLongTripRequest");
		MultipartFile mfile = requests.getShipperMatchingRequest();
		try{
			InputStream file = mfile.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(file,writer,"UTF-8");
			String json = writer.toString();
			System.out.println(json);
			Gson gson = new Gson();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			try {
			    //HttpPost request = new HttpPost("http://103.18.4.32:8080/ezRoutingAPI/shared-taxi-plan-dichung");
				//HttpPost request = new HttpPost("http://192.168.76.15:8080/ezRoutingAPI/shared-taxi-plan-dichung");
				HttpPost request = new HttpPost("http://localhost:8080/ezRoutingAPI/request-shipper-matching");
			    StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
			    request.addHeader("content-type", "application/json");
			    request.setEntity(params);
			    System.out.println(request.getEntity());
			    HttpResponse response = httpClient.execute(request);
			    HttpEntity  res= response.getEntity();
			    String responseString = EntityUtils.toString(res, "UTF-8");
			    System.out.println(name() + "::solveLongTripRequests, responseString = " + responseString);
			    RequestShipperMatchingSolution sts= gson.fromJson(responseString, RequestShipperMatchingSolution.class);
			    
			    model.put("sol", responseString);
			    
			    System.out.println(sts);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			file.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "requestshippermatching.viewsolutionshippermatching";
	}
	String name(){
		return "RequestShipperMatchingController::";
	}
}
