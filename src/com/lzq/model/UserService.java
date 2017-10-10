package com.lzq.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

	//用户信息分页显示
	public ArrayList<UserInfo> UserInfoSplitPage(int pageNow,int pageSize)
	{
		SqlHelper sh=new SqlHelper();
		ArrayList<UserInfo> al=new ArrayList<UserInfo>();
		//定义数据库参数
		ResultSet rs=null;
		String sql="select * from userinfo limit "+((pageNow-1)*pageSize)+","+pageSize;
		//连接数据库
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
	
	//验证用户是否正确
	public boolean checkUser(UserLoginInfo ul)
	{
		boolean b=false;
		SqlHelper sh=new SqlHelper(); 
		//定义数据库参数
		ResultSet rs=null;
		String sql="select * from userlogin where id=? and pwd=?";
		String para[]={ul.id+"",ul.psw};
		//连接数据库
		sh.connection();
		//查询数据库
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
