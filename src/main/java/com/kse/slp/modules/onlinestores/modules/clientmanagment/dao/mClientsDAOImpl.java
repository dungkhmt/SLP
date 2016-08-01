package com.kse.slp.modules.onlinestores.modules.clientmanagment.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
@Repository("mClientsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mClientsDAOImpl extends BaseDao implements mClientsDAO{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<mClients> loadClientbyPhoneTag(String tag) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mClients.class);
			criteria.add(Restrictions.like("C_PhoneNumber", tag, MatchMode.ANYWHERE));
			List<mClients> o= criteria.list();
			commit();
			return o;
		} catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally {
			flush();
			close();
		}
	}
}
