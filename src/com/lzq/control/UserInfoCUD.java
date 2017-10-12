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

//���û���Ϣ�������ӡ��޸ġ�ɾ������
public class UserInfoCUD extends HttpServlet {
	//����UserService
	UserService us=new UserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;paraset=utf-8");
//		PrintWriter out = response.getWriter();
		if("del".equals(request.getParameter("type")))
		{
			//��ȡҪɾ�����û�ID
			String id=request.getParameter("id");
			if(us.delUserInfo(id))
			{
				request.getRequestDispatcher("/UserManager").forward(request, response);
				System.out.println("ɾ���û��ɹ�������");
			}
			else
			{
				System.out.println("ɾ���û�ʧ�ܣ�����");
			}
		}else if("gotoUpd".equals(request.getParameter("type")))
		{
			UserInfo ui=new UserInfo();
			//��ȡҪ�޸ĵ��û�ID
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
			//��userInfo��Ϣ���뵽request����
			request.setAttribute("userInfo", ui);
			//��Ϣת��
			request.getRequestDispatcher("UpdateUserInfo").forward(request, response);
		}else if("update".equals(request.getParameter("type")))
		{
//			System.out.println("�����޸�ģʽ");
			UserInfo ui=new UserInfo();
			ui.setId(Integer.parseInt(request.getParameter("id")));
			ui.setName(request.getParameter("name"));
			ui.setSex(request.getParameter("sex"));
			ui.setAge(Integer.parseInt(request.getParameter("age")));
			ui.setEmail(request.getParameter("email"));
			if(us.updateUserInfo(ui))
			{
				System.out.println("�޸��û���Ϣ�ɹ�������");
				response.sendRedirect("/UserManager/UserManager");
			}
			else
			{
				System.out.println("�޸��û���Ϣʧ�ܣ�����");
			}
		}else if("addUser".equals(request.getParameter("type")))
		{
			System.out.println("�������ģʽ");
			UserInfo ui=new UserInfo();
			ui.setId(Integer.parseInt(request.getParameter("id")));
			ui.setName(request.getParameter("name"));
			ui.setSex(request.getParameter("sex"));
			ui.setAge(Integer.parseInt(request.getParameter("age")));
			ui.setEmail(request.getParameter("email"));
			if(us.addUserInfo(ui))
			{
				System.out.println("����û���Ϣ�ɹ�������");
				response.sendRedirect("/UserManager/UserManager");
			}
			else
			{
				System.out.println("����û���Ϣʧ�ܣ�����");
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
