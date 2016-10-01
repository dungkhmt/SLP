package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.clientmanagment.model.mClients;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
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
	
	@Override
	public void updateAShipper(mShippers shipper) {
		try {
	           begin();
	           getSession().update(shipper);
	           commit();
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            rollback();
	            close();
	        } finally {
	            flush();
	            close();
	        }
		
	}

	@Override
	public List<mShippers> getByCustomerCode(String cus_code) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mShippers.class);
			criteria.add(Restrictions.eq("SHP_Customer_Code", cus_code));
			List<mShippers> listShippers= criteria.list();
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
	public List<mShippers> getListInBatch(String batchCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			String sql = "SELECT s.SHP_Code, s.SHP_User_Name, s.SHP_CurrentLocation"
					+ " FROM mShippers s, ShipperBatch sb"
					+ " WHERE s.SHP_Code = sb.SHPBAT_ShipperCode and sb.SHPBAT_BatchCode = '"+batchCode+"'";
			List<Object[]> queryResult = getSession().createQuery(sql).list();
			List<mShippers> lsshp = new ArrayList<mShippers>();
			for(int i=0; i<queryResult.size(); i++){
				mShippers tmp = new mShippers();
				tmp.setSHP_Code((String)queryResult.get(i)[0]);
				tmp.setSHP_User_Name((String)queryResult.get(i)[1]);
				tmp.setSHP_CurrentLocation((String)queryResult.get(i)[2]);
				lsshp.add(tmp);
			}
			
			commit();
			return lsshp;
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
