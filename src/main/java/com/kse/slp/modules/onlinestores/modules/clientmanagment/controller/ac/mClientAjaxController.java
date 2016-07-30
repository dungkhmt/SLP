package com.kse.slp.modules.onlinestores.modules.clientmanagment.controller.ac;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kse.slp.modules.onlinestores.modules.clientmanagment.validation.mClientSearchTag;


@Controller("mClientAjaxController")
public class mClientAjaxController {
	@RequestMapping(value="/clientSearch-byPhone", method = RequestMethod.GET)
	public @ResponseBody List<mClientSearchTag> getTags(@RequestParam String tagName) {
		List<mClientSearchTag> l= new ArrayList<mClientSearchTag>();
		l.add(new mClientSearchTag(0, "1"));
		l.add(new mClientSearchTag(0, "2"));
		System.out.print(name());
		return l;

	}
	public String name(){
		return "mClientAjaxController::";
	}
}
