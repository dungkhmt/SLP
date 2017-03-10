package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.usermanagement.model.UserFunctions;
@Repository("UserFunctions")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserFunctionsDAOImpl extends BaseDao implements UserFunctionsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	String name(){
		return "UserFunctionDAO::";
	}
	@Override
	public List<UserFunctions> loadFunctionsPermissionByUserList(String userName) {
		try {
			Criteria criteria = getSession()
					.createCriteria(UserFunctions.class);
			criteria.add(Restrictions.eq("USERFUNC_UserCode", userName));
			criteria.addOrder(Order.asc("USERFUNC_Id"));
			List<UserFunctions> userFuncs = criteria.list();
			return userFuncs;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserFunctions loadFunctionsPermissionByCodeAndUser(
			String functionCode, String userName) {
		try {
			Criteria criteria = getSession()
					.createCriteria(UserFunctions.class);
			criteria.add(Restrictions.eq("USERFUNC_UserCode", userName));
			criteria.add(Restrictions.eq("USERFUNC_FuncCode", functionCode));
			UserFunctions userFunc = (UserFunctions) criteria.uniqueResult();
			return userFunc;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveAFunction(UserFunctions u) {
		try {
			int id = (int) getSession().save(u);
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeAFunction(UserFunctions u) {
		try {
			getSession().delete(u);
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
