package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.usermanagement.model.StaffCustomer;

@Repository("StaffCustomerDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class StaffCustomerDAOImpl extends BaseDao implements StaffCustomerDAO {

	@Override
	public StaffCustomer getCusCodeByUsername(String userName) {
		// TODO Auto-generated method stub
		try{
			begin();
			/*
			System.out.println(name()+"getCusCodeByUsername--userName "+userName);
			String sql = "SELECT sc.STFCUS_CustomerCode"
					+ " FROM StaffCustomer sc"
					+ " WHERE sc.STFCUS_Username = '" + userName+"'";
			System.out.println(name()+"getCusCodeByUsername--sql: "+sql);
			
			List<Object[]> queryResult = getSession().createQuery(sql).list();
			String CusCode = (String)queryResult.get(0)[0];
			System.out.println(name()+"getCusCodeByUsername--CusCode: "+CusCode);
			*/
			Criteria criteria = getSession().createCriteria(StaffCustomer.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("STFCUS_Username", userName));
			StaffCustomer sc = (StaffCustomer) criteria.uniqueResult();
			
			commit();
			return sc;
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
	
	public String name(){
		return "StaffCustomerDAOImpl";
	}

}
