package com.kse.slp.modules.vrpload3d.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.tspd.model.TSPDInput;
import com.kse.slp.modules.vrpload3d.model.VRPLoad3DInput;

@Controller("VRPLoad3DController")
@RequestMapping("/vrp-load3d")
public class VRPLoad3DController extends BaseWeb{

	@RequestMapping(value="", method = RequestMethod.GET)
	public String home(){
		return "vrpload3d.home";
	}
	
	@RequestMapping(value="/upload-file", method = RequestMethod.GET)
	public String uploadFile(ModelMap model){
		model.put("vrpInputFile", new VRPLoad3DInput());
		return "vrpload3d.uploadFile";
	}
	
	@RequestMapping(value="/computeSolution", method = RequestMethod.POST)
	public String computeTSPDTour(ModelMap model, @ModelAttribute("vrpInputFile") VRPLoad3DInput request){
		System.out.println("VRPLoad3DController::computeTSPDTour::");
		MultipartFile mFile = request.getVrpInputRequest();
		try {
			InputStream file = mFile.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(file, writer,"UTF-8");
			String json = writer.toString();
			Gson gson = new Gson();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			
			HttpPost post = new HttpPost("http://localhost:8080/ezRoutingAPI/vrp-load3d");
			StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
			post.addHeader("content-type", "application/json");
			post.setEntity(params);
			
			HttpResponse response = httpClient.execute(post);
			HttpEntity res = response.getEntity();
			String responseString = EntityUtils.toString(res, "UTF-8");
			//Tour[] result = gson.fromJson(responseString, Tour[].class);
			
			model.put("sol", responseString);
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "vrpload3d.viewSolution";
	}
}
