package org.Final_BE_Project.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DockerModule 
{
	static int instance;
	String environment,version,file,username,password,ip;
	String status;
	
	Map<String,String> tomcatDockerfilePaths=new HashMap<String,String>();
	Map<String,String> nodeJSDockerfilePaths=new HashMap<String,String>();
	
	Ports p=new Ports();
	DataHandling d=new DataHandling();
	
//**************************************************************************************************
	
	DockerModule()
	{
		this.populateTomcat();
		this.populateNodeJS();
	}
	

	public DockerModule(String environment, String version, String file, String username, String password,String ip) 
	{
		super();
		this.environment = environment;
		this.version = version;
		this.file = file;
		this.username = username;
		this.password = password;
		this.ip=ip;
		
	}
	
/*------------------------------------------------------------------------------------------------------------------
 * 	Function name	 :	populateTomcat
 * 	Description		 : 	This function will insert the paths of the Dockerfiles corresponding to the tomcat version
 * 						Example: 
 * 						7jre7 - Tomcat version 7 for jre 7
 *  
 * 	Input parameters :	None
 *  Return value	 :	None
 *  	
 */
	public void populateTomcat()
	{
		tomcatDockerfilePaths.put("7jre7","/home/esamp/workspace/Final_BE_Project/Tomcat/Tomcat7_7.tar.gz");
		tomcatDockerfilePaths.put("7jre8","/home/esamp/workspace/Final_BE_Project/Tomcat/Tomcat7_8.tar.gz");
		tomcatDockerfilePaths.put("8jre7","/home/esamp/workspace/Final_BE_Project/Tomcat/Tomcat8_7.tar.gz");
		tomcatDockerfilePaths.put("8jre8","/home/esamp/workspace/Final_BE_Project/Tomcat/Tomcat8_8.tar.gz");
	}
	
//---------------------------------------------------------------------------------------------------------	
	
	
/*------------------------------------------------------------------------------------------------------------------
 * 	Function name	 :	populateNodeJS
 * 	Description		 : 	This function will insert the paths of the Dockerfiles corresponding to the NodeJS
 * 	Input parameters :	None
 *  Return value	 :	None
 *  	
 */	
	public void populateNodeJS()
	{
		nodeJSDockerfilePaths.put("Argon","/home/esamp/workspace/Final_BE_Project/NodeJS/NodeJS_Argon.tar.gz");
		
	}

//---------------------------------------------------------------------------------------------------------	
	
	
/*------------------------------------------------------------------------------------------------------------------
 * 	Function name	 :	setStatus
 * 	Description		 : 	This function will insert the paths of the Dockerfiles corresponding to the NodeJS
 * 	Input parameters :	status-->String
 *  Return value	 :	None
 *  	
 */		
	
	public void setStatus(String status)
	{
		this.status=status;
	}
	
	public String getStatus()
	{
		return status;
	}
		
	public void getData()
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("\n Enter the Environment");
		environment=sc.next();
		System.out.println("\n Enter the Version");
		version=sc.next();
		System.out.println("\n Enter the Path");
		file=sc.next();
		System.out.println("\n Enter the username");
		username=sc.next();
		System.out.println("\n Enter the password");
		password=sc.next();
		System.out.println("\nEnter ip");
		ip=sc.next();
			
	}
	
	public void setData(String environment,String version,String file,String username,String password,String ip)
	{
		this.environment=environment;
		this.version=version;
		this.file=file;
		this.username=username;
		this.password=password;
		this.ip=ip;
		
	}
		
	public int check()
	{
		String url_c="http://"+ip+":2375/containers/json?all=1";
		d.getContainers(url_c);
		d.extract(p);
		
		
		String file=ip+".ser";
		InstanceHandling i=new InstanceHandling();
		
		
		if(new File(file).exists())
		{
			instance=i.deserialize(instance,ip);
			instance++;
			i.serialize(instance, ip);
			
		}
		else
		{
			i.serialize(instance,ip);
		}

		
			if(environment.equals("Tomcat"))
			{
				startTomcat();
				
				i.serialize(instance,ip);
			}
			else if(environment.equals("NodeJS"))
			{
				startNodeJS();
				
				i.serialize(instance, ip);
			}
			
			status=getStatus();
			
			if(!status.contains("Error"))
			{	
				return 200;
			}
			else
			{
				return 0;
			}
			
			
			
			
	}
	
//----------------------------------------------------------------------------------------------------	
		
		public void startTomcat()
		{
			String PATH;
						
			if (tomcatDockerfilePaths.containsKey(version))
			{
				PATH=tomcatDockerfilePaths.get(version);
				buildTomcat(PATH,ip);
			}
			else
			{
				System.out.println("Error. Please enter a correct version");
			}
		}				
			

		public void buildTomcat(String PATH,String ip)
		{
			String imageName="tomcat:"+version;
			String urlBuild="http://"+ip+":2375/build?t="+imageName;
			String containerName;
			
			Docker dockerObject=new Docker();
			
			if(dockerObject.build(urlBuild,PATH)==200)
			{
				System.out.println("Build Successful");
					
				containerName=createTomcat(dockerObject, imageName,ip);
				
				if(containerName.equals(null))
				{
					System.out.println("Error");
				}
				else
				{
					System.out.println("Creation Successful");
					runTomcat(dockerObject,containerName,ip);
				}
							
			}
			else
			{
				System.out.println("Build Unsuccessful");
			}
			
		}

		public String createTomcat(Docker dockerObject,String imageName,String ip)
		{	
			String containerName=version+instance;
		
			String publicPort=p.getPort();
			String urlCreate="http://"+ip+":2375/containers/create?name="+containerName;
			String data="{\"Image\": \""+imageName+"\",\"PortBindings\": { \"8080/tcp\": [{ \"HostPort\": \""+publicPort+"\" }]}, \"PublishAllPorts\": true}";
			
			if(dockerObject.createContainer(urlCreate,data)==201)
			{
				instance++;
				System.out.println(publicPort);
				
				setStatus("Container Name: "+containerName+": Port number :"+publicPort);
				System.out.println("Container created");
				return containerName;
			}
			else if(dockerObject.createContainer(urlCreate,data)==404)
			{
				System.out.println("Creation failed");
			}
			else if(dockerObject.createContainer(urlCreate,data)==500)
			{
				System.out.println("Server error");
			}
			return "Error";
							
			
		}
		
		public void runTomcat(Docker d,String containerName,String ip)
		{
		
			
			String urlRun="http://"+ip+":2375/containers/"+containerName+"/start";
			if(d.startContainer(urlRun)==204)
			{
				System.out.println("Container Started");
				
				if(file.equals(null))
				{
					System.out.println("No file given !!");
				}
				else
				{
					copyToContainer(d,containerName,ip);
				}
			}
			else if(d.startContainer(urlRun)==304)
			{
				System.out.println("Container already started");
			}
			else if(d.startContainer(urlRun)==404)
			{
				System.out.println("No such container");
			}
			else if(d.startContainer(urlRun)==500)
			{
				System.out.println("Server Error");
			}
					
		}
		
		public void copyToContainer(Docker dockerObject,String containerName,String ip)
		{
			String compressedFile;
			
			FileTransfer f=new FileTransfer();
			f.transfer(file, username, password,ip);
			compressedFile=f.compress(file);
			
			String url_a="http://"+ip+":2375/containers/"+containerName+"/archive?path=/usr/local/tomcat/webapps/";
			String path_a="/home/esamp/workspace/Final_BE_Project/"+compressedFile;
			
			if(dockerObject.copyFile(url_a, path_a)==200)
			{
				System.out.println("Copying successful");
			}
			else if(dockerObject.copyFile(url_a, path_a)==400)
			{
				System.out.println("Bad parameters");
			}
			else if(dockerObject.copyFile(url_a, path_a)==404)
			{
				System.out.println("Container not found");
			}
			else if(dockerObject.copyFile(url_a, path_a)==500)
			{
				System.out.println("Server error");
			}
			
			
		}
		
		

//----------------------------------------------------------------------------------------------------		
		
		public void startNodeJS()
		{

			String PATH = null;
						
			if (nodeJSDockerfilePaths.containsKey(version))
			{
				PATH=nodeJSDockerfilePaths.get(version);
				
				FileTransfer f=new FileTransfer();
				f.transfer(file, username, password, ip);
				f.concatenate(PATH,file);
				
				buildNodeJS(PATH, ip);			
			}
			else
			{
				System.out.println("Error. Please enter a correct version");
			}
			
													
		}
		
		public void buildNodeJS(String PATH,String ip)
		{
			String imageName="node:"+version;
			String urlBuild="http://"+ip+":2375/build?t="+imageName;
			String containerName;
			
			Docker dockerObject=new Docker();
			
			if(dockerObject.build(urlBuild, PATH)==200)
			{
				System.out.println("Build Successful");
				containerName=createNodeJS(dockerObject, imageName,ip);
				if(containerName.equals(null))
				{
					System.out.println("Error");
				}
				else
				{
					System.out.println("Creation successful");
					runNodeJS(dockerObject,containerName,ip);
				}
			}
			else
			{
				System.out.println("Build unsuccessful");
			}
			
		}
		
		public String createNodeJS(Docker dockerObject,String imageName,String ip)
		{	
			String containerName=version+instance;
			
			String publicPort=p.getPort();
			String urlCreate="http://"+ip+":2375/containers/create?name="+containerName;
			String data="{\"Image\": \""+imageName+"\",\"PortBindings\": { \"8080/tcp\": [{ \"HostPort\": \""+publicPort+"\" }]}, \"PublishAllPorts\": true}";
			
			if(dockerObject.createContainer(urlCreate,data)==201)
			{
				instance++;
				System.out.println(publicPort);
				setStatus("Container Name: "+containerName+": Port Number :"+publicPort);
				return containerName;
			}
			else if(dockerObject.createContainer(urlCreate,data)==404)
			{
				System.out.println("Creation failed");
			}
			else if(dockerObject.createContainer(urlCreate,data)==500)
			{
				System.out.println("Server error");
			}
			
			return "Error";
							
			
		}
		
		public void runNodeJS(Docker dockerObject,String containerName,String ip)
		{
			if(containerName.isEmpty())
			{
				System.out.println("Container Not created");
			}
			else
			{
			
			String urlRun="http://"+ip+":2375/containers/"+containerName+"/start";
			
			if(dockerObject.startContainer(urlRun)==204)
			{
				System.out.println("Container Started");
			}
			else if(dockerObject.startContainer(urlRun)==304)
			{
				System.out.println("Container already started");
			}
			else if(dockerObject.startContainer(urlRun)==404)
			{
				System.out.println("No such container");
			}
			else if(dockerObject.startContainer(urlRun)==500)
			{
				System.out.println("Server Error");
			}
			else
			{
				System.out.println("Fail");
				status="NULL";
			}
			
			}
		}
		
	
//----------------------------------------------------------------------------------------------------
		
	public static void main(String[] args) 
	{
		String status = null;
		DockerModule t=new DockerModule();
		t.getData();
		t.check();
		System.out.println(t.getStatus());
	}

}
