package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;

@Repository("mShippersDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class mShippersDAOImpl extends BaseDao implements mShippersDAO {

	@Override
	public List<mShippers> getList() {
		// TODO Auto-generated method stub
		try{
			
			begin();
			Criteria criteria = getSession().createCriteria(mShippers.class);
			List<mShippers> listShippers = criteria.list();
			commit();
			return listShippers;
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

	@Override
	public mShippers loadShiperByUserName(String userName) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mShippers.class);
			criteria.add(Restrictions.eq("SHP_User_Name", userName));
			List<mShippers> o= criteria.list();
			commit();
			if(o.size()>0){
				return o.get(0);
			} else
			return null;
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
