package com.kse.slp.modules.usermanagement.service;

import java.util.List;

import com.kse.slp.modules.usermanagement.model.Customer;

public interface CustomerService {
	public List<Customer> getList();
	public Customer getByCode(String cus_code);
	public int save(String cusCode,String cusName, String cusPhone, String cusAddress, float lat, float lng );
	public int save(Customer cus);
	public int editACustomer(Customer cus);
	public int delACustomer(String cusCode);
}
