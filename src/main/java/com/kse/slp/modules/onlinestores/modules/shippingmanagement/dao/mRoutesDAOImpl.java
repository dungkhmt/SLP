package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;

import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;
@Repository("mRoutesDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class mRoutesDAOImpl extends BaseDao implements mRoutesDAO {

	@Override
	public List<mRoutes> loadRoutebyShipperCode(String shipperCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mRoutes.class);
			criteria.add(Restrictions.eq("Route_Shipper_Code", shipperCode));
			List<mRoutes> r= criteria.list();
			commit();
			return r;
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
