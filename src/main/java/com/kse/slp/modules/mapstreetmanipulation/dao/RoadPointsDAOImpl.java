package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
@Repository("RoadPointDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoadPointsDAOImpl extends BaseDao implements RoadPointsDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public List<RoadPoint> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RoadPoint.class);
			List<RoadPoint> list = criteria.list();
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
