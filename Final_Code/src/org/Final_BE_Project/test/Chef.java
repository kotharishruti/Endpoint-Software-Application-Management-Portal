package org.Final_BE_Project.test;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class Chef{

	public static int runFile(String ip,String root,String node,String recipe)
	
	{
	
		String path = "/home/esamp/chef-repo/myscript.sh";
		File file = new File(path);
		if (! file.exists()) {
		   throw new IllegalArgumentException("The file " + path + " does not exist");
		}
		try{
			System.out.println(recipe);
		Process p = Runtime.getRuntime().exec("/home/esamp/chef-repo/myscript.sh " +ip+" "+root+" "+node+" "+recipe);
		
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s="";
		while((s=br.readLine())!=null)
		{if(s.contains("(exit code 1)"))
		{
			System.out.println("error in running");
			
			
			return 0;
		}
			System.out.println(s);
		}
	     
		}
		catch (IOException e) {
        System.out.println(e.getMessage());
    }
		return 1;
		
		
	}
		public int runNew(String recipe,String osid)
		{
			Try t1 = new Try();
			if(t1.checkSoftware(recipe) == 1)
			{
				return 1;
			}
			
			else
		{
			String path = "/home/esamp/chef-repo/mydynamic.sh";
			File file = new File(path);
			if (! file.exists()) {
			   throw new IllegalArgumentException("The file " + path + " does not exist");
			}
			try{
				
			Process p = Runtime.getRuntime().exec("/home/esamp/chef-repo/mydynamic.sh " +recipe);
			
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s="";
			while((s=br.readLine())!=null)
			{
				System.out.println(s);
			}
	
			System.out.println("hellloooooo");
			int i = 0;
			String url = "jdbc:mysql://localhost/datab";
			String user = "root";
			String pass = "root";
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from software");
			while(rs.next())
			{
				i++;
			}
			i++;
			System.out.println(i);
			stmt.executeUpdate("insert into software values ("+i+",'"+recipe+"',"+osid+",'1.0.0');");
System.out.println("Done");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
 }
		return 0;

		}
		
		public int runNewDep(String recipe,String osid)
		{
			Try t1 = new Try();
			if(t1.checkSoftware(recipe) == 1)
			{
				return 1;
			}
			
			else
		{
			String path = "/home/esamp/chef-repo/dependency.sh";
			File file = new File(path);
			if (! file.exists()) {
			   throw new IllegalArgumentException("The file " + path + " does not exist");
			}
			try{
				
			Process p = Runtime.getRuntime().exec("/home/esamp/chef-repo/dependency.sh " +recipe);
			
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s="";
			while((s=br.readLine())!=null)
			{
				System.out.println(s);
			}
	
			System.out.println("hellloooooo");
			int i = 0;
			String url = "jdbc:mysql://localhost/datab";
			String user = "root";
			String pass = "root";
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from software");
			while(rs.next())
			{
				i++;
			}
			i++;
			System.out.println(i);
			stmt.executeUpdate("insert into software values ("+i+",'"+recipe+"',"+osid+",'1.0.0';");
System.out.println("Done");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
 }
			return 0;
		}
			
		public int runPath(String recipe,String osid)
		{
			Try t1 = new Try();
			if(t1.checkSoftware(recipe) == 1)
			{
				return 1;
			}
			
			else
		{
			String path = "/home/esamp/chef-repo/path.sh";
			File file = new File(path);
			if (! file.exists()) {
			   throw new IllegalArgumentException("The file " + path + " does not exist");
			}
			try{
				
			Process p = Runtime.getRuntime().exec("/home/esamp/chef-repo/path.sh " +recipe);
			
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s="";
			while((s=br.readLine())!=null)
			{
				System.out.println(s);
			}
	
			System.out.println("hellloooooo");
			int i = 0;
			String url = "jdbc:mysql://localhost/datab";
			String user = "root";
			String pass = "root";
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from software");
			while(rs.next())
			{
				i++;
			}
			i++;
			System.out.println(i);
			stmt.executeUpdate("insert into software values ("+i+",'"+recipe+"',"+osid+",'1.0.0';");
System.out.println("Done");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
 }
			return 0;
		}
		
		public int runUrl(String recipe,String osid)
		{
			Try t1 = new Try();
			if(t1.checkSoftware(recipe) == 1)
			{
				return 1;
			}
			
			else
		{
			String path = "/home/esamp/chef-repo/url.sh";
			File file = new File(path);
			if (! file.exists()) {
			   throw new IllegalArgumentException("The file " + path + " does not exist");
			}
			try{
				
			Process p = Runtime.getRuntime().exec("/home/esamp/chef-repo/url.sh " +recipe);
			
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s="";
			while((s=br.readLine())!=null)
			{
				System.out.println(s);
			}
	
			System.out.println("hellloooooo");
			int i = 0;
			String url = "jdbc:mysql://localhost/datab";
			String user = "root";
			String pass = "root";
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from software");
			while(rs.next())
			{
				i++;
			}
			i++;
			System.out.println(i);
			//stmt.executeUpdate("insert into software values ("+i+",'"+recipe+"',"+osid+",'1.0.0';");
System.out.println("Done");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
 }
			return 0;
		}
		
		
			public int runCommands(String com,String ip,String root)
			
			{
				System.out.println("user com");
				System.out.println(com);
				String path = "/home/esamp/chef-repo/user_commands.sh";
				File file = new File(path);
				if (! file.exists()) {
				   throw new IllegalArgumentException("The file " + path + " does not exist");
				}
				try{
		
				Process p = Runtime.getRuntime().exec("/home/esamp/chef-repo/user_commands.sh "+ip+" "+root+" "+com);
				
				InputStream is = p.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String s="";
				while((s=br.readLine())!=null)
				{
					System.out.println(s);
				}
			
			     
				}
				catch (IOException e) {
		        System.out.println(e.getMessage());
		    }
				return 0;
				
				
			
		

		}

}
