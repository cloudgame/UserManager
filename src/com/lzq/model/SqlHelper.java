package com.lzq.model;
import java.sql.*;

public class SqlHelper {
	
	//数据库驱动名
	static String deriver="com.mysql.jdbc.Driver";
	//URL指向的要访问的数据库名userinfo
	static String url="jdbc:mysql://127.0.0.1:3306/userinfo";
	//mysql配置时的用户名
	static String username="root";
	//mysql配置时的密码
	static String password="123456";
	static Connection ct=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	static
	{
		try {
			Class.forName(deriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//建立连接
	public void connection()
	{
		//加载驱动
		try {
			ct=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接数据库成功！");
	}
	
	//对数据库执行“增”“删”“改”
		public boolean updateSql(String sql,String[] para)
		{
			boolean b=false;
			try {
				connection();
				//创建ps,预编译后再传送到数据库中
				ps=ct.prepareStatement(sql);
				//查看参数
				for(int j=0;j<para.length;j++)
				{
					System.out.println("para["+j+"]="+para[j]);
				}
				//对ps语句问号进行赋值
				for(int i = 0;i<para.length;i++)
				{
					ps.setString(i+1, para[i]);
				}
				//执行“增”“删”“改”操作
				ps.executeUpdate();
				b=true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return b;
		}
	
	//对数据库执行"查找"
	public ResultSet query(String sql,String[] para)
	{
		try {
			connection();
			ps=ct.prepareStatement(sql);
			if(para!=null)
			{
				for(int i = 0;i<para.length;i++)
				{
					ps.setString(i+1, para[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return rs;
		}
	}
	
	//关闭资源
	public void close(ResultSet rs,PreparedStatement ps,Connection ct)
	{
		try {
			if(rs!=null)
			{
				rs.close();
			}
			rs=null;
			if(ps!=null)
			{
				ps.close();
			}
			ps=null;
			if(ct!=null)
			{
				ct.close();
			}
			ct=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
