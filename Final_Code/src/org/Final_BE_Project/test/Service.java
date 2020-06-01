package org.Final_BE_Project.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Service {
	

	
	public Os[] getOs()
	{

	
		Os[] o1 = null;
		String url = "jdbc:mysql://localhost/datab";
		String user = "root";
		String pass = "root";
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from os");
		int size = 0;
		while(rs.next())
		{
			size++;
		}
		o1 = new Os[size];
		for(int i=0; i< o1.length;i++)
		    o1[i] = new Os();
		System.out.println(size);
		int i = 0 ;
		ResultSet rs1=stmt.executeQuery("select * from os");
		while(rs1.next()) 
		{ 			
			
		    int x = rs1.getInt(1);
		    String s = rs1.getString(2);
		   o1[i].os_id=x;
		   o1[i].os_name=s;
		    i++;
		    
		    
		}  
		
 		
	}
		 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
			
						
		
		
		return o1;
		
	
	}
	
	
	public Software[] getSoftware(String osid)
	{
		Software[] s1 = null;
		String url = "jdbc:mysql://localhost/datab";
		String user = "root";
		String pass = "root";
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from software where os_id="+osid+";");
		int size = 0;
		while(rs.next())
		{
			size++;
		}
		s1 = new Software[size];
		for(int i=0; i< s1.length;i++)
		    s1[i] = new Software();
		System.out.println(size);
		int i = 0 ;
		ResultSet rs1=stmt.executeQuery("select * from software where os_id="+osid+" ;");
		while(rs1.next()) 
		{ 			
			
		    int x = rs1.getInt(1);
		    String s = rs1.getString(2);
		    int y = rs1.getInt(3);
		  String z=rs1.getString(4);
		   s1[i].os_id=y;
		   s1[i].soft_id=x;
		   s1[i].soft_name=s;
		  s1[i].ver=z;
		    i++;
		    
		    
		}  
		
 		
	}
		 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
			
						
		
		
		return s1;
		
	
	}
	
	
	public Software[] searchSoftware(String osid,String sname)
	{
		Software[] s1 = null;
		String url = "jdbc:mysql://localhost/datab";
		String user = "root";
		String pass = "root";
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,user,pass);
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from software where os_id="+osid+" and  soft_name like '%"+sname+"%';");
		int size = 0;
		while(rs.next())
		{
			size++;
		}
		s1 = new Software[size];
		for(int i=0; i< s1.length;i++)
		    s1[i] = new Software();
		System.out.println(size);
		int i = 0 ;
		if(size>0)
		{
		ResultSet rs1=stmt.executeQuery("select * from software where os_id="+osid+" and soft_name like '%"+sname+"%';");
		while(rs1.next()) 
		{ 			
			
		    int x = rs1.getInt(1);
		    String s = rs1.getString(2);
		    int y = rs1.getInt(3);
		   String z = rs1.getString(4);
		    
		   s1[i].os_id=y;
		   s1[i].soft_id=x;
		   s1[i].soft_name=s;
		   s1[i].ver=z;
		    i++;
		    
		    
		}  
		
		}
		else{
			System.out.println("not");
			s1 = null;

			
		}
	}
		 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
			
						
		
		
		return s1;
		
	
	}
	
	/*public static void main(String args[])
	{
		Service s = new Service();
		Os[] s1 = s.getOs("soft");
		for(int i=0;i<2;i++)
		{
			System.out.println(s1[i].os_id);
			System.out.println(s1[i].os_name);
		}
		
		Software[] s2 = s.getSoftware(2);
		for(int i=0;i<4;i++)
		{
			System.out.println(s2[i].os_id);
			System.out.println(s2[i].soft_name);
			System.out.println(s2[i].soft_id);
		}
		
		
	}*/
}
