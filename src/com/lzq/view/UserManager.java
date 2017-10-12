package com.lzq.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzq.model.SqlHelper;
import com.lzq.model.UserInfo;
import com.lzq.model.UserService;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class UserManager extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//定义SqlHelper
		SqlHelper sh=new SqlHelper();
		//定义UserService
		UserService us=new UserService();
		//定义UserInfo
		UserInfo ui=new UserInfo();
		//定义分页的三个变量
		int pageNow=1;
		int pageSize=3;
		int pageCount=1;
		int rowCount=1;
		
		//接收页面反馈当前页及跳转页
		String tem_pageNow=(String) request.getParameter("pageNow");
		if(tem_pageNow!=null)
		{
			pageNow=Integer.parseInt(tem_pageNow);
		}
		System.out.println("pageNow="+pageNow);
		String tem_pageTo=request.getParameter("pageTo");
		if(tem_pageTo!=null)
		{
			pageNow=Integer.parseInt(tem_pageTo);
		}
		//查询用户数及分页数
		rowCount=us.queryRowCount();
		pageCount=(rowCount-1)/pageSize+1;
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function confirmOper(){return window.confirm('确定要删除该用户吗？');}");
		out.println("</script>");
		out.print("<center><h1>用户管理</h1></center>");
		out.println("<center><table border='1' cellspacing='0'></center>");
		out.println("<tr><th>用户ID</th><th>姓名</th><th>性别</th><th>年龄</th><th>E-mail</th><th>删除用户</th><th>修改用户</th></tr>");
		
		//对用户信息进行分页显示
		ArrayList al= us.UserInfoSplitPage(pageNow, pageSize);
		Iterator<UserInfo> it=al.iterator();
		while(it.hasNext())
		{
			ui=it.next();
			out.println("<tr><td>"+ui.getId()+
					"</td><td>"+ui.getName()+
					"</td><td>"+ui.getSex()+
					"</td><td>"+ui.getAge()+
					"</td><td>"+ui.getEmail()+
					"</td><td><a onclick='return confirmOper()'  " +
					"href='/UserManager/UserInfoCUD?type=del&id="+ui.getId()+"'>删除用户" +
							"</a></td><td><a href='/UserManager/UserInfoCUD?type=gotoUpd&id="+ui.getId()+"'>修改用户</a></td></tr>");
		}
		
		out.println("</table>");
		out.println("<a href='/UserManager/UserManager?pageNow="+1+"'>"+"<< "+"</a>");
		out.println("&nbsp &nbsp");
		if(pageNow!=1)
		{
			out.println("<a href='/UserManager/UserManager?pageNow="+(pageNow-1)+"'>"+"<"+"</a> &nbsp ");
		}
		for(int i=1;i<=pageCount;i++)
		{
			out.println("<a href='/UserManager/UserManager?pageNow="+i+"'>"+i+"</a>");
		}
		if(pageNow!=pageCount)
		{
			out.println("&nbsp <a href='/UserManager/UserManager?pageNow="+(pageNow+1)+"'>"+">"+"</a>");
		}
		out.println("&nbsp;&nbsp<a href='/UserManager/UserManager?pageNow="+pageCount+"'>"+">>"+"</a>");
		out.println("&nbsp;&nbsp;当前第"+pageNow+"页&nbsp;&nbsp;总共"+pageCount+"页");
		out.println("<form action=/UserManager/UserManager method='post'>");
		out.println("跳转到&nbsp<input type='text' name='pageTo' style='width:30px;' />");
		out.println("<input type='submit' value='GO'/></br>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
