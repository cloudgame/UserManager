package com.lzq.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzq.model.UserInfo;

public class UpdateUserInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取用户信息
		UserInfo ui=(UserInfo) request.getAttribute("userInfo");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<center><h1>修改用户</h1></center>");
		out.println("<center><table border='1' cellspacing='0'></center>");
		out.println("<form action='/UserManager/UserInfoCUD?type=update' method='post'>");
		out.println("<tr><th>ID&nbsp&nbsp&nbsp</th><th><input type='text' name='id' readonly value='"+ui.getId()+"'/></th></tr></br>");
		out.println("<tr><th>姓名</th><th><input type='text' name='name' value='"+ui.getName()+"'/></th></tr></br>");
		out.println("<tr><th>性别</th><th><input type='text' name='sex' value='"+ui.getSex()+"'/></th></tr></br>");
		out.println("<tr><th>年龄</th><th><input type='text' name='age' value='"+ui.getAge()+"'/></th></tr></br>");
		out.println("<tr><th>邮箱</th><th><input type='text' name='email' value='"+ui.getEmail()+"'/></th></tr></br>");
		out.println("<tr><th></th><th><input type='submit' value='确认修改' />&nbsp&nbsp&nbsp&nbsp<input type='reset' value='重置' /></th></tr></br>");
		out.println("</form>");
		out.println("</table>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		this.doGet(request, response);
	}

}
