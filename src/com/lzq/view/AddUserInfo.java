package com.lzq.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		out.println("<center><h1>添加用户</h1></center>");
		out.println("<center><table border='1' cellspacing='0'></center>");
		out.println("<form action='/UserManager/UserInfoCUD?type=addUser' method='post'>");
		out.println("<tr><th>ID&nbsp&nbsp&nbsp</th><th><input type='text' name='id' /></th></tr></br>");
		out.println("<tr><th>姓名</th><th><input type='text' name='name' /></th></tr></br>");
		out.println("<tr><th>性别</th><th><input type='text' name='sex' /></th></tr></br>");
		out.println("<tr><th>年龄</th><th><input type='text' name='age' /></th></tr></br>");
		out.println("<tr><th>邮箱</th><th><input type='text' name='email' /></th></tr></br>");
		out.println("<tr><th></th><th><input type='submit' value='确认添加' />&nbsp&nbsp&nbsp&nbsp<input type='reset' value='重置' /></th></tr></br>");
		out.println("</form>");
		out.println("</table>");
		
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
