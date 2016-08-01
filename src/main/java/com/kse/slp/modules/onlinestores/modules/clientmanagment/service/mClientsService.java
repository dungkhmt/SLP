package com.kse.slp.modules.onlinestores.modules.clientmanagment.service;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;

public interface mClientsService {
	public List<mClients> loadClientbyPhoneTag(String tag);

}
