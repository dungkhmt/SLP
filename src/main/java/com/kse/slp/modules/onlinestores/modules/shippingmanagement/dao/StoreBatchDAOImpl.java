package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.StoreBatch;

@Repository("StoreBatchDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class StoreBatchDAOImpl extends BaseDao implements StoreBatchDAO {

	@Override
	public int saveAStoreBatch(StoreBatch store) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			int id=0;
			id = (int)getSession().save(store);
			commit();
			return id;
			
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return 0;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public void deleteStoreBatch(String batchCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			List<StoreBatch> lstsb = getSession().createCriteria(StoreBatch.class).add(Restrictions.eq("STBAT_BatchCode", batchCode)).list();
			if(lstsb != null){
				for(StoreBatch sb : lstsb){
					getSession().delete(sb);
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

}
