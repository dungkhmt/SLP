package com.kse.slp.modules.onlinestores.modules.clientmanagment.controller.ac;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.service.mClientsService;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.validation.mClientSearchTag;


@Controller
public class mClientAjaxController {
	@Autowired
	mClientsService clientsService;
	@ResponseBody @RequestMapping(value="/clientSearch-byPhone", method = RequestMethod.POST)
	public  List<mClientSearchTag> getTags(@RequestBody String tag_Json) {
		System.out.println(name()+" "+tag_Json);
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(tag_Json);
			String s= (String) json.get("inputString");
			List<mClients> lClients= clientsService.loadClientbyPhoneTag(s);
			List<mClientSearchTag> lCT= new ArrayList<mClientSearchTag>();
			for(int i=0;i<lClients.size();i++){
				lCT.add(new mClientSearchTag(lClients.get(i).getC_PhoneNumber(), lClients.get(i).getC_Name(), lClients.get(i).getC_Address()));
			}
			return lCT;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		

	}
	public String name(){
		return "mClientAjaxController::";
	}
}
