package com.kse.slp.modules.timaappraisalscheduling.controller;

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
import com.kse.slp.modules.timaappraisalscheduling.model.TimaAppraisalSchedulingInput;
import com.kse.slp.modules.tspd.model.TSPDInput;

@Controller("TIMAppraisalScheduling")
@RequestMapping("/tima-appraisal-scheduling")
public class TimaAppraisalSchedulingController extends BaseWeb{
	@RequestMapping(value="", method = RequestMethod.GET)
	public String home() {
		return "timascheduling.home";
	}
	
	@RequestMapping(value="/upload-file", method = RequestMethod.GET)
	public String uploadFile(ModelMap model){
		model.put("timaschedulingInputFile", new TimaAppraisalSchedulingInput());
		return "timascheduling.uploadFile";
	}
	
	@RequestMapping(value="/computeTour", method = RequestMethod.POST)
	public String computeTSPDTour(ModelMap model, @ModelAttribute("tspdInputFile") TimaAppraisalSchedulingInput request){
		//System.out.println(name()+"computeTSPDTour::");
		MultipartFile mFile = request.getTimaAppraisalSchedulingInputRequest();
		try {
			InputStream file = mFile.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(file, writer,"UTF-8");
			String json = writer.toString();
			Gson gson = new Gson();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			
			HttpPost post = new HttpPost("http://localhost:8088/appraisal-scheduling/run-algorithm");
			StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
			post.addHeader("content-type", "application/json");
			post.setEntity(params);
			
			HttpResponse response = httpClient.execute(post);
			HttpEntity res = response.getEntity();
			String responseString = EntityUtils.toString(res, "UTF-8");
			//Tour[] result = gson.fromJson(responseString, Tour[].class);
			System.out.println("responseString "+responseString);
			model.put("sol", responseString);
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "timascheduling.viewSolution";
	}
}
