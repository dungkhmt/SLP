package com.kse.slp.modules.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.usermanagement.dao.CustomerDAO;
import com.kse.slp.modules.usermanagement.model.Customer;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired 
	private CustomerDAO CustomerDAO;
	
	@Override
	public List<Customer> getList() {
		// TODO Auto-generated method stub
		return CustomerDAO.getList();
	}

	@Override
	public Customer getByCode(String cus_code) {
		// TODO Auto-generated method stub
		return CustomerDAO.getByCode(cus_code);
	}

}
