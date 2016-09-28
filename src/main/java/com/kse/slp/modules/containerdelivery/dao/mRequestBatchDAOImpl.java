package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.containerdelivery.model.RequestBatch;

@Repository("mRequestBatchDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mRequestBatchDAOImpl extends BaseDao implements mRequestBatchDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
	public List<RequestBatch> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatch.class);
			List<RequestBatch > list = criteria.list();
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
	public RequestBatch getByCode(String code) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatch.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("REQBAT_Code", code));
			RequestBatch batch = (RequestBatch)criteria.uniqueResult();
			commit();
			return batch;
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
	public List<RequestBatch> getList(String CustomerCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatch.class);
			criteria.add(Restrictions.eq("REQBAT_CustomerCode", CustomerCode));
			List<RequestBatch > list = criteria.list();
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
