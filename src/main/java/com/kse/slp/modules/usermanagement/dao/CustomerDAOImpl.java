package com.kse.slp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.usermanagement.model.Customer;

@Repository("CustomerDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerDAOImpl extends BaseDao implements CustomerDAO {

	@Override
	public List<Customer> getList() {
		// TODO Auto-generated method stub
		try {
	     	begin();
	     	Criteria criteria = getSession().createCriteria(Customer.class);
	     	List<Customer> lstCustomer = criteria.list();
	        commit();
	        return lstCustomer;
	     }catch (HibernateException e) {
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
	public Customer getByCode(String cus_code) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			Criteria criteria = getSession().createCriteria(Customer.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.eq("Cus_Code", cus_code));
			Customer cus = (Customer) criteria.uniqueResult();
			commit();
			
			return cus;
			
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
