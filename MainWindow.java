/*
 * 一个比较复杂的主程序操作界面
 * 完成界面的顺序。从上到下，从左到右.
 */
package com.jiemian;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;

import tools.ImagePanel;
import tools.myTools;

import java.io.*;

public class MainWindow extends JFrame implements ActionListener,MouseListener{
	//定义需要的组件
	Image titelIcon,timeBG;//ImageIcon指图像图标,但是貌似左上角的不能Icon，所以直接读进去一个Image
	//菜单栏一类的组件
	JMenuBar jmb;//最上层的菜单栏
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;//菜单下的各个字段
	JMenuItem jmm1,jmm2,jmm3,jmm4,jmm5;//单个字段下的选项
	//图标
	ImageIcon jmm1_iocn,jmm2_iocn,jmm3_iocn,jmm4_iocn,jmm5_iocn;
	//菜单下面的工具栏
	JToolBar jtb;//工具栏的类型
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//需要的5个JPanel
	JPanel p1,p2,p3,p4,p5;
	//显示时间的JLabel
	JLabel showtime;
	//p1面板下的8个标签
	JLabel p1_lab1,p1_lab2,p1_lab3,p1_lab4,p1_lab5,p1_lab6,p1_lab7,p1_lab8;
	//p2面板下的标签
	JLabel p2_lab1,p2_lab2;
	//javax.swing包中的Timer类，可以定时间的触发Action事件，利用这个可以做一些事情
	javax.swing.Timer t;
	//p1面板下的背景图片
	ImagePanel p1_panel;
	Image p1_bg=null;
	//存放p1和p4的拆分窗口
	JSplitPane jsp1=null;
	CardLayout card2=null;
	CardLayout crad3=null;
	public static void main(String[] args) {
		
		MainWindow mw=new MainWindow();
	}
	
	/**
	 * 觉得构造函数中一堆界面的东西很烦，整个函数全弄了出来
	 */
	public void myJiemian(){
		//创建组件
				try {
					titelIcon=ImageIO.read(new File("image/tv_dark.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//创建图标
				jmm1_iocn=new ImageIcon("image/cabin.png");
				jmm2_iocn=new ImageIcon("image/sign_explain.png");
				jmm3_iocn=new ImageIcon("image/down.png");
				jmm4_iocn=new ImageIcon("image/sign_copyright.png");
				jmm5_iocn=new ImageIcon("image/spanner_blue.png");
				//创jm1建菜单字段
				jm1=new JMenu("系统管理");
				jm1.setFont(myTools.f1);
				//创建jm1的菜单选项
				jmm1=new JMenuItem("切换用户",jmm1_iocn);//直接就可以把图标放进选项中
				jmm1.setFont(myTools.f2);
				jmm2=new JMenuItem("切换到收款界面",jmm2_iocn);
				jmm2.setFont(myTools.f2);
				jmm3=new JMenuItem("登录管理",jmm3_iocn);
				jmm3.setFont(myTools.f2);
				jmm4=new JMenuItem("万年历",jmm4_iocn);
				jmm4.setFont(myTools.f2);
				jmm5=new JMenuItem("退出",jmm5_iocn);
				jmm5.setFont(myTools.f2);
				//加入到jm1
				jm1.add(jmm1);
				jm1.add(jmm2);
				jm1.add(jmm3);
				jm1.add(jmm4);
				jm1.add(jmm5);
				//创jm2建菜单字段
				jm2=new JMenu("人事管理");
				jm2.setFont(myTools.f1);
				//创jm3建菜单字段
				jm3=new JMenu("菜单服务");
				jm3.setFont(myTools.f1);
				//创jm4建菜单字段
				jm4=new JMenu("报表统计");
				jm4.setFont(myTools.f1);
				//创jm5建菜单字段
				jm5=new JMenu("成本及库房");
				jm5.setFont(myTools.f1);
				//创jm6建菜单字段
				jm6=new JMenu("帮助");
				jm6.setFont(myTools.f1);
				//创建最上级菜单
				jmb=new JMenuBar();
				//把字段菜单加入到最上级菜单
				jmb.add(jm1);
				jmb.add(jm2);
				jmb.add(jm3);
				jmb.add(jm4);
				jmb.add(jm5);
				jmb.add(jm6);
				//JFrame中加入最上级菜单
				this.setJMenuBar(jmb);//菜单的加入语句，加入菜单语句不要搞错
				//处理工具栏的组件
				jtb=new JToolBar();
				//设置工具栏不可以移动，官方说法是不允许浮动
				jtb.setFloatable(false);
				jb1=new JButton(new ImageIcon("image/jb1.gif"));//让JButton显示一个图片
				jb2=new JButton(new ImageIcon("image/jb2.gif"));
				jb3=new JButton(new ImageIcon("image/jb3.gif"));
				jb4=new JButton(new ImageIcon("image/jb4.gif"));
				jb5=new JButton(new ImageIcon("image/jb5.gif"));
				jb6=new JButton(new ImageIcon("image/jb6.gif"));
				jb7=new JButton(new ImageIcon("image/jb7.gif"));
				jb8=new JButton(new ImageIcon("image/jb8.gif"));
				jb9=new JButton(new ImageIcon("image/jb9.gif"));
				jb10=new JButton(new ImageIcon("image/jb10.gif"));
				//把按钮加入到工具栏jtb
				jtb.add(jb1);
				jtb.add(jb2);
				jtb.add(jb3);
				jtb.add(jb4);
				jtb.add(jb5);
				jtb.add(jb6);
				jtb.add(jb7);
				jtb.add(jb8);
				jtb.add(jb9);
				jtb.add(jb10);
				
				
				//处理p1面板
				p1=new JPanel(new BorderLayout());
				try {
					p1_bg=ImageIO.read(new File("image/p1_bg.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p1_panel=new ImagePanel(p1_bg);
				p1_panel.setLayout(new GridLayout(8,1));//设置为8行1列的网格布局
				//初始化8个Label
				p1_lab1=new JLabel(new ImageIcon("image/dongtai.gif"));//加入图标
				p1_lab2=new JLabel("人 事 管 理",new ImageIcon("image/xinxin.png"),0);//标签中加入文字和图片，0横向还是纵向排列
				p1_lab2.setFont(myTools.f3);
				p1_lab2.setEnabled(false);
				//标签注册监听
				p1_lab2.addMouseListener(this);
				p1_lab2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab3=new JLabel("登 录 管 理",new ImageIcon("image/theboy.png"),0);
				p1_lab3.setFont(myTools.f3);
				p1_lab3.setEnabled(false);
				p1_lab3.addMouseListener(this);
				p1_lab3.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab4=new JLabel("菜 谱 价 格",new ImageIcon("image/xoo.png"),0);
				p1_lab4.setFont(myTools.f3);
				p1_lab4.setEnabled(false);
				p1_lab4.addMouseListener(this);
				p1_lab4.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab5=new JLabel("报 表 统 计",new ImageIcon("image/wowo.png"),0);
				p1_lab5.setFont(myTools.f3);
				p1_lab5.setEnabled(false);
				p1_lab5.addMouseListener(this);
				p1_lab5.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab6=new JLabel("成本及库房",new ImageIcon("image/45678.png"),0);
				p1_lab6.setFont(myTools.f3);
				p1_lab6.setEnabled(false);
				p1_lab6.addMouseListener(this);
				p1_lab6.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab7=new JLabel("系 统 设 置",new ImageIcon("image/abc.png"),0);
				p1_lab7.setFont(myTools.f3);
				p1_lab7.setEnabled(false);
				p1_lab7.addMouseListener(this);
				p1_lab7.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab8=new JLabel("动 画 帮 助",new ImageIcon("image/ghj.png"),0);
				p1_lab8.setFont(myTools.f3);
				p1_lab8.setEnabled(false);
				p1_lab8.addMouseListener(this);
				p1_lab8.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_panel.add(p1_lab1);
				p1_panel.add(p1_lab2);
				p1_panel.add(p1_lab3);
				p1_panel.add(p1_lab4);
				p1_panel.add(p1_lab5);
				p1_panel.add(p1_lab6);
				p1_panel.add(p1_lab7);
				p1_panel.add(p1_lab8);
				
				p1.add(p1_panel);
				
				
				
				//处理p2,p3,p4三个面板,p2和p3是被p4包起来的
				card2=new CardLayout();
				crad3=new CardLayout();
				p4=new JPanel(new BorderLayout());
				p2=new JPanel(card2);
				p3=new JPanel(crad3);
				
				
				//p2里面的组件
				p2_lab1=new JLabel(new ImageIcon("image/migi.png"));
				p2_lab1.addMouseListener(this);
				p2_lab1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p2_lab2=new JLabel(new ImageIcon("image/hidari.png"));
				p2_lab2.addMouseListener(this);
				p2_lab2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//两个label加入到p2中
				p2.add(p2_lab1,"migi");
				p2.add(p2_lab2,"hidari");
				
				
				//处理p3
				//先给P3加入一个主界面的卡片
				Image zhu_image=null;
				Image renshi=null;
				Image denglu=null;
				try {
					zhu_image=ImageIO.read(new File("image/36.jpg"));
					
					denglu=ImageIO.read(new File("image/201.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ImagePanel ip=new ImagePanel(zhu_image);
				p3.add(ip,"zhu");
				EmpInfo ip2=new EmpInfo();
				p3.add(ip2,"renshi");
				ImagePanel ip3=new ImagePanel(denglu);
				p3.add(ip3,"denglu");
				
				
				//做一个拆分窗口，存放p1和p4
				jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,p1,p4);
				//HORIZONTAL_SPLIT代表横向分割
				//指定左边的面板占多大,下面写的是占据屏幕长度的1/4
				jsp1.setDividerLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/4);
				//把边界线设置为0，也就是不显示
				jsp1.setDividerSize(0);
				
				
				//把p2和p3加入到p4
				p4.add(p2,"West");//p2在左边
				p4.add(p3,"Center");
				//处理p5，底部的面板，就是状态栏，在状态栏中显示当前时间
				p5=new JPanel(new BorderLayout());
				t=new Timer(1000,this);//每隔1000毫秒，触发一次ActionEvent
				//启动定时器
				t.start();
				//显示当前时间放入showtime的标签中，这里要先引入个util包
				showtime=new JLabel(Calendar.getInstance().getTime().toLocaleString());
				showtime.setFont(myTools.f2);
				try {
					timeBG=ImageIO.read(new File("image/bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImagePanel ip1=new ImagePanel(timeBG);
				ip1.setLayout(new BorderLayout());//JPanel设置布局
				
				ip1.add(showtime,"East");
				p5.add(ip1);
	}
	//构造函数
	public MainWindow(){
		//调用界面函数
		this.myJiemian();
		
		//中间的是一堆的面板
		this.add(jsp1,"Center");
		//把工具栏加入到JFrame的北部
		this.add(jtb,"North");
		//把p5放到JFrame的南部
		this.add(p5,"South");
		//设置窗口上小图标的显示,左上角小图标
		this.setIconImage(titelIcon);
		//设置窗口显示
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h-30);
		this.setTitle("程序主界面");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}

	/**
	 * 事件监听的函数
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.showtime.setText("当前时间："+Calendar.getInstance().getTime().toLocaleString()+"    ");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==p1_lab2){
			//点击人事管理的标签，换到p3的第二张卡片
			crad3.show(p3, "renshi");
		}else if(e.getSource()==p1_lab3){
			crad3.show(p3, "denglu");
		}else if(e.getSource()==p2_lab1){
			jsp1.setDividerLocation((Toolkit.getDefaultToolkit().getScreenSize().width)-30);
			card2.show(p2, "hidari");
		}else if(e.getSource()==p2_lab2){
			jsp1.setDividerLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/4);
			card2.show(p2, "migi");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 当鼠标进入标签，把标签变成高亮状态
		if(e.getSource()==p1_lab2){
			p1_lab2.setEnabled(true);
		}else if(e.getSource()==p1_lab3){
			p1_lab3.setEnabled(true);
		}else if(e.getSource()==p1_lab4){
			p1_lab4.setEnabled(true);
		}else if(e.getSource()==p1_lab5){
			p1_lab5.setEnabled(true);
		}else if(e.getSource()==p1_lab6){
			p1_lab6.setEnabled(true);
		}else if(e.getSource()==p1_lab7){
			p1_lab7.setEnabled(true);
		}else if(e.getSource()==p1_lab8){
			p1_lab8.setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// 当鼠标离开标签，把标签再变回灰暗不可用状态
		if(e.getSource()==p1_lab2){
			p1_lab2.setEnabled(false);
		}else if(e.getSource()==p1_lab3){
			p1_lab3.setEnabled(false);
		}else if(e.getSource()==p1_lab4){
			p1_lab4.setEnabled(false);
		}else if(e.getSource()==p1_lab5){
			p1_lab5.setEnabled(false);
		}else if(e.getSource()==p1_lab6){
			p1_lab6.setEnabled(false);
		}else if(e.getSource()==p1_lab7){
			p1_lab7.setEnabled(false);
		}else if(e.getSource()==p1_lab8){
			p1_lab8.setEnabled(false);
		}
	}
	

}
