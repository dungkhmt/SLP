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
	public List<Road> getListByProvince(String proCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			Criteria criteria = getSession().createCriteria(Road.class);
			criteria.add(Restrictions.eq("RoadProvince", proCode));
			List<Road> road = criteria.list();
			commit();
			return road;
			
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

	@Override
	public int saveARoad(Road r) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			int id=0;
			id = (int)getSession().save(r);
			commit();
			return id;
			
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return 0;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public void updateARoad(Road road) {
		// TODO Auto-generated method stub
		try{
			begin();
			getSession().update(road);
			commit();
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
		}finally{
			flush();
			close();
		}
	}

	@Override
	public Road loadARoadByRoadCode(String roadCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			Criteria criteria = getSession().createCriteria(Road.class);
			criteria.add(Restrictions.eq("RoadCode", roadCode));
			Road road = (Road) criteria.uniqueResult();
			commit();
			return road;
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
