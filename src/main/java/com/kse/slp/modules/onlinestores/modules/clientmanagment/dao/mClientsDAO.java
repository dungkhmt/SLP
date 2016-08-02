package com.kse.slp.modules.onlinestores.modules.clientmanagment.dao;

import java.util.List;

import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;

public interface mClientsDAO {
	public List<mClients> loadClientbyPhoneTag(String tag);
	public int saveAClient(mClients client);

}
