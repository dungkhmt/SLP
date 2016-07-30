package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.MsoMember;


public interface MemberDAO {
	
	public List<MsoMember> getList();
	public MsoMember getByID(int id);
	public int save(MsoMember member);
	public int delete(MsoMember member);
	
	
}
