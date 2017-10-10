package com.lzq.control;

import com.lzq.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginYZ extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		//获取到用户名和密码
		int userId=Integer.parseInt(request.getParameter("username"));
		String passwd=request.getParameter("password");
		//将用户名密码定义到UserLogin中
		UserLoginInfo ul=new UserLoginInfo();
		ul.setId(userId);
		ul.setPsw(passwd);
		//验证用户名，密码是否正确
		UserService uv=new UserService();
		boolean b=uv.checkUser(ul);
		if(b)
		{
			//如果验证通过
			request.getRequestDispatcher("/MainFrame").forward(request, response);
		}
		else
		{
			//如果验证失败
			request.setAttribute("err","用户名或密码有误！");
			request.getRequestDispatcher("/UserLogin").forward(request, response);
		}
		
		
		/*
		 * 
		 * 
		//数据库驱动名
		String deriver="com.mysql.jdbc.Driver";
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
				ps=ct.prepareStatement("select * from userlogin where id=? and pwd=?");
				//给？赋值
				ps.setObject(1, userId);
				ps.setObject(2, passwd);
				//4.执行操作
				rs=ps.executeQuery();
				//5.根据结果处理
				if(rs.next())
				{
					request.getRequestDispatcher("/MainFrame").forward(request, response);
				}
				else
				{
					request.setAttribute("err","用户名或密码有误！");
					request.getRequestDispatcher("/UserLogin").forward(request, response);
				}
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
		
		*/
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
