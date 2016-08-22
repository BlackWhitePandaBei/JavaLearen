/**
 * �������µ�����ģ���࣬��ɶ���ʶ���crud
 */
package com.model;
import javax.swing.table.*;

import com.model.*;
import com.database.*;

import java.util.*;
import java.sql.*;
public class EmpModel extends AbstractTableModel{
	
	Vector<String> colums;//��������ɷ�������ν
	Vector<Vector> rows;
	
	
	
	//дһ��������������ʾ����ѯ���ִ����ı����Ϣ
	//��ѯ�ķ����Ƚ�ī������Ϊǣ�����ֶ���ʾ��һЩ�У�����Ҫ��Щ����,�������������ִ�н����һ�Ų�ѯ��ɵı�
	public void chaxun(String sql,String shuzu[]){
		//��ʼ����,Ҳ���Ǵ�����������ĸ����ֶ���
		colums=new Vector<String>();
		//��ʼ����
		rows=new Vector<Vector>();
		
		//����һ�������ݿ�Ķ���
		SqlHelper sh=new SqlHelper();
		ResultSet rs=sh.chaxun(sql, shuzu);
		
		
		try {
			//��rs������п����õ�ResultSetMetaData
			ResultSetMetaData rsm=rs.getMetaData();
			//�ɴˣ����ǿ���֪���������һ���ж����У���ÿһ�е�����
			//�������forѭ���������������ϵ��ֶ�����
			for(int i=0;i<rsm.getColumnCount();i++){
				this.colums.add(rsm.getColumnName(i+1));//ѭ������ÿһ�е��ֶ�����
				
			}
					
			//ѭ����rs�Ľ������ĵ����У���������������temp����Ϊrowsû��ֱ��add
			while(rs.next()){
				Vector<String> temp=new Vector<String>();
				for(int i=0;i<rsm.getColumnCount();i++){
					temp.add(rs.getString(i+1));
				}
				rows.add(temp);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sh.close();
		}
	}
	
	//һ�����������ڴ���sql��䣬�����������������ɾ�ģ�����ɹ���ҳ����෵��true�����κ��쳣����false
	public boolean chuli(String sql,String shuzu[]){
		boolean b=true;
		//new һ�����ݿ������
		SqlHelper sh=new SqlHelper();
		//ʹ�ð������еĴ���������sql���
		b=sh.chuli(sql, shuzu);
		return b;
	}
	
	public int getRowCount() {
		// ������Ҫ��д
		return this.rows.size();
	}

	@Override
	public int getColumnCount() {
		// ������Ҫ��д
		return this.colums.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// ������Ҫ��д
		return ((Vector)this.rows.get(rowIndex)).get(columnIndex);
	}
	@Override
	/**
	 * ���������Ҫ���Ǹ��ǣ�Ҫ��Ȼ��������ֶ�������ʾ������
	 */
	public String getColumnName(int column) {
		// ��д�õ������ķ���
		return this.colums.get(column).toString();
	}
	
}
