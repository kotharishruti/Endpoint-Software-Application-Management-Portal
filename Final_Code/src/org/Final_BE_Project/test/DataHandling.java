package org.Final_BE_Project.test;

import java.awt.List;
import java.io.BufferedReader;
import org.apache.*;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.*;

public class DataHandling 
{
	String responseContainer,responseImage;
	
	ArrayList<Container> c=new ArrayList<Container>();
	
	
	
//-----------------------------------------------------------------------------------------------------------	
	
	public int getImages(String url_a)
	{
		int responseCode=0;
		try 
		{
			
			URL url = new URL(url_a);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			
			responseCode=conn.getResponseCode();
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) 
			{
				sb.append(line+"\n");
			}
			br.close();
			
			responseImage= sb.toString();
			//System.out.println(responseImage);
			
			conn.disconnect();

		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();

		}
		return responseCode;
	}
	
	
	public int getContainers(String url_a)
	{
		int responseCode=0;
		try 
		{
			
			URL url = new URL(url_a);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			
			responseCode=conn.getResponseCode();
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) 
			{
				sb.append(line+"\n");
			}
			br.close();
			
			responseContainer= sb.toString();
			//System.out.println(responseContainer);
			
			conn.disconnect();

		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();

		}
		return responseCode;
	}
	
	
	/*public void extract()
	{
		try 
		{
			MultiMap m=new MultiValueMap();
			
			JSONArray a=new JSONArray(responseImage);
			for (int i = 0; i < a.length(); i++) 
			{
				String id = a.getJSONObject(i).getString("Id");
				
				JSONArray tag=a.getJSONObject(i).getJSONArray("RepoTags");
				
				for(int j=0;j<tag.length();j++)
				{
					m.put(id, tag.get(j));
				}
				
										
		    }
			
	        Set<String> keys = m.keySet();

	        // iterate through the key set and display key and values

	        for (String key : keys) {

	            System.out.println("Key = " + key);

	            System.out.println("Values = " + m.get(key));

	        }
			
			
			
			
		
			
			
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/	
	
	
	public void display()
	{
		System.out.println(responseContainer);
	}
	
	
	public void extract(Ports p)
	{		
		//Ports p=new Ports();
		String id=null,n=null;
		
		JSONArray a = null;
		try 
		{
			a = new JSONArray(responseContainer);
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		for (int i = 0; i < a.length(); i++) 
		{
			JSONArray ports = null;
			
			try 
			{
				id = a.getJSONObject(i).getString("Id");
				ports = a.getJSONObject(i).getJSONArray("Ports");
				n=a.getJSONObject(i).getJSONArray("Names").getString(0);
			}
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				p.setUsed(ports.getJSONObject(0).get("PublicPort").toString());
				
				c.add(new Container(id, n,ports.getJSONObject(0).get("PublicPort").toString()));
				
				
			}
			catch (JSONException e) 
			{
				continue;
			}
			
											
		}
		
		System.out.println("------------------------");
	//	p.display();
		
	}
	
	public void displayContainers()
	{
		Iterator i=c.iterator();
		
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
	}
	
//-----------------------------------------------------------------------------------------------------------	
	/*public static void main(String[] args) 
	{
		DataHandling d=new DataHandling();
	
		String url_c="http://localhost:2375/containers/json?all=1";
			
		int j=d.getContainers(url_c);
		System.out.println(j);
//		d.extractI();
		d.display();
		d.extract();
		d.displayContainers();
	}*/

}
