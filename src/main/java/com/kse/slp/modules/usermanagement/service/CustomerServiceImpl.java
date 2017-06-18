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

	@Override
	public int save(String cusCode,String cusName, String cusPhone, String cusAddress,
			float lat, float lng) {
		// TODO Auto-generated method stub
		Customer cus = new Customer(0, cusCode, cusName, cusPhone, cusAddress, lat, lng);
		
		return CustomerDAO.save(cus);
	}

	@Override
	public int save(Customer cus) {
		// TODO Auto-generated method stub
		return CustomerDAO.save(cus);
	}

	@Override
	public int editACustomer(Customer cus) {
		// TODO Auto-generated method stub
		Customer customer = CustomerDAO.getByCode(cus.getCus_Code());
		customer.setCus_Address(cus.getCus_Address());
		customer.setCus_Name(cus.getCus_Name());
		customer.setCus_Phone(cus.getCus_Phone());
		customer.setCus_Lat(cus.getCus_Lat());
		customer.setCus_Lng(cus.getCus_Lng());
		return CustomerDAO.editACustomer(customer);
	
	}

	@Override
	public int delACustomer(String cusCode) {
		// TODO Auto-generated method stub
		Customer customer = CustomerDAO.getByCode(cusCode);
		return CustomerDAO.delACustomer(customer);
	}

}
