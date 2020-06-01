package org.Final_BE_Project.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InstanceHandling 
{
	
	public void serialize(int instance,String ip)
	{
		  try
	      {
	         FileOutputStream fileOut =new FileOutputStream("/home/esamp/workspace/Final_BE_Project/"+ip+".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(instance);
	         out.close();
	         fileOut.close();
	     
	      }
	      catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public int deserialize(int instance,String ip)
	{
		try
	    {
	         FileInputStream fileIn = new FileInputStream("/home/esamp/workspace/Final_BE_Project/"+ip+".ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         try 
	         {
				instance=(int) in.readObject();
	         }
	         catch (ClassNotFoundException e) 
	         {
	        	 e.printStackTrace();
	         }
	         in.close();
	         fileIn.close();
	         return instance;
	    }
		catch(IOException i)
	    {
			i.printStackTrace();
		
	    }
		
		System.out.println(instance);
		return 0;
	}
	
	
	
	/*public static void main(String args[])
	{
		
		
		
		
	}*/
	


}
