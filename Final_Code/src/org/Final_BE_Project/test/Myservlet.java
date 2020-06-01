package org.Final_BE_Project.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Myservlet extends HttpServlet {
	@Override
	 protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("act");
	
		Service ser = new Service();
		System.out.println(action);
		switch(action)
		{
		case("oslist"):
		{
			Os[] list = ser.getOs();
			String os = request.getParameter("osid");
			System.out.println(os);
		
			try{
			JSONArray jsArray = new JSONArray(list);
		
			response.setContentType("application/json");
			response.getWriter().println(jsArray.toString());
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			break;
		}
		
		case("softwarelist"):
		{
			String oid = request.getParameter("osid");
			Software[] list = ser.getSoftware(oid);
		
			try{
				JSONArray jsArray = new JSONArray(list);

				response.setContentType("application/json");
				response.getWriter().println(jsArray.toString());

				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			break;
		}
	
		case("search"):
		{
			String osid = request.getParameter("osid");
			String sf = request.getParameter("softwarname");

			Software[] list = ser.searchSoftware(osid,sf);
		
			if(list!=null)
			{
			try{
				JSONArray jsArray = new JSONArray(list);
				
				response.setContentType("application/json");
				response.getWriter().println(jsArray.toString());

				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			else
			{
				JSONObject jo = new JSONObject();
				try {
					jo.put("sname","null");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("not present");
				JSONArray jsArray = new JSONArray();
				jsArray.put(jo);
	
				response.setContentType("application/json");
				response.getWriter().write(jsArray.toString());
	}
			break;
	
			
		}
		
		case("submitsoftware"):
		{
			try
			{
			String modd =  request.getParameter("mode");
			String sid =  request.getParameter("softwarid");
			String sname =  request.getParameter("softwarname");
			String oid =  request.getParameter("osid");
			String oname = request.getParameter("osname");
			JSONObject jo = new JSONObject();
			jo.put("mode",modd);
			jo.put("osid", oid);
			jo.put("sname", sname);
			jo.put("sid", sid);
			jo.put("oname", oname);

			JSONArray jsArray = new JSONArray();
			jsArray.put(jo);

			response.setContentType("application/json");
			response.getWriter().write(jsArray.toString());

			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			break;
			
		}
		
		case ("submit"):
		{
			String modd =  request.getParameter("mode");
			String sid =  request.getParameter("softwarid");
			String sname =  request.getParameter("softwarname");
			String oid =  request.getParameter("osid");
			String oname = request.getParameter("osname");
			String rootpass = request.getParameter("root");
			String ip = request.getParameter("ip");
			if(ip =="")
			{
				ip = request.getRemoteAddr();
			}
			String node = "N"+ip;
			Chef ch = new Chef();
			int error= ch.runFile(ip, rootpass, node, sname);

			if(error == 0)
					{
			JSONObject jo = new JSONObject();
			try {
				jo.put("error",0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray jsArray = new JSONArray();
			jsArray.put(jo);
		
			response.setContentType("application/json");
			response.getWriter().write(jsArray.toString());
		
					}
			else{
				JSONObject jo = new JSONObject();
				try {
					jo.put("error",1);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONArray jsArray = new JSONArray();
				jsArray.put(jo);
			
				response.setContentType("application/json");
				response.getWriter().write(jsArray.toString());
			
				
			}
			//response.sendRedirect("thankyou.html");
			
		break;
		}
		case ("submitapp"):
		{
			String o_name = request.getParameter("osname");
			try{
			JSONObject jo = new JSONObject();
			jo.put("oname", o_name);
			JSONArray jsArray = new JSONArray();
			jsArray.put(jo);
			System.out.println(jsArray.toString());
			
			

			response.setContentType("application/json");
			response.getWriter().write(jsArray.toString());
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			break;
		}
		
		case("deploy"):
		{
			String modd =  request.getParameter("mode");
			String env =  request.getParameter("env");
			String ver =  request.getParameter("ver");
			String oid =  request.getParameter("osid");
			String oname = request.getParameter("osname");
			String rootpass = request.getParameter("rootpasswordD");
			String ip = request.getParameter("ipD");
			String file = request.getParameter("fil");
			String user = request.getParameter("user");
		
			DockerModule t=new DockerModule();
			int er=0;
			t.setData(env, ver, file, user, rootpass, ip);
			er=t.check();
			String port = t.getStatus();
			System.out.println("port no is:"+port);
			
			if(er == 200){
			
			JSONObject jo = new JSONObject();
			try {
				jo.put("error",200);
				jo.put("port", port);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray jsArray = new JSONArray();
			jsArray.put(jo);
		
			response.setContentType("application/json");
			response.getWriter().write(jsArray.toString());
			}
			else{

				JSONObject jo = new JSONObject();
				try {
					jo.put("error",300);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONArray jsArray = new JSONArray();
				jsArray.put(jo);
			
				response.setContentType("application/json");
				response.getWriter().write(jsArray.toString());
		}
			
			
		}
		break;
		
		case ("addnew"):
		{
		
		try{
			String os = request.getParameter("osname");
			
			JSONObject jo = new JSONObject();
			jo.put("oname", os);
			JSONArray jsArray = new JSONArray();
			jsArray.put(jo);
			response.getWriter().write(jsArray.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			break;
		}	
		case("submitnew"):
		{
			String osid =  request.getParameter("osid");
			String sname = request.getParameter("soname");
			String url = request.getParameter("url");
			String path = request.getParameter("path");
			String dep = request.getParameter("dep");
			String com = request.getParameter("com");
			String ip1 = request.getParameter("ip1");
			String pass1 = request.getParameter("pass1");
			
			Search s1 = new Search();
			if(s1.findSoftware(sname))
			{

				JSONObject jo = new JSONObject();
				try {
					jo.put("error",0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONArray jsArray = new JSONArray();
				jsArray.put(jo);
			
				response.setContentType("application/json");
				response.getWriter().write(jsArray.toString());
				break;
			}
			
			if(dep == "")
			{
				dep="no";
			}
			if(url==""&&path==""&&com=="")
			{
				if(dep.contains("no"))
				{
					System.out.println("sushant");
				Chef ch = new Chef();
				int error = ch.runNew(sname,osid);
				if (error == 1)
				{
					JSONObject jo = new JSONObject();
					try {
						jo.put("error",1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONArray jsArray = new JSONArray();
					jsArray.put(jo);
				
					response.setContentType("application/json");
					response.getWriter().write(jsArray.toString());
					System.out.println(jsArray);
				
				}
		
				else
				{
					JSONObject jo = new JSONObject();
					try {
						jo.put("error",2);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONArray jsArray = new JSONArray();
					jsArray.put(jo);
				
					response.setContentType("application/json");
					response.getWriter().write(jsArray.toString());
				}
				}
				else
				{
			
					
					Chef ch = new Chef();
					int error=ch.runNewDep(sname, osid);
					if (error == 1)
					{
						JSONObject jo = new JSONObject();
						try {
							jo.put("error",1);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JSONArray jsArray = new JSONArray();
						jsArray.put(jo);
					
						response.setContentType("application/json");
						response.getWriter().write(jsArray.toString());
					}
				
					
				
					
					else
					{
						JSONObject jo = new JSONObject();
						try {
							jo.put("error",2);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JSONArray jsArray = new JSONArray();
						jsArray.put(jo);
					
						response.setContentType("application/json");
						response.getWriter().write(jsArray.toString());
						System.out.println(jsArray);
					}
				}
			}
			else if(url == "" && com == "")
			{
				Chef ch = new Chef();
				int error=ch.runPath(sname, osid);
				//int error = ch.runNewDep(sname,osid);
				if (error == 1)
				{
					JSONObject jo = new JSONObject();
					try {
						jo.put("error",1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONArray jsArray = new JSONArray();
					jsArray.put(jo);
				
					response.setContentType("application/json");
					response.getWriter().write(jsArray.toString());
					System.out.println(jsArray);
				}
			
				
			
				
				else
				{
					JSONObject jo = new JSONObject();
					try {
						jo.put("error",2);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONArray jsArray = new JSONArray();
					jsArray.put(jo);
				
					response.setContentType("application/json");
					response.getWriter().write(jsArray.toString());
					System.out.println(jsArray);
				}
			}
			else if(com == "" && path == "")
			{
				Chef ch = new Chef();
				int error=ch.runUrl(sname, osid);
				if (error == 1)
				{
					JSONObject jo = new JSONObject();
					try {
						jo.put("error",1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONArray jsArray = new JSONArray();
					jsArray.put(jo);
				
					response.setContentType("application/json");
					response.getWriter().write(jsArray.toString());
				}
				else
				{
					JSONObject jo = new JSONObject();
					try {
						jo.put("error",2);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONArray jsArray = new JSONArray();
					jsArray.put(jo);
				
					response.setContentType("application/json");
					response.getWriter().write(jsArray.toString());
					System.out.println(jsArray);
				}
			}
			else if(path == "" && url == "" )
			{
				System.out.println("run commandssssss");
				System.out.println(com);
				Chef ch = new Chef();
				ch.runCommands(com, ip1, pass1);
				
			}
			
			break;
		}
		case ("help"):
		{
			String act = request.getParameter("act");
			JSONObject jo = new JSONObject();
			try {
				jo.put("act", act);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray jsArray = new JSONArray();
			jsArray.put(jo);

			response.setContentType("application/json");
			response.getWriter().write(jsArray.toString());
			break;
		}
		
		
	        	case ("about"):
	    		{
	    			String act = request.getParameter("act");
	    			JSONObject jo = new JSONObject();
	    			try {
	    				jo.put("act", act);
	    			} catch (JSONException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			JSONArray jsArray = new JSONArray();
	    			jsArray.put(jo);

	    			response.setContentType("application/json");
	    			response.getWriter().write(jsArray.toString());
	    		}
	        	break;
	    		}
		}


	}		
