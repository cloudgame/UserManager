package com.lzq.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

	//�û���Ϣ��ҳ��ʾ
	public ArrayList<UserInfo> UserInfoSplitPage(int pageNow,int pageSize)
	{
		SqlHelper sh=new SqlHelper();
		ArrayList<UserInfo> al=new ArrayList<UserInfo>();
		//�������ݿ����
		ResultSet rs=null;
		String sql="select * from userinfo limit "+((pageNow-1)*pageSize)+","+pageSize;
		//�������ݿ�
		sh.connection();
		rs = sh.query(sql, null);
		try {
			while(rs.next())
			{
				UserInfo ui=new UserInfo();
				ui.setId(rs.getInt(1));
				ui.setName(rs.getString(2));
				ui.setSex(rs.getString(3));
				ui.setAge(rs.getInt(4));
				ui.setEmail(rs.getString(5));
				al.add(ui);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al ;
	}
	
	//��֤�û��Ƿ���ȷ
	public boolean checkUser(UserLoginInfo ul)
	{
		boolean b=false;
		SqlHelper sh=new SqlHelper(); 
		//�������ݿ����
		ResultSet rs=null;
		String sql="select * from userlogin where id=? and pwd=?";
		String para[]={ul.id+"",ul.psw};
		//�������ݿ�
		sh.connection();
		//��ѯ���ݿ�
		rs=sh.query(sql, para);
		try {
			if(rs.next())
			{
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			sh.close(rs, sh.ps, sh.ct);
		}
		System.out.println("b="+b);
		return b;
	}
}
