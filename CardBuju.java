package com.jiemian;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class CardBuju extends JFrame implements ActionListener{
	
	JButton jb1,jb2,jb3,jb4;
	JPanel jp1,jp2,jp3,jp4,jp5;
	
	CardLayout cl;
	public static void main(String[] args) {
		CardBuju cb=new CardBuju();

	}
	
	//���캯��
	public CardBuju(){
		jb1=new JButton("���¹���");
		jb1.addActionListener(this);
		jb2=new JButton("��¼����");
		jb2.addActionListener(this);
		jb3=new JButton("���׼۸�");
		jb3.addActionListener(this);
		
		cl=new CardLayout();
		jp1=new JPanel(cl);//��jp1����Ϊ��Ƭ���֣����ֻ�����ѿ�Ƭ����Ū�����涨��֮���ټӽ�������ԭ��new�������һ��
		
		//����3��Panel��ÿ��Panel���涼����һ��JLanel
		jp3=new JPanel();
		jp3.add(new JLabel("wahaha"));//���Label��ʾ1
		jp4=new JPanel();
		jp4.add(new JLabel("ziehaha"));
		jp5=new JPanel();
		jp5.add(new JLabel("bahaha"));
		//��3��Panel����jp1�У�jp1�ǿ�Ƭ����
		//jp1����3����Ƭ���ֱ���jp3��jp4��jp5
		//��Ĭ���������ʾ��һ������Ŀ�Ƭ
		jp1.add(jp3,"nihao");//��jp3ȡ����Ϊ"nihao"
		jp1.add(jp4,"wohao");//��jp4ȡ����Ϊ"wohao"
		jp1.add(jp5,"dajiahao");//��jp5ȡ����Ϊ"dajiahao",һ���������0����1�Ⱥ�С����������
		
		jp2=new JPanel();
		//��3����ť���뵽jp2
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		
		this.add(jp1,"Center");
		this.add(jp2,"South");
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//��3����ť���еļ���
		if(e.getSource()==jb1){
			//ע�⣬cl���ǿ�Ƭ����cl=new CardLayout();
			cl.show(jp1, "nihao");//չʾjp1���������Ϊ"nihao"�Ŀ�Ƭ
		}else if(e.getSource()==jb2){
			cl.show(jp1,"wohao");//չʾjp1���������Ϊ"wohao"�Ŀ�Ƭ
		}else if(e.getSource()==jb3){
			cl.show(jp1, "dajiahao");//չʾjp1���������Ϊ"dajiahao"�Ŀ�Ƭ
		}
	}

}
