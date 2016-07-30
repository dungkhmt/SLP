package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.usermanagement.model.Role;

@Repository("roleDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RoleDAOImpl extends BaseDao implements RoleDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Role> getList(){
		
		try {
			begin();
			Criteria criteria = getSession().createCriteria(Role.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Role> candidates = criteria.list();
			commit();
			return candidates;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return null;
		} finally {
			flush();
			close();
		}		
	}
	
	public Role getByName(String name){
		
		try {
			begin();
			Criteria criteria = getSession().createCriteria(Role.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("name", name));
			Role candidate = (Role)criteria.uniqueResult();
			commit();
			return candidate;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return null;
		} finally {
			flush();
			close();
		}		
	}
	
	
}
