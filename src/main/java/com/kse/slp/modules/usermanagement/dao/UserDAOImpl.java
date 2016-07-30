package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.modules.usermanagement.model.User;
import com.kse.slp.dao.BaseDao;

@Repository("userDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserDAOImpl extends BaseDao implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<User> getList(){
		
		try {
			begin();
			Criteria criteria = getSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<User> candidates = criteria.list();
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
	
	public User getByID(int id){		
		try {
			begin();
			Criteria criteria = getSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("id", id));
			User candidate = (User)criteria.uniqueResult();
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
	
	@Override
	public User getByUsernameAndPassword(String username, String password) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("username", username));
			criteria.add(Restrictions.eq("password", password));
			User candidate = (User)criteria.uniqueResult();
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
	
	@Override
	public User getByUsername(String username) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("username", username));
			User candidate = (User)criteria.uniqueResult();
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

	public int save(User user) {		
		try {
			begin();
			int id = (Integer)getSession().save(user);
			commit();
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return 0;
		} finally {
			flush();
			close();
		}
	}
	public int delete(User user) {		
		try {
			begin();
			getSession().delete(user);
			commit();
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return 0;
		} finally {
			flush();
			close();
		}
	}

	
}
