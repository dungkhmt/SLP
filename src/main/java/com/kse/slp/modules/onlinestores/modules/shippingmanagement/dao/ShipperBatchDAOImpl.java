package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.ShipperBatch;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.mRoutes;

@Repository("ShipperBatchDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class ShipperBatchDAOImpl extends BaseDao implements ShipperBatchDAO {

	@Override
	public int saveAShipperBatch(ShipperBatch shipper) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			int id= 0;
			id = (int)getSession().save(shipper);
			commit();
			
			return id;
			
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return 0;
		}finally {
			flush();
			close();
		}
	}

	@Override
	public void removeShipperBatch(String batchCode) {
		// TODO Auto-generated method stub
		System.out.println("shipperbatch::"+batchCode);
		try{
			begin();
			List<ShipperBatch> lstshp = getSession().createCriteria(ShipperBatch.class).add(Restrictions.eq("SHPBAT_BatchCode", batchCode)).list();
			if(lstshp != null){
				for(ShipperBatch shp : lstshp){
					getSession().delete(shp);
				}
			}
			commit();
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
		}finally{
			flush();
			close();
		}
	}

	@Override
	public List<String> getShippersInBatch(String batch) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(ShipperBatch.class);
			criteria.add(Restrictions.eq("SHPBAT_BatchCode", batch));
			criteria.setProjection(Projections.property("SHPBAT_ShipperCode"));

			List<String> r= criteria.list();
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
