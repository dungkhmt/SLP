package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.usermanagement.model.MsoMember;

@Repository("memberDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MemberDAOImpl extends BaseDao implements MemberDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<MsoMember> getList(){
		
		try {
			begin();
			Criteria criteria = getSession().createCriteria(MsoMember.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<MsoMember> candidates = criteria.list();
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
	
	public MsoMember getByID(int id){
		
		try {
			begin();
			Criteria criteria = getSession().createCriteria(MsoMember.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("id", id));
			MsoMember candidate = (MsoMember)criteria.uniqueResult();
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
	public int save(MsoMember member) {		
		try {
			begin();
			int id = (Integer)getSession().save(member);
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
	public int delete(MsoMember member) {		
		try {
			begin();
			getSession().delete(member);
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
