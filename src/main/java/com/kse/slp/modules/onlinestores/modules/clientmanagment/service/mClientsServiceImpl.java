package com.kse.slp.modules.onlinestores.modules.clientmanagment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kse.slp.modules.onlinestores.modules.clientmanagment.dao.mClientsDAO;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao.mOrdersDAO;

@Service("mClientsService")
public class mClientsServiceImpl implements mClientsService {
	@Autowired
	mClientsDAO clientDAO;
	@Override
	public List<mClients> loadClientbyPhoneTag(String tag) {
		// TODO Auto-generated method stub
		return clientDAO.loadClientbyPhoneTag(tag);
	}
	@Override
	public int saveAClient(String C_Phone, String C_Name, String C_Address,
			String C_Facebook, String C_Email) {
		// TODO Auto-generated method stub
		mClients client= new mClients();
		client.setC_Code(C_Phone);
		client.setC_PhoneNumber(C_Phone);
		client.setC_Address(C_Address);
		client.setC_Email(C_Email);
		client.setC_Name(C_Name);
		client.setC_FacebookAccount(C_Facebook);
		
		return clientDAO.saveAClient(client);
	}
	

}
