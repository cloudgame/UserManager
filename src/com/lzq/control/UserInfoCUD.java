package com.lzq.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzq.model.SqlHelper;
import com.lzq.model.UserInfo;
import com.lzq.model.UserService;

//对用户信息进行增加、修改、删除操作
public class UserInfoCUD extends HttpServlet {
	//定义UserService
	UserService us=new UserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;paraset=utf-8");
//		PrintWriter out = response.getWriter();
		if("del".equals(request.getParameter("type")))
		{
			//获取要删除的用户ID
			String id=request.getParameter("id");
			if(us.delUserInfo(id))
			{
				request.getRequestDispatcher("/UserManager").forward(request, response);
				System.out.println("删除用户成功！！！");
			}
			else
			{
				System.out.println("删除用户失败！！！");
			}
		}else if("gotoUpd".equals(request.getParameter("type")))
		{
			UserInfo ui=new UserInfo();
			//获取要修改的用户ID
			String id=request.getParameter("id");
			ResultSet rs=us.queryUserInfo(id);
			try {
				while(rs.next())
				{
					ui.setId(rs.getInt(1));
					ui.setName(rs.getString(2));
					ui.setSex(rs.getString(3));
					ui.setAge(rs.getInt(4));
					ui.setEmail(rs.getString(5));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//将userInfo信息放入到request属性
			request.setAttribute("userInfo", ui);
			//消息转向
			request.getRequestDispatcher("UpdateUserInfo").forward(request, response);
		}else if("update".equals(request.getParameter("type")))
		{
//			System.out.println("进入修改模式");
			UserInfo ui=new UserInfo();
			ui.setId(Integer.parseInt(request.getParameter("id")));
			ui.setName(request.getParameter("name"));
			ui.setSex(request.getParameter("sex"));
			ui.setAge(Integer.parseInt(request.getParameter("age")));
			ui.setEmail(request.getParameter("email"));
			if(us.updateUserInfo(ui))
			{
				System.out.println("修改用户信息成功！！！");
				response.sendRedirect("/UserManager/UserManager");
			}
			else
			{
				System.out.println("修改用户信息失败！！！");
			}
		}else if("addUser".equals(request.getParameter("type")))
		{
			System.out.println("进入添加模式");
			UserInfo ui=new UserInfo();
			ui.setId(Integer.parseInt(request.getParameter("id")));
			ui.setName(request.getParameter("name"));
			ui.setSex(request.getParameter("sex"));
			ui.setAge(Integer.parseInt(request.getParameter("age")));
			ui.setEmail(request.getParameter("email"));
			if(us.addUserInfo(ui))
			{
				System.out.println("添加用户信息成功！！！");
				response.sendRedirect("/UserManager/UserManager");
			}
			else
			{
				System.out.println("添加用户信息失败！！！");
			}
		}else if("gotoAddUser".equals(request.getParameter("type")))
		{
			request.getRequestDispatcher("/AddUserInfo").forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
