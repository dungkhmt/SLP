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

}
