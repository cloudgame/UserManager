package com.lzq.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

	//����SqlHelper
	SqlHelper sh=new SqlHelper(); 
	
	//��ѯ�û�������
	public int queryRowCount()
	{
		int rowCount=0;
		//�������ݿ����
		ResultSet rs=null;
		sh.connection();
		String sql="select count(*) from userinfo";
		rs=sh.query(sql, null);
		try {
			rs.next();
			rowCount=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			sh.close(rs, sh.ps, sh.ct);
		}
		return rowCount;
	}
	
	//�û���Ϣ��ҳ��ʾ
	public ArrayList<UserInfo> UserInfoSplitPage(int pageNow,int pageSize)
	{
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
		finally
		{
			sh.close(rs, sh.ps, sh.ct);
		}
		return al ;
	}
	
	//��֤�û��Ƿ���ȷ
	public boolean checkUser(UserLoginInfo ul)
	{
		boolean b=false;
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
	//ɾ���û���Ϣ
	public boolean delUserInfo(String id)
	{
		boolean b=false;
		//ɾ�����
		String sql="delete from userinfo where id=?";
		String para[]={id};
		if(sh.updateSql(sql, para))
		{
			b=true;
			System.out.println("UserServiceɾ���û��ɹ�");
		}
		sh.close(sh.rs, sh.ps, sh.ct);
		return b;
	}
	//��ѯ�û�����Ϣ
	public ResultSet queryUserInfo(String id)
	{
		String sql="select * from userinfo where id=?";
		String para[]={id};
		ResultSet rs=sh.query(sql, para);
		return rs;
	}
	//���û���Ϣ�����޸�
	public boolean updateUserInfo(UserInfo ui)
	{
		boolean b=true;
		String sql="update userinfo set name=?, sex=?, age=?, email=? where id=?";
		String para[]={ui.name,ui.sex,ui.age+"",ui.email,ui.id+""};
		try {
			sh.updateSql(sql, para);
		} catch (Exception e) {
			b=false;
		}
		sh.close(sh.rs, sh.ps, sh.ct);
		return b;
	}
	//����û���Ϣ
	public boolean addUserInfo(UserInfo ui)
	{
		boolean b=true;
		String sql="insert into userinfo values(?, ?, ?, ? ,?) ";
		String para[]={ui.id+"",ui.name,ui.sex,ui.age+"",ui.email};
		try {
			sh.updateSql(sql, para);
		} catch (Exception e) {
			b=false;
		}
		sh.close(sh.rs, sh.ps, sh.ct);
		return b;
	}
}
