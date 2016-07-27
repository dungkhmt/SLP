package com.kse.slp.modules.onlinestores.modules.incomingarticles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.incomingarticles.model.mSuppliers;

@Repository("mSuppliersDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mSuppliersDAOImpl extends BaseDao implements mSuppliersDAO {

	/*
	 try {
     	begin();
        commit();
     }catch (HibernateException e) {
     	e.printStackTrace();
        rollback();
        close();
        return null;
     } finally {
     	flush();
        close();
     }
	 */
	
	@Override
	public List<mSuppliers> getList() {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mSuppliers.class);
			List<mSuppliers> suppliers = criteria.list();
			commit();
			
			return suppliers;
			
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
