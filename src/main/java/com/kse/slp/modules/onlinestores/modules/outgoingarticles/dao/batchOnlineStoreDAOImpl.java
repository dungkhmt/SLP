package com.kse.slp.modules.onlinestores.modules.outgoingarticles.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.record.chart.BeginRecord;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.model.Road;
import com.kse.slp.modules.onlinestores.common.Constants;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.batchOnlineStore;
import com.kse.slp.modules.onlinestores.modules.outgoingarticles.model.mOrders;
import com.kse.slp.modules.usermanagement.model.User;

@Repository("batchOnlineStoreDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class batchOnlineStoreDAOImpl extends BaseDao implements batchOnlineStoreDAO {
	@Autowired
    private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public batchOnlineStore getREQBAT_ID(int REQBAT_ID) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(batchOnlineStore.class);
			criteria.add(Restrictions.eq("REQBAT_ID", REQBAT_ID));
			List<batchOnlineStore> bos= criteria.list();
			commit();
			return bos.get(0);
		} catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally {
			flush();
			close();
		}
	};
	
	@Override
	public List<batchOnlineStore> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(batchOnlineStore.class);
			//criteria.add(Restrictions.ne("O_Status_Code",Constants.ORDER_STATUS_DELIVERIED ));
			List<batchOnlineStore> listBatchOnlineStore = criteria.list();
			commit();
			return listBatchOnlineStore;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	};
	
	@Override
	public List<batchOnlineStore> getList(String CustomerCode) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(batchOnlineStore.class);
			criteria.add(Restrictions.eq("REQBAT_CustomerCode", CustomerCode));
			List<batchOnlineStore> listBatchOnlineStore = criteria.list();
			commit();
			return listBatchOnlineStore;
			
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	};
	
	@Override
	public int addBatchOnlineStore(batchOnlineStore newBatch) {
		
		try{
			begin();
			int id = 0;
			id = (int)getSession().save(newBatch);
			commit();
			return id;
		} catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return -1;
		}finally {
			flush();
			close();
		}
	};
	
	@Override
	public void updateBatchOnlineStore( int REQBAT_ID,String REQBAT_Description) {
		try{
			begin();
			String hql = "UPDATE batchOnlineStore set REQBAT_Description = :REQBAT_Description "  + 
		             "WHERE REQBAT_ID = :REQBAT_ID";
			Query query = getSession().createQuery(hql);
			query.setParameter("REQBAT_Description", REQBAT_Description);
			query.setParameter("REQBAT_ID", REQBAT_ID);
			int result = query.executeUpdate();
			System.out.println("pancel Rows affected: " + result);
			commit();
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
		}finally{
			flush();
			close();
		}
	};
	
	@Override
	public void deleteBatchOnlineStore(int batchCode) {
		try{
			begin();
			List<batchOnlineStore> lstbos = getSession().createCriteria(batchOnlineStore.class).add(Restrictions.eq("REQBAT_ID", batchCode)).list();
			if(lstbos != null){
				for(batchOnlineStore bos : lstbos){
					getSession().delete(bos);
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
	};

}
