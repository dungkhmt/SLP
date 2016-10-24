package com.kse.slp.modules.containerdelivery.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;

import com.kse.slp.modules.containerdelivery.model.RequestBatchContainerDelivery;

@Repository("mRequestBatchContainerDeliveryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mRequestBatchContainerDeliveryDAOImpl extends BaseDao implements mRequestBatchContainerDeliveryDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
	public List<RequestBatchContainerDelivery> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchContainerDelivery.class);
			List<RequestBatchContainerDelivery > list = criteria.list();
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
	public RequestBatchContainerDelivery getByCode(String code) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchContainerDelivery.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("REQBAT_Code", code));
			RequestBatchContainerDelivery batch = (RequestBatchContainerDelivery)criteria.uniqueResult();
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
	public List<RequestBatchContainerDelivery> getList(String CustomerCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RequestBatchContainerDelivery.class);
			criteria.add(Restrictions.eq("REQBAT_CustomerCode", CustomerCode));
			List<RequestBatchContainerDelivery > list = criteria.list();
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
