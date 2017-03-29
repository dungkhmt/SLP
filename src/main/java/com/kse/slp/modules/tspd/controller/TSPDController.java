package com.kse.slp.modules.tspd.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.TTCCLayout;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.tspd.model.TSPDInput;
import com.kse.slp.modules.tspd.model.Tour;

@Controller("TSPDController")
@RequestMapping("/tsp-drone")
public class TSPDController extends BaseWeb{

	@RequestMapping(value="", method = RequestMethod.GET)
	public String home(){
		return "tspd.home";
	}
	
	@RequestMapping(value="/upload-file", method = RequestMethod.GET)
	public String uploadFile(ModelMap model){
		model.put("tspdInputFile", new TSPDInput());
		return "tspd.uploadFile";
	}
	
	@RequestMapping(value="/computeTSPDTour", method = RequestMethod.POST)
	public String computeTSPDTour(ModelMap model, @ModelAttribute("tspdInputFile") TSPDInput request){
		System.out.println(name()+"computeTSPDTour::");
		MultipartFile mFile = request.getTspdInputRequest();
		try {
			InputStream file = mFile.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(file, writer,"UTF-8");
			String json = writer.toString();
			Gson gson = new Gson();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			
			HttpPost post = new HttpPost("http://localhost:8080/ezRoutingAPI/tsp-with-drone");
			StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
			post.addHeader("content-type", "application/json");
			post.setEntity(params);
			
			HttpResponse response = httpClient.execute(post);
			HttpEntity res = response.getEntity();
			String responseString = EntityUtils.toString(res, "UTF-8");
			Tour result = gson.fromJson(responseString, Tour.class);
			
			model.put("tour", responseString);
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "tspd.viewSolution";
	}
	
	public String name(){
		return "TSPDController::";
	}
}
