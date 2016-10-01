package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.Stores;

@Repository("StoresDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class StoresDAOImpl extends BaseDao implements StoresDAO{

	@Override
	public List<Stores> getListStoreInBatch(String batchCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			String sql = "SELECT s.STR_Code, s.STR_Address, s.STR_Name, s.STR_LatLng, s.STR_CustomerCode"
					+ " FROM Stores s, StoreBatch sb"
					+ " WHERE s.STR_Code = sb.STBAT_StoreCode and sb.STBAT_BatchCode = '"+batchCode+"'";
			List<Object[]> queryResult = getSession().createQuery(sql).list();
			
			List<Stores> lstst = new ArrayList<Stores>();
			for(int i=0; i<queryResult.size(); i++){
				Stores tmp = new Stores();
				tmp.setSTR_Code((String)queryResult.get(i)[0]);
				tmp.setSTR_Address((String)queryResult.get(i)[1]);
				tmp.setSTR_Name((String)queryResult.get(i)[2]);
				tmp.setSTR_LatLng((String)queryResult.get(i)[3]);
				tmp.setSTR_CustomerCode((String)queryResult.get(i)[4]);
				lstst.add(tmp);
			}
			commit();
			return lstst;
			
			
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
