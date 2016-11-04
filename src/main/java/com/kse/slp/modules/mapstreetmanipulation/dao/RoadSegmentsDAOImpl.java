package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;
@Repository("RoadSegmentsDAOImpl")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoadSegmentsDAOImpl extends BaseDao implements RoadSegmentsDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public List<RoadSegment> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RoadSegment.class);
			List<RoadSegment> list = criteria.list();
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
	public int saveASegment(RoadSegment segment) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			int id = 0;
			id = (int)getSession().save(segment);
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
	public void updateASegment(RoadSegment segment) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			getSession().update(segment);
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
	public void deleteSegmentByCode(int code) {
		// TODO Auto-generated method stub
		RoadSegment segment = new RoadSegment();
		segment.setRSEG_Code(code);
		try{
			
			begin();
			getSession().delete(segment);
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

}
