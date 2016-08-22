/**
 * 这是人事的数据模型类，完成对认识表的crud
 */
package com.model;
import javax.swing.table.*;

import com.model.*;
import com.database.*;

import java.util.*;
import java.sql.*;
public class EmpModel extends AbstractTableModel{
	
	Vector<String> colums;//定不定义成泛型无所谓
	Vector<Vector> rows;
	
	
	
	//写一个方法，用于显示出查询语句执行完的表格信息
	//查询的方法比较墨迹，因为牵扯到字段显示等一些列，所以要多些步骤,这个方法的最终执行结果是一张查询完成的表
	public void chaxun(String sql,String shuzu[]){
		//初始化列,也就是创建出表上面的各个字段名
		colums=new Vector<String>();
		//初始化行
		rows=new Vector<Vector>();
		
		//创建一个连数据库的对象
		SqlHelper sh=new SqlHelper();
		ResultSet rs=sh.chaxun(sql, shuzu);
		
		
		try {
			//从rs结果集中可以拿到ResultSetMetaData
			ResultSetMetaData rsm=rs.getMetaData();
			//由此，我们可以知道结果集中一共有多少列，和每一列的名字
			//下面这个for循环是用来处理表格上的字段名的
			for(int i=0;i<rsm.getColumnCount();i++){
				this.colums.add(rsm.getColumnName(i+1));//循环加入每一列的字段名称
				
			}
					
			//循环把rs的结果放入的到行中，这里利用了向量temp，因为rows没法直接add
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
	
	//一个方法，用于处理sql语句，这个方法可以用来增删改，处理成功向页面的类返回true，有任何异常返回false
	public boolean chuli(String sql,String shuzu[]){
		boolean b=true;
		//new 一个数据库帮助类
		SqlHelper sh=new SqlHelper();
		//使用帮助类中的处理方法处理sql语句
		b=sh.chuli(sql, shuzu);
		return b;
	}
	
	public int getRowCount() {
		// 方法需要重写
		return this.rows.size();
	}

	@Override
	public int getColumnCount() {
		// 方法需要重写
		return this.colums.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// 方法需要重写
		return ((Vector)this.rows.get(rowIndex)).get(columnIndex);
	}
	@Override
	/**
	 * 这个方法不要忘记覆盖，要不然最上面的字段名称显示不出来
	 */
	public String getColumnName(int column) {
		// 重写得到列名的方法
		return this.colums.get(column).toString();
	}
	
}
