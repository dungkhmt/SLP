package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.Customer;

public interface CustomerDAO {
	public List<Customer> getList();
	public Customer getByCode(String cus_code);
}
