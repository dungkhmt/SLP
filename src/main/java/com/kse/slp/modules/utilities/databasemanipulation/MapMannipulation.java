package com.kse.slp.modules.utilities.databasemanipulation;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MapMannipulation {
	public String DRIVER = "com.mysql.jdbc.Driver";
	public String PATH = "jdbc:mysql://localhost/slp";
	public PrintWriter log = null;
	
	
	public void listRoadSegments(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tblroadsegments";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String segCode = rs.getString("RSEG_Code");
				double distance = rs.getDouble("RSEG_Distance");
				System.out.println(segCode + "\t" + distance);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void removeSegmentRoads(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "delete from tblroadsegments";
			Statement st = cn.createStatement();
			st.execute(sql);			
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void setStatusRoads(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "update tblroads setRoadStatus = 'NOT_PROCESSED'";
			Statement st = cn.createStatement();
			st.execute(sql);			
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapMannipulation m = new MapMannipulation();
		m.listRoadSegments();
	}

}
