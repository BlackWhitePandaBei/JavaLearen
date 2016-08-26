/**
 * 这个类是用户的数据模型，这个模型就认为是一张或者几张联合的用户表
 */
package com.model;
import java.sql.*;

import com.database.*;
public class UserModel {
	
	private ResultSet ResultSet;

	/**
	 * 
	 * @param uid 传进来的用户ID，也就是员工号
	 * @param pwd 传进来的用户密码
	 * @return 这个方法用来检查User的职位，如果用户不存在，默认返回字符串"用户名不存在"
	 */
	public String checkUser(String uid,String pwd){
		String zhiwei="用户名不存在";
		SqlHelper sh=null;
		try {
			//组织sql语句和参数列表
			/*
			 * 1.?需要查询的字段
			 * 2.?登录传进来的用户ID
			 * 3.?登录传进来的用户密码
			 */
			String sql="select renyuan.JOB from renyuan,denglu where denglu.ID=renyuan.ID and denglu.ID=? and denglu.PWD=?";
			String []shuzu={uid,pwd};
			//创建数据库操作类对象
			sh=new SqlHelper();
			//定义一个结果集，接受结果
			ResultSet rs=sh.chaxun(sql,shuzu);
			if(rs.next()){
				//取出职位
				zhiwei=rs.getString(1);//第一个字段
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sh.close();//调用sqlHelper的关闭资源方法
		}
		
		return zhiwei;
	}
}
