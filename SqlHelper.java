/**
 * �����ݿ���в�������,����CRUD
 * ������д��Ŀ��Ҫ��ҵ�����
 */
package com.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;
import java.sql.*;

public class SqlHelper {
	//�������ݿ���Ҫ�����
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String dirverName="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url="jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=guanlixitong";
	String user="sa";
	String pwd="xiongmao";
	//���캯������ʼ��ct
	public SqlHelper(){
		try {
			//��������
			Class.forName(dirverName);
			ct=DriverManager.getConnection(url,user,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	
	//��ѯ�ķ������������������飬���ز�ѯ��Ľ����
	public ResultSet chaxun(String sql,String[] shuzu){
		try {
			ps=ct.prepareStatement(sql);
			//��sql�Ĳ�����ֵ
			for(int i=0;i<shuzu.length;i++){
				ps.setString(i+1, shuzu[i]);
			}
			//rs�ǽ���������������ѯ���ִ����ɺ�Ľ��
			rs=ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//����sql���ݵķ�������������ɾ�ģ�������sql��������ʹ���ĺڿƼ�����
	//�������������ɣ�����true��һ�������쳣����false
	public boolean chuli(String sql,String shuzu[]){
		boolean b=true;
		try {

			//�õ�����
			ct=DriverManager.getConnection(url,user,pwd);
			//����ps
			ps=ct.prepareStatement(sql);
			//����ps,ѭ����ps�е�?��ֵ
			for(int i=0;i<shuzu.length;i++){
				ps.setString(i+1, shuzu[i]);
			}
			//5.ִ��
			if(ps.executeUpdate()!=1){
				b=false;
			}
			
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
		}finally{
			//�ر���Դ
			this.close();
			
		}	
		return b;
	}
	
	//�ر���Դ�ķ���
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
