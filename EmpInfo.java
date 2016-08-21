/***
 * ����������ʾ���¹����һ�����棬��������ݾ������ݿ��е�Ա����
 */
package com.jiemian;
import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import com.model.EmpModel;

import tools.*;
public class EmpInfo extends JPanel implements ActionListener{
	//������Ҫ�����
	JPanel p1,p2,p3,p4,p5;
	JLabel p1_lab1,p3_lab1;
	JTextField p1_jtf1;
	JButton p1_jb1,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	JTable jtable;//�����������ʾ��Ա���table
	JScrollPane jsp=null;//������ŵ�������,���ŵĻ���������������޷���ʾ
	String chushi="ID,NAME,SEX,JOB,CARDID";//�����ƶ���ʼ�������ѯ���ַ���
	EmpModel em;
	
	//���캯��
	public EmpInfo(){
		//������Ҫ�����
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_lab1=new JLabel("������������Ա���Ż�ְλ��");
		p1_lab1.setFont(myTools.f2);
		p1_jtf1=new JTextField(20);
		p1_jb1=new JButton("��ѯ");
		p1_jb1.addActionListener(this);
		p1_jb1.setFont(myTools.f2);
		//��p1����Ҫ��������뵽p1
		p1.add(p1_lab1);
		p1.add(p1_jtf1);
		p1.add(p1_jb1);
		
		//�����м�
		EmpModel em=new EmpModel();
		String shuzu[]={"1"};
		em.chaxun("select "+chushi+" from renyuan where 1=?", shuzu);
		jtable=new JTable(em);
		//����p2
		p2=new JPanel(new BorderLayout());
		jsp=new JScrollPane(jtable);//����������Ǳ����
		p2.add(jsp);
		
		//���������Panel
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3_lab1=new JLabel("���У�����¼");
		p3.add(p3_lab1);
		
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1=new JButton("��ϸ��Ϣ");
		p4_jb2=new JButton("���");
		p4_jb2.addActionListener(this);
		p4_jb3=new JButton("�޸�");
		p4_jb3.addActionListener(this);
		p4_jb4=new JButton("ɾ��");
		p4_jb4.addActionListener(this);
		//����p4�����
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		//p5������p3��p4
		p5=new JPanel(new BorderLayout());
		p5.add(p3,"West");
		p5.add(p4,"East");
		
		
		
		//���ô��ڵĲ���
		this.setLayout(new BorderLayout());
		this.add(p1,"North");
		this.add(p2,"Center");
		this.add(p5,"South");
		this.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		//�ж��û�����İ�ť
		if(e.getSource()==p1_jb1){
			//�����ѯ
			String yonghu=this.p1_jtf1.getText().trim();
			//д����ѯ��sql���
			String sql="select "+chushi+" from renyuan where renyuan.JOB=?";
			String shuzu[]={yonghu};
			//����һ��Ա����ģ��
			em=new EmpModel(); 
			//����em�е���ʾ��ѯ�ķ���
			em.chaxun(sql, shuzu);
			//����JTable
			jtable.setModel(em);
		}else if(e.getSource()==p4_jb2){
			//������
		}else if(e.getSource()==p4_jb3){
			//����޸�
		}else if(e.getSource()==p4_jb4){
			//���ɾ��
			em=new EmpModel();
			
			//1.�õ�Ա����ID
			//JTable��getSelectedRow�����᷵���û����е���
			//����û�û��ѡ���κ�һ�У��ͷ���-1
			int rn=this.jtable.getSelectedRow();
			if(rn==-1){
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			//�õ����
			//em�Ǳ��ģ�ͣ�getValueAt�����õ�����rn�еĵ�һ���ֶΣ�0��ʾ��һ���ֶ�
			String ID=(String)em.getValueAt(rn, 0);
			System.out.println("ID:"+ID);
			//����ȥsql�������飬ִ��ɾ��
			//д��ɾ����sql���
			String sql="delete from renyuan where ID=?";
			//��������
			String shuzu[]={ID};
			//����EmpModel�Ķ���temp
			EmpModel temp=new EmpModel();
			temp.chuli(sql, shuzu);

			
			//����JTable
			
			jtable.setModel(em);
			
		}
		
	}
}
