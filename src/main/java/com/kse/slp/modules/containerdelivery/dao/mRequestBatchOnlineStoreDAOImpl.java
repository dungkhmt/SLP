package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;

import com.kse.slp.modules.containerdelivery.model.RequestBatchOnlineStore;

@Repository("mRequestBatchDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mRequestBatchOnlineStoreDAOImpl extends BaseDao implements mRequestBatchOnlineStoreDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
	public List<RequestBatchOnlineStore> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchOnlineStore.class);
			List<RequestBatchOnlineStore > list = criteria.list();
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
	public RequestBatchOnlineStore getByCode(String code) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchOnlineStore.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("REQBAT_Code", code));
			RequestBatchOnlineStore batch = (RequestBatchOnlineStore)criteria.uniqueResult();
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
	public List<RequestBatchOnlineStore> getList(String CustomerCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchOnlineStore.class);
			criteria.add(Restrictions.eq("REQBAT_CustomerCode", CustomerCode));
			List<RequestBatchOnlineStore > list = criteria.list();
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
