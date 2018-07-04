package com.crudexample.db;

 

 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.crudexample.models.ISGUser;

public class DBConnection{
	public static final String TOMCAT  = "java:comp/env/";
	public static final String JNDI_HMS = TOMCAT + "jdbc/GENAFF";
	public static List<ISGUser> getAllISGUsers(String name){
		List<ISGUser> list = new ArrayList<ISGUser>();
		if(name == null)
			return list; 
		
		Connection con = getConnectionJNDI(JNDI_HMS); 
		String sqlQuery = "select * from dbo.VR_ISG where name LIKE '%" + name +"%' and flag=0";
		System.out.println(":: Sql Query :: " + sqlQuery);
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while ( rs.next()){
					int id = rs.getInt(1);
					String nm = rs.getString(2);
					String date = rs.getString(3);
					String updateDate = rs.getString(4);
					String sicil = rs.getString(5);
					int flag = rs.getInt(6);

					ISGUser isgUser = new ISGUser(id, nm, date, updateDate, sicil, flag);
					list.add(isgUser);
			};
			rs.close();
		}catch (Exception e){e.printStackTrace();
		}finally{closeConnection(con);}
		return list;
	}
	
	public static int deleteISGUser(String id){
		if(id == null)
			return 0; 
		int result = 0;
		Connection con = getConnectionJNDI(JNDI_HMS); 
		String sqlQuery = "update dbo.VR_ISG set flag=1, update_date=GETDATE(), sicil='T135554' where id="+id;
		System.out.println(":: Sql Query :: " + sqlQuery);
		try{
			Statement stmt = con.createStatement();
			result = stmt.executeUpdate(sqlQuery);
			System.out.println("Result is: " + result);
		}catch (Exception e){e.printStackTrace();
		}finally{closeConnection(con);}
		return result;
		
	}
	
	public static int updateISGUser(String id, String name){
		if(id == null)
			return 0; 
		int result = 0;
		Connection con = getConnectionJNDI(JNDI_HMS); 
		String sqlQuery = "update dbo.VR_ISG set name='"+name+"' , update_date=GETDATE(), sicil='T135554' where id=" + id + " and flag=0";
		System.out.println(":: Sql Query :: " + sqlQuery);
		try{
			Statement stmt = con.createStatement();
			result = stmt.executeUpdate(sqlQuery);
			System.out.println("Result is: " + result);
		}catch (Exception e){e.printStackTrace();
		}finally{closeConnection(con);}
		return result;
		
	}
	
	public static int insertISGUser(String name){
		if(name == null)
			return 0; 
		int result = 0;
		Connection con = getConnectionJNDI(JNDI_HMS); 
		String sqlQuery = "INSERT INTO [dbo].[VR_ISG]([name],[date],[update_date],[sicil],[flag]) VALUES ( '" + name + "', GETDATE(), GETDATE(), 'T135552', 0)";
		System.out.println(":: Sql Query :: " + sqlQuery);
		try{
			Statement stmt = con.createStatement();
			result = stmt.executeUpdate(sqlQuery);
			System.out.println("Result is: " + result);
		}catch (Exception e){e.printStackTrace();
		}finally{closeConnection(con);}
		return result;
		
	}
	public static Connection getConnectionJNDI(String sJNDI)
	{
		Connection conn = null;
		if ( sJNDI == null || sJNDI == "" )
			return null;
		String xJNDI = sJNDI;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(xJNDI);
			conn = ds.getConnection();
		} catch (Exception exc) {
			System.out.println(	"Error with JNDI : " + sJNDI + "..... :" + exc.getMessage());
			conn = null;
		}
		return conn;
	}
	public static void closeConnection(Connection c)
	{
		try {
			if ( c!=null && !c.isClosed() )
				c.close();
		}catch(Exception e) {}
	}
}
