package com.kse.slp.modules.onlinestores.modules.shippingmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.onlinestores.modules.shippingmanagement.model.infoAutoRouteElement;


@Repository("InfoAutoRouteElementDAO")
@SuppressWarnings({"unchecked","rawtypes"})
public class InfoAutoRouteElementDAOImpl extends BaseDao implements InfoAutoRouteElementDAO{

	@Override
	public List<infoAutoRouteElement> getList(String routeCode) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			String sql = "SELECT mo.O_ClientCode, mo.O_DeliveryAddress, mo.O_OrderDate, mo.O_DueDate, mo.O_TimeEarly, mo.O_TimeLate,"
						+ " mo.O_DeliveryLat, mo.O_DeliveryLng, mrd.RTD_Sequence"
					+ " FROM mRouteDetail mrd, mOrders mo"
					+ " WHERE mrd.RTD_OrderCode = mo.O_Code and"
						+ " mrd.RTD_RouteCode = '"+routeCode+"'"
					+ " ORDER BY mrd.RTD_Sequence ASC";
			List<Object[]> sql_result = getSession().createQuery(sql).list();
			List<infoAutoRouteElement> lst = new ArrayList<infoAutoRouteElement>();
			for(int i=0; i<sql_result.size(); i++){
				infoAutoRouteElement tmp = new infoAutoRouteElement();
				tmp.setClientCode((String) sql_result.get(i)[0]);
				tmp.setClientAddress((String) sql_result.get(i)[1]);
				String expectedTime = (String)sql_result.get(i)[2] + " " +(String)sql_result.get(i)[4] + ">" 
							+ (String)sql_result.get(i)[3] +" "+ (String)sql_result.get(i)[5];
				tmp.setExpectedTime(expectedTime);
				tmp.setAddLat((float)sql_result.get(i)[6]);
				tmp.setAddLng((float)sql_result.get(i)[7]);
				tmp.setRouteSequence((int)sql_result.get(i)[8]);
				//System.out.println(tmp.toString());
				lst.add(tmp);
			}
			
			commit();
			return lst;
			
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
