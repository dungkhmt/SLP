package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;

import com.kse.slp.modules.mapstreetmanipulation.model.RoadType;
@Repository("RoadTypeDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoadTypesDAOImpl extends BaseDao implements RoadTypesDAO {
	@Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public List<RoadType> getListRoadType() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RoadType.class);
			List<RoadType> list = criteria.list();
			commit();
			return list;
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
