package com.lzq.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

	//提取用户信息
	
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
