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



public class Servlet extends HttpServlet {
	@Override
	 protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
		String recipeName = request.getParameter("serch");
		String clientIp = request.getRemoteAddr();
		String username = request.getRemoteUser();
		String password = request.getParameter("pass");
		String nodeName = "N"+clientIp;

		response.sendRedirect("thankyou.html");
		
		
		System.out.println(recipeName+" "+clientIp+" "+nodeName+" "+username);
		//Script.runFile(clientIp,password,nodeName,recipeName);
	}		
}