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
	
	//构造函数
	public CardBuju(){
		jb1=new JButton("人事管理");
		jb1.addActionListener(this);
		jb2=new JButton("登录管理");
		jb2.addActionListener(this);
		jb3=new JButton("菜谱价格");
		jb3.addActionListener(this);
		
		cl=new CardLayout();
		jp1=new JPanel(cl);//把jp1设置为卡片布局，这个只不过把卡片布局弄到外面定义之后再加进来，和原先new在里面的一样
		
		//创建3个Panel，每个Panel上面都放上一个JLanel
		jp3=new JPanel();
		jp3.add(new JLabel("wahaha"));//这个Label显示1
		jp4=new JPanel();
		jp4.add(new JLabel("ziehaha"));
		jp5=new JPanel();
		jp5.add(new JLabel("bahaha"));
		//把3个Panel放入jp1中，jp1是卡片布局
		//jp1中有3个卡片，分别是jp3，jp4，jp5
		//在默认情况下显示第一个加入的卡片
		jp1.add(jp3,"nihao");//给jp3取名字为"nihao"
		jp1.add(jp4,"wohao");//给jp4取名字为"wohao"
		jp1.add(jp5,"dajiahao");//给jp5取名字为"dajiahao",一般情况下用0或者1等很小的数字命名
		
		jp2=new JPanel();
		//把3个按钮加入到jp2
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
		//对3个按钮进行的监听
		if(e.getSource()==jb1){
			//注意，cl就是卡片布局cl=new CardLayout();
			cl.show(jp1, "nihao");//展示jp1面板中名字为"nihao"的卡片
		}else if(e.getSource()==jb2){
			cl.show(jp1,"wohao");//展示jp1面板中名字为"wohao"的卡片
		}else if(e.getSource()==jb3){
			cl.show(jp1, "dajiahao");//展示jp1面板中名字为"dajiahao"的卡片
		}
	}

}
