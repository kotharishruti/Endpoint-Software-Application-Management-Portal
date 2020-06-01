package org.Final_BE_Project.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Try {

	
	public int checkSoftware(String sname)
	{
		 try {
			System.out.println("checking in yum repo");
				
			Process p = Runtime.getRuntime().exec("/home/esamp/chef-repo/check.sh " +sname);
				InputStream is = p.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String s="";
				while((s=br.readLine())!=null)
				{
					//s.concat(s);
					if(s.contains("No matches found "))
					{
						System.out.println("No matches found");
						
						
						return 1;
					}
					
					else
					{
						System.out.println("match found");
					
					}
					System.out.println(s);
				}
				//System.out.println(s);
				//if(s.contains("1.1.5.26"))
				//{
					//System.out.println("match found");
				//}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return 0;
			
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
