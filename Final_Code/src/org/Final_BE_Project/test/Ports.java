package org.Final_BE_Project.test;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Ports 
{
	ArrayList<String> usedP=new ArrayList();
	ArrayList<String> unusedP=new ArrayList();
	
	Ports()
	{
		this.populateUnused();
	}
	
	void populateUnused()
	{
		int i;
		
		for(i=1024;i<3000;i++)
		{
			unusedP.add(Integer.toString(i));
		}
	
	}
	
	void setUsed(String port)
	{
		usedP.add(port);
		removeUnused(port);
	}
	
	void removeUnused(String port)
	{
		Iterator i=unusedP.iterator();
		
		while(i.hasNext())
		{
			if(i.next().equals(port))
			{
				i.remove();
			}
		}
	}
	
	void display()
	{
		Iterator i=usedP.iterator();
		Iterator j=unusedP.iterator();
	
		System.out.println("Used Ports: ");
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		
		System.out.println("UnUsed Ports:");
		while(j.hasNext())
		{
			System.out.println(j.next());
		}
		
	}
	
	public void display1()
	{
		Iterator j=usedP.iterator();
		
		while(j.hasNext())
		{
			System.out.println(j.next());
		}
	}
	
	public String getPort()
	{
		String port=unusedP.get(0);
		setUsed(port);
		return port;
	}
	
	
	
	/*public static void main(String[] args) 
	{
		Ports p=new Ports();
		p.populateUnused();
		//p.display();
		
	}*/

}
