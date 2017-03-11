package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrderStatus;

@Repository("mOrderStatusDAO")
@SuppressWarnings({"unchecked"})
public class mOrderStatusDAOImpl extends BaseDao implements mOrderStatusDAO {
	@Autowired
    private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public int saveOrderStatus(mOrderStatus status) {
		//TODO
		return 0;
	};
	
	@Override
	public void editOrderStatus(int id) {
		//TODO
	};
	
	@Override
	public void deleteOrderStatus(int id) {
		//TODO
	};
	
	@Override
	public List<mOrderStatus> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mOrderStatus.class);
			List<mOrderStatus> listOrderStatus = criteria.list();
			commit();
			return listOrderStatus;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	};
	
}
