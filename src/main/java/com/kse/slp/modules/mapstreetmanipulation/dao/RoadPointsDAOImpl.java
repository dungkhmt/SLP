package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;
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
	@Override
	public RoadPoint getRoadPointbyCode(int Code) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RoadPoint.class);
			criteria.add(Restrictions.eq("RP_Code", Code));
			List<RoadPoint> list = criteria.list();
			commit();
			if(list==null) return null;
			return list.get(0);
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
