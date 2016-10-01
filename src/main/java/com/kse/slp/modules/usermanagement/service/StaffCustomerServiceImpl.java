package com.kse.slp.modules.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.usermanagement.dao.StaffCustomerDAO;
import com.kse.slp.modules.usermanagement.model.StaffCustomer;

@Service("StaffCustomerService")
public class StaffCustomerServiceImpl implements StaffCustomerService {
	
	@Autowired
	StaffCustomerDAO StaffCustomerDAO;
	
	@Override
	public StaffCustomer getCusCodeByUserName(String username) {
		// TODO Auto-generated method stub
		return StaffCustomerDAO.getCusCodeByUsername(username);
	}

}
