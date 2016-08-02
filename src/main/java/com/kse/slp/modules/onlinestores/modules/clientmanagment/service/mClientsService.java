package com.kse.slp.modules.onlinestores.modules.clientmanagment.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;

public interface mClientsService {
	public List<mClients> loadClientbyPhoneTag(String tag);
	public int saveAClient(String C_Phone,String C_Name,String C_Address,String C_Facebook, String C_Email);

}
