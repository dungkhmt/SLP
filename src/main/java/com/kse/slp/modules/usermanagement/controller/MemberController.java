package com.kse.slp.modules.usermanagement.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kse.slp.controller.BaseWeb;
import com.kse.slp.modules.usermanagement.model.MsoMember;
import com.kse.slp.modules.usermanagement.service.MemberService;


/**
 * Handles requests for the application home page.
 */
@Controller(value = "memberController")
@RequestMapping(value = {"/member"})
public class MemberController extends BaseWeb {
	
	@Autowired
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String memberList(Locale locale, Model model) {
		
		List<MsoMember> listMembers = memberService.getList();		
		model.addAttribute("listMembers", listMembers);
		
		return "member.list";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String memberAddForm(Locale locale, Model model) {		
		return "member.add";
	}
	@RequestMapping(value = "/saveAdd", method = RequestMethod.POST)
	public String memberSaveAdd(Locale locale, Model model,
				                @RequestParam(value="name", required=true) String name,
				                @RequestParam(value="role", required=true) String role) {
		memberService.save(name, role);
		System.out.println(name);
		
		List<MsoMember> listMembers = memberService.getList();		
		model.addAttribute("listMembers", listMembers);
		
		return "member.list";
	}
	@RequestMapping(value = "/saveAdd", method = RequestMethod.GET)
	public String memberSaveAddGet(Locale locale, Model model) {
				
		List<MsoMember> listMembers = memberService.getList();		
		model.addAttribute("listMembers", listMembers);
		
		return "member.list";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteAnUser(ModelMap model, @PathVariable("id") int id, HttpSession session) {
	
		if(memberService.getByID(id) != null)
			memberService.delete(id);
		
		List<MsoMember> listMembers = memberService.getList();		
		model.addAttribute("listMembers", listMembers);
		
		return "member.list";		
	}
	
	
}
