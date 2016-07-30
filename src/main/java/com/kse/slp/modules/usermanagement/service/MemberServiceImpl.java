package com.kse.slp.modules.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.usermanagement.dao.MemberDAO;
import com.kse.slp.modules.usermanagement.model.MsoMember;


@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	public MemberDAO getMemberDAO() {
		return memberDAO;
	}
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	public List<MsoMember> getList(){		
		return memberDAO.getList();		
	}
	public MsoMember getByID(int id){
		return memberDAO.getByID(id);
	}
	public int save(String name, String role){
		MsoMember member = new MsoMember(name, role);
		return memberDAO.save(member);
	}
	public int delete(int id){
		MsoMember member = memberDAO.getByID(id);
		return memberDAO.delete(member);
	}
	
}
