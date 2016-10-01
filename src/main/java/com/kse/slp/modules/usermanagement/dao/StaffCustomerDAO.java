package com.kse.slp.modules.usermanagement.dao;

import com.kse.slp.modules.usermanagement.model.StaffCustomer;

public interface StaffCustomerDAO {
	public StaffCustomer getCusCodeByUsername(String userName);
}
