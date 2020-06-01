package org.Final_BE_Project.test;



import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.jcraft.*;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
@SuppressWarnings("unused")
public class FileTransfer 
{
	
	public void transfer(String filePath,String username,String password,String hostName)
	{
		//String copyTo="/home/ubuntu/workspace/ESAMP/";
		String copyTo="/home/esamp/workspace/Final_BE_Project/";
		JSch jsch = new JSch();
	    Session session = null;
	    
	    try
	    {
			session = jsch.getSession(username, hostName, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			
			sftpChannel.get(filePath, copyTo);
			//sftpChannel.get("/root/sample.war","/home/esamp/workspace/Final_BE_Project/");
			sftpChannel.exit();
			session.disconnect();
	    }
	    catch (JSchException e) 
	    {
	    	e.printStackTrace();
	    }
	    catch (SftpException e) 
	    {
	    	e.printStackTrace();
	    }
				
		
	}
	
	public String compress(String file)
	{
		
		
		Path remFilePath=Paths.get(file);
		String filename=remFilePath.getFileName().toString();
		
		System.out.println(filename);
		     
		String compressedFile="test.tar.gz";
		System.out.println(compressedFile);
		try
		{
			Process p = Runtime.getRuntime().exec("tar czf /home/esamp/workspace/Final_BE_Project/"+compressedFile+" -C /home/esamp/workspace/Final_BE_Project/ "+filename);
		
			//Process p = Runtime.getRuntime().exec("pwd");
			java.io.InputStream is =p.getInputStream();
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
		
		return compressedFile;
		
	}
	
	void concatenate(String p1,String p2)
	{
		try
		{
			Path remFilePath=Paths.get(p2);
			String filename=remFilePath.getFileName().toString();
			String path2="/home/esamp/workspace/Final_BE_Project/"+filename;
			System.out.println(path2);
			
			Process p = Runtime.getRuntime().exec("tar --concatenate --file="+p1+" "+path2);
		//	Process p = Runtime.getRuntime().exec("tar --concatenate --file=/root/workspace/Docker2/NodeJS/NodeJS_Argon.tar.gz /root/workspace/Docker2/Data.tar.gz");
			//Process p = Runtime.getRuntime().exec("pwd");
			java.io.InputStream is =p.getInputStream();
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
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
		
	/*public static void main(String[] args) 
	{
		String filePath="/root/sample.war";
		String username="root";
		String password="root";
		String hostName="1.1.5.11";
	
		
		FileTransfer f=new FileTransfer();
	//	f.compress("/root/workspace/Docker2/sample.war");
		f.transfer(filePath, username, password, hostName);
		//f.compress("/root/sample.war");
	//	f.concatenate();
		
	


	}*/

}
