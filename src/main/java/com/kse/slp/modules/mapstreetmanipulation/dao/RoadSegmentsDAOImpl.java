package com.kse.slp.modules.mapstreetmanipulation.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kse.slp.dao.BaseDao;
import com.kse.slp.modules.mapstreetmanipulation.model.Point;
import com.kse.slp.modules.mapstreetmanipulation.model.Road;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadPoint;
import com.kse.slp.modules.mapstreetmanipulation.model.RoadSegment;

@Repository("RoadSegmentsDAOImpl")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoadSegmentsDAOImpl extends BaseDao implements RoadSegmentsDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public List<RoadSegment> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(RoadSegment.class);
			List<RoadSegment> list = criteria.list();
			commit();
			return list;
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
	public List<RoadSegment> getSegmentInRange(Point ne, Point sw) {
		// TODO Auto-generated method stub
		try{
			begin();
			
			String sql = "SELECT DISTINCT rs.RSEG_ID ,rs.RSEG_Code,rs.RSEG_FromPoint,rs.RSEG_ToPoint,rs.RSEG_Distance,rs.RSEG_Speed,rs.RSEG_Bidirectional,rp.RP_LatLng"
				
					+ " FROM  RoadSegment rs, RoadPoint rp "
					+ " WHERE rs.RSEG_FromPoint = rp.RP_Code "
					+"UNION "
					+"SELECT DISTINCT rs.RSEG_ID ,rs.RSEG_Code,rs.RSEG_FromPoint,rs.RSEG_ToPoint,rs.RSEG_Distance,rs.RSEG_Speed,rs.RSEG_Bidirectional,rp.RP_LatLng"
					+ " FROM  RoadSegment rs, RoadPoint rp "
					+ "WHERE rs.RSEG_ToPoint = rp.RP_Code";
				
			List<Object[]> sql_result = getSession().createQuery(sql).list();
			
			List<RoadSegment> res= new ArrayList<RoadSegment>();
			
			for(int i=0;i<sql_result.size();i++){
				System.out.println(name()+(int)sql_result.get(i)[2]+" "+(int)sql_result.get(i)[3]);
				RoadSegment s= new RoadSegment();
				String latlng =(String) sql_result.get(i)[7];
				String[] llng= latlng.split(",");
				double lat= Double.parseDouble(llng[0]);
				double lng= Double.parseDouble(llng[1]);
				if(lat >= ne.getLat() || lng >= ne.getLng() || lat <=sw.getLat() || lng <=sw.getLng()){
					continue;
				}
				s.setRSEG_ID((int)sql_result.get(i)[0]);
				s.setRSEG_Code((int)sql_result.get(i)[1]);
				s.setRSEG_FromPoint((int)sql_result.get(i)[2]);
				s.setRSEG_ToPoint((int)sql_result.get(i)[3]);
				s.setRSEG_Distance((double)sql_result.get(i)[4]);
				s.setRSEG_Speed((int)sql_result.get(i)[5]);
				s.setRSEG_Bidirectional((String)sql_result.get(i)[6]);
				res.add(s);
			}
			commit();
			//System.out.println(name()+"getLstRTUnderCreation--return result: "+lstRTUnCreation.toString());
			return res;
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

	public int saveASegment(RoadSegment segment) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			int id = 0;
			System.out.println("save segment: "+segment.toString());
			id = (int)getSession().save(segment);
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
	String name(){
		return "RoadSegmentsDAO::";
	}
	@Override
	public List<RoadSegment> getListbyPoint(int code) {
		// TODO Auto-generated method stub
		List<RoadSegment> list;
		try{
			begin();
			System.out.println(name()+"getListbyPoint ::"+code);
			Criteria criteria = getSession().createCriteria(RoadSegment.class);
			criteria.add(Restrictions.eq("RSEG_FromPoint", code));
			list = criteria.list();
			
			commit();
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
		try{
			begin();
			Criteria criteria2 = getSession().createCriteria(RoadSegment.class);
			criteria2.add(Restrictions.eq("RSEG_ToPoint", code));
			List<RoadSegment> list2 = criteria2.list();
			list.addAll(list2);
			commit();
			list.addAll(list2);
			return list;
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
	public void updateASegment(RoadSegment segment) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			getSession().update(segment);
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
	public void deleteSegmentByCode(int code) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			Criteria criteria = getSession().createCriteria(RoadSegment.class);
			criteria.add(Restrictions.eq("RSEG_Code", code));
			List<RoadSegment> segments = criteria.list();
			for(RoadSegment segment : segments){
				getSession().delete(segment);
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
