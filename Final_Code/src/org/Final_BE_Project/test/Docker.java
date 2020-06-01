package org.Final_BE_Project.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.omg.CORBA.portable.InputStream;

public class Docker 
{

	public int createContainer(String url_a,String input)
	{
		int responseCode=0;
		try 
		{
			URL url = new URL(url_a);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			/*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) 
			{
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}*/

			responseCode=conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("---------------Output from Server---------------\n");
			
			while ((output = br.readLine()) != null) 
			{
				System.out.println(output);
			}

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
	
	public int startContainer(String url_a)
	{
		int responseCode=0;
		try
		{
			URL url = new URL(url_a);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			//String input = "{\"PortBindings\":{\"8888/tcp\":[{\"HostPort\":\"8888\"}]}}";

/*			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) 
			{
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}*/
			
			responseCode=conn.getResponseCode();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) 
			{
				System.out.println(output);
			}

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
	
	public int copyFile(String url_a,String path_a)
	{
		int responseCode=0;
		  try 
		  {

			URL url = new URL(url_a);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/x-tar");
			
		
			Path path = Paths.get(path_a);
			byte[] data = Files.readAllBytes(path);
			
			DataOutputStream d=new DataOutputStream(conn.getOutputStream());
			d.write(data);;
			d.flush();

			/*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) 
			{
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}*/
			
			responseCode=conn.getResponseCode();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) 
			{
				System.out.println(output);
			}

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
	
	public int build(String url_a,String path_a)
	{
		//String url_a="http://localhost:2375/build?t=xyz97";
		//String path_a="/root/workspace/Docker2/Dockerfile.tar.gz";
		
		int responseCode=0;
		
		try 
		{
			
			URL url = new URL(url_a);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/tar");
			
		
			Path path = Paths.get(path_a);
			byte[] data = Files.readAllBytes(path);
			
			DataOutputStream d=new DataOutputStream(conn.getOutputStream());
			d.write(data);;
			d.flush();

		/*	if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) 
			{
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}*/
			
			
			responseCode=conn.getResponseCode();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server-----\n");
			
			int flag=0;
			
			while ((output = br.readLine()) != null) 
			{
				if(output.contains("error"))
				{
					System.out.println("Error in building!!");
				}
				else
				{
				System.out.println(output);
				}
			}
			
			/*if(flag==1)
			{
				System.out.println("Error in building!!");
			}*/

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
	
	
	
	
	
/*public static void main(String[] args) 
	{
		Docker d=new Docker();
		String data="{\"Image\": \""+containerName+"\",\"PortBindings\": { \"8888/tcp\": [{ \"HostPort\": \"8888\" }]}, \"PublishAllPorts\": true}";
		String urlRun="http://"+ip+":2375/containers/"+containerName+"/start";
		
		int i=d.createContainer(, )
	}*/

	
	
}
