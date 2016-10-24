package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;

import com.kse.slp.modules.containerdelivery.model.RequestBatchDiChung;

@Repository("mRequestBatchDiChungDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mRequestBatchDiChungDAOImpl extends BaseDao implements mRequestBatchDiChungDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
	public List<RequestBatchDiChung> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchDiChung.class);
			List<RequestBatchDiChung > list = criteria.list();
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
	public RequestBatchDiChung getByCode(String code) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchDiChung.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("REQBAT_Code", code));
			RequestBatchDiChung batch = (RequestBatchDiChung)criteria.uniqueResult();
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
	public List<RequestBatchDiChung> getList(String CustomerCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchDiChung.class);
			criteria.add(Restrictions.eq("REQBAT_CustomerCode", CustomerCode));
			List<RequestBatchDiChung > list = criteria.list();
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
