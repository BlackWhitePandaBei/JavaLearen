/**
 * 对数据库进行操作的类,就是CRUD
 * 用来编写项目需要的业务操作
 */
package com.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;
import java.sql.*;

public class SqlHelper {
	//定义数据库需要的组件
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String dirverName="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url="jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=guanlixitong";
	String user="sa";
	String pwd="xiongmao";
	//构造函数，初始化ct
	public SqlHelper(){
		try {
			//加载驱动
			Class.forName(dirverName);
			ct=DriverManager.getConnection(url,user,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	
	//查询的方法，传过来语句和数组，返回查询后的结果集
	public ResultSet chaxun(String sql,String[] shuzu){
		try {
			ps=ct.prepareStatement(sql);
			//对sql的参数赋值
			for(int i=0;i<shuzu.length;i++){
				ps.setString(i+1, shuzu[i]);
			}
			//rs是结果集，就是这个查询语句执行完成后的结果
			rs=ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//处理sql数据的方法，功能是增删改，传过来sql语句和数组就处理的黑科技函数
	//如果处理正常完成，返回true，一旦出现异常返回false
	public boolean chuli(String sql,String shuzu[]){
		boolean b=true;
		try {

			//得到连接
			ct=DriverManager.getConnection(url,user,pwd);
			//创建ps
			ps=ct.prepareStatement(sql);
			//设置ps,循环给ps中的?赋值
			for(int i=0;i<shuzu.length;i++){
				ps.setString(i+1, shuzu[i]);
			}
			//5.执行
			if(ps.executeUpdate()!=1){
				b=false;
			}
			
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
		}finally{
			//关闭资源
			this.close();
			
		}	
		return b;
	}
	
	//关闭资源的方法
	public void close(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(ct!=null){
				ct.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
