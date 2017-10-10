package com.lzq.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserManager extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//定义分页的三个变量
		int pageNow=1;
		int pageSize=3;
		int pageCount=1;
		int rowCount=1;
		
		
		String tem_pageNow=(String) request.getParameter("pageNow");
		if(tem_pageNow!=null)
		{
			pageNow=Integer.parseInt(tem_pageNow);
		}
		String tem_pageTo=request.getParameter("pageTo");
		if(tem_pageTo!=null)
		{
			pageNow=Integer.parseInt(tem_pageTo);
		}
		System.out.println("pageNow="+pageNow);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("<center><h1>用户管理</h1></center>");
		out.println("<center><table border='1' cellspacing='0'></center>");
		out.println("<tr><th>用户ID</th><th>姓名</th><th>性别</th><th>年龄</th><th>E-mail</th></tr>");
				
		
		
		
				/**
				 * 
				 * //数据库驱动名
				 * String deriver="com.mysql.jdbc.Driver";
				//URL指向的要访问的数据库名userinfo
				String url="jdbc:mysql://127.0.0.1:3306/userinfo";
				//mysql配置时的用户名
				String username="root";
				//mysql配置时的密码
				String password="123456";
				Connection ct=null;
				ResultSet rs=null;
				PreparedStatement ps=null;
				try {
					//1.加载驱动
					Class.forName(deriver);
					try {
						//2.得到链接
						ct=DriverManager.getConnection(url,username,password);
						System.out.println("数据库验证成功！");
						//3.创建PreparedStatement
						String sql="select * from userinfo limit "+((pageNow-1)*pageSize)+","+pageSize;
						ps=ct.prepareStatement(sql);
						//4.执行操作
						rs=ps.executeQuery();
						//5.根据结果处理
						while(rs.next())
						{
							out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
						}
						//查询用户的数量
						ps=ct.prepareStatement("select count(*) from userinfo");
						rs=ps.executeQuery();
						rs.next();
						rowCount=rs.getInt(1);
						pageCount=(rowCount-1)/pageSize+1;
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					if(rs!=null)
					{
						try {
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						rs=null;
					}
					if(ps!=null)
					{
						try {
							ps.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ps=null;
					}
					if(ct!=null)
					{
						try {
							ct.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ct=null;
					}
				}
				
				**/
		
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
