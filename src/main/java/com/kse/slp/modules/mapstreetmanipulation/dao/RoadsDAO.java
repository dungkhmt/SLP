package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import com.kse.slp.modules.mapstreetmanipulation.model.Road;

public interface RoadsDAO {
	public List<String> getListByProvince(String proCode);
	public int saveARoad(Road r);
}
