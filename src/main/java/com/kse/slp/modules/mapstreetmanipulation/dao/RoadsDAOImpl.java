package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.model.Road;

@Repository("RoadsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoadsDAOImpl extends BaseDao implements RoadsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<String> getListByProvince(String proCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			Criteria criteria = getSession().createCriteria(Road.class);
			criteria.add(Restrictions.eq("RoadProvince", proCode));
			List<Road> road = criteria.list();
			List<String> streetNames = new ArrayList<String>();
			for(int i=0; i<road.size(); i++){
				String streetName = road.get(i).getRoadName();
				streetNames.add(streetName);
			}
			commit();
			return streetNames;
			
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}

}
