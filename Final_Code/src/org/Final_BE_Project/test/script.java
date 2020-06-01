package org.Final_BE_Project.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class script {
	public static void runNew()
	
	{
		String path = "/home/esamp/chef-repo/myscript.sh";
		File file = new File(path);
		if (! file.exists()) {
		   throw new IllegalArgumentException("The file " + path + " does not exist");
		}
		try{
			
		Process p = Runtime.getRuntime().exec("/home/esamp/chef-repo/mydynamic.sh lighttpd" );
		
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s="";
		while((s=br.readLine())!=null)
		{
			System.out.println(s);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		public static void main(String args[])
		{
			runNew();
		}
		
}
