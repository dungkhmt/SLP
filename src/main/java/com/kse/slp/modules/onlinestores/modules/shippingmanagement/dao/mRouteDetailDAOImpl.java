package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRouteDetail;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mShippers;
@Repository("mRouteDetailDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class mRouteDetailDAOImpl extends BaseDao implements mRouteDetailDAO {

	@Override
	public List<mRouteDetail> loadRouteDetailbyRouteCode(String routeCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mRouteDetail.class);
			criteria.add(Restrictions.eq("RTD_RouteCode", routeCode));
			List<mRouteDetail> r= criteria.list();
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
