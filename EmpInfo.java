/***
 * 这是用于显示人事管理的一个界面，界面的内容就是数据库中的员工表
 */
package com.jiemian;
import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import com.model.EmpModel;

import tools.*;
public class EmpInfo extends JPanel implements ActionListener{
	//定义需要的组件
	JPanel p1,p2,p3,p4,p5;
	JLabel p1_lab1,p3_lab1;
	JTextField p1_jtf1;
	JButton p1_jb1,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	JTable jtable;//这个是用于显示人员表的table
	JScrollPane jsp=null;//表格必须放到这里面,不放的话，最上面的列名无法显示
	String chushi="ID,NAME,SEX,JOB,CARDID";//用来制定初始化界面查询的字符段
	EmpModel em;
	
	//构造函数
	public EmpInfo(){
		//创建需要的组件
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_lab1=new JLabel("请输入姓名（员工号或职位）");
		p1_lab1.setFont(myTools.f2);
		p1_jtf1=new JTextField(20);
		p1_jb1=new JButton("查询");
		p1_jb1.addActionListener(this);
		p1_jb1.setFont(myTools.f2);
		//把p1上需要的组件加入到p1
		p1.add(p1_lab1);
		p1.add(p1_jtf1);
		p1.add(p1_jb1);
		
		//处理中间
		EmpModel em=new EmpModel();
		String shuzu[]={"1"};
		em.chaxun("select "+chushi+" from renyuan where 1=?", shuzu);
		jtable=new JTable(em);
		//创建p2
		p2=new JPanel(new BorderLayout());
		jsp=new JScrollPane(jtable);//加入滚动条是必须的
		p2.add(jsp);
		
		//处理下面的Panel
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3_lab1=new JLabel("共有？条记录");
		p3.add(p3_lab1);
		
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1=new JButton("详细信息");
		p4_jb2=new JButton("添加");
		p4_jb2.addActionListener(this);
		p4_jb3=new JButton("修改");
		p4_jb3.addActionListener(this);
		p4_jb4=new JButton("删除");
		p4_jb4.addActionListener(this);
		//加入p4的组件
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		//p5包括了p3和p4
		p5=new JPanel(new BorderLayout());
		p5.add(p3,"West");
		p5.add(p4,"East");
		
		
		
		//设置窗口的参数
		this.setLayout(new BorderLayout());
		this.add(p1,"North");
		this.add(p2,"Center");
		this.add(p5,"South");
		this.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		//判断用户点击的按钮
		if(e.getSource()==p1_jb1){
			//点击查询
			String yonghu=this.p1_jtf1.getText().trim();
			//写出查询的sql语句
			String sql="select "+chushi+" from renyuan where renyuan.JOB=?";
			String shuzu[]={yonghu};
			//创建一个员工表模型
			em=new EmpModel(); 
			//调用em中的显示查询的方法
			em.chaxun(sql, shuzu);
			//更新JTable
			jtable.setModel(em);
		}else if(e.getSource()==p4_jb2){
			//点击添加
		}else if(e.getSource()==p4_jb3){
			//点击修改
		}else if(e.getSource()==p4_jb4){
			//点击删除
			em=new EmpModel();
			
			//1.得到员工的ID
			//JTable的getSelectedRow函数会返回用户点中的行
			//如果用户没有选中任何一行，就返回-1
			int rn=this.jtable.getSelectedRow();
			if(rn==-1){
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			//得到编号
			//em是表的模型，getValueAt函数得到的是rn行的第一个字段，0表示第一个字段
			String ID=(String)em.getValueAt(rn, 0);
			System.out.println("ID:"+ID);
			//传出去sql语句和数组，执行删除
			//写出删除的sql语句
			String sql="delete from renyuan where ID=?";
			//设置数组
			String shuzu[]={ID};
			//创建EmpModel的对象temp
			EmpModel temp=new EmpModel();
			temp.chuli(sql, shuzu);

			
			//更新JTable
			
			jtable.setModel(em);
			
		}
		
	}
}
