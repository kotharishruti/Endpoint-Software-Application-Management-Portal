package org.Final_BE_Project.test;

import java.util.ArrayList;

public class Container 
{
	String id;
	String name;
	String port;
	
	
	public Container(String id,String n,String port)
	{
		this.id=id;
		this.name=n;
		this.port=port;
	}


	@Override
	public String toString() {
		return "Container [id=" + id + ", name=" + name + ", port=" + port + "]";
	}
	
	

}
