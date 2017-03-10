package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.usermanagement.model.Function;


@Repository("FunctionDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class FunctionDAOImpl extends BaseDao implements FunctionDAO  {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
    public List<Function> loadFunctionsList(){
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Function.class);
            List<Function> funcs = criteria.list();
            commit();
            return funcs;
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
	public List<Function> loadFunctionsParentHierachyList() {
		 try {
	            begin();
	            Criteria criteria = getSession().createCriteria(Function.class);
	            criteria.add(Restrictions.eq("FUNC_ParentId", 0));
	            List<Function> funcs = criteria.list();
	            commit();
	            return funcs;
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
	public List<Function> loadFunctionsChildHierachyList() {
		 try {
	            begin();
	            Criteria criteria = getSession().createCriteria(Function.class);
	            criteria.add(Restrictions.gt("FUNC_ParentId", 0));
	            List<Function> funcs = criteria.list();
	            commit();
	            return funcs;
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
