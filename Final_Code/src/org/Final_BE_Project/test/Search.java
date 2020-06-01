package org.Final_BE_Project.test;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Search {

	public boolean findSoftware(String recipe)
	{
		try{
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/datab", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from software where soft_name = '"+recipe+"';");
		   boolean more = rs.next();
	       System.out.println(rs.next());
	         // if user does not exist set the isValid variable to false
	         if (!more) 
	         {
	            System.out.println("software not present");
	            return false;
	           // st.executeUpdate("insert into softwares values ('"+recipe+"');");
	         } 
	         
	         else{
	        	 System.out.println("software present");
	        	 return true;
	         }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Search s = new Search();
		s.findSoftware("nginx");
		
	}

}
