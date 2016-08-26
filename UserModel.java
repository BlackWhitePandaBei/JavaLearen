/**
 * ��������û�������ģ�ͣ����ģ�;���Ϊ��һ�Ż��߼������ϵ��û���
 */
package com.model;
import java.sql.*;

import com.database.*;
public class UserModel {
	
	private ResultSet ResultSet;

	/**
	 * 
	 * @param uid ���������û�ID��Ҳ����Ա����
	 * @param pwd ���������û�����
	 * @return ��������������User��ְλ������û������ڣ�Ĭ�Ϸ����ַ���"�û���������"
	 */
	public String checkUser(String uid,String pwd){
		String zhiwei="�û���������";
		SqlHelper sh=null;
		try {
			//��֯sql���Ͳ����б�
			/*
			 * 1.?��Ҫ��ѯ���ֶ�
			 * 2.?��¼���������û�ID
			 * 3.?��¼���������û�����
			 */
			String sql="select renyuan.JOB from renyuan,denglu where denglu.ID=renyuan.ID and denglu.ID=? and denglu.PWD=?";
			String []shuzu={uid,pwd};
			//�������ݿ���������
			sh=new SqlHelper();
			//����һ������������ܽ��
			ResultSet rs=sh.chaxun(sql,shuzu);
			if(rs.next()){
				//ȡ��ְλ
				zhiwei=rs.getString(1);//��һ���ֶ�
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sh.close();//����sqlHelper�Ĺر���Դ����
		}
		
		return zhiwei;
	}
}
