/*
 * 制作一个熊猫背景的空布局，然后再这个布局中加入各种框，做成登录感觉
 */
package com.jiemian;

import javax.swing.*;
import com.model.*;

import tools.myTools;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.*;

public class UserLogin extends JDialog implements ActionListener{
	//定义需要的组件
	JLabel jl1,jl2,jl3;
	JTextField jtf;
	JPasswordField jpf;
	JButton jb1,jb2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserLogin ul=new UserLogin();
		
	}
	
	//构造函数
	public UserLogin(){
		Container ct=this.getContentPane();
		this.setLayout(null);
		//创建一个BackImage对象
		BackImage bi=new BackImage();
		
		//初始化组件
		jl1=new JLabel("请输入用户名：");
		jl1.setFont(myTools.f1);
		jl1.setBounds(80,200,150,15);
		//放入
		this.add(jl1);
		jb1=new JButton("确定");
		jb1.setFont(myTools.f1);
		jb1.addActionListener(this);
		jb1.setBounds(110,300,70,30);
		this.add(jb1);
		
		
		jtf=new JTextField(20);
		jtf.setFont(myTools.f1);
		jtf.setBorder(BorderFactory.createLoweredBevelBorder());
		//下凹感觉
		jtf.setBounds(180,190,120,30);
		this.add(jtf);
		
		jl2=new JLabel("(员工号)");
		jl2.setFont(myTools.f2);
		//设置前景色
		jl2.setForeground(Color.red);
		jl2.setBounds(100,210,100,30);
		ct.add(jl2);
		
		jl3=new JLabel("请输入密码：");
		jl3.setFont(myTools.f1);
		jl3.setBounds(90,240,150,30);
		this.add(jl3);
		
		jpf=new JPasswordField(20);
		jpf.setBounds(180,240,120,30);
		this.add(jpf);
		
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		jb2.setFont(myTools.f1);
		jb2.setBounds(220,300,70,30);
		this.add(jb2);
		
		//加入图片的这个要放在标签等等东西的后面
		bi.setBounds(0, 0, 360, 360);
		this.add(bi);
		
		//不使用边框
		this.setUndecorated(true);
		this.setSize(360,360);
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(w/2-180,h/2-180);
		this.setVisible(true);
		
	}
	
	//内部类
	class BackImage extends JPanel{
		Image im;
		public BackImage(){
			try {
				im=ImageIO.read(new File("C:\\Users\\Administraor\\Desktop\\pic\\panda.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g){
			g.drawImage(im,0,0,360,360,this);
		}
	}

	
	//响应用户登录的请求
	public void actionPerformed(ActionEvent e) {
		//判断点击的按钮是哪个
		if(e.getSource()==jb1){//按钮监听，按钮监听响应
			//这个是确定
			//取出用户输入的员工号和密码
			String uid=jtf.getText().trim();//trim是去掉前后空白，把StringBuffer转换成String类型时 没有用.trim()会导致意想不到的结果。
			String pwd=new String(jpf.getPassword());//这里的类型转换，我无语了，记住得了
			UserModel um=new UserModel();
			String zhiwei=um.checkUser(uid, pwd);
			if(zhiwei.equals("作者")||zhiwei.equals("国王")||zhiwei.equals("队长")){
				new MainWindow();//打开主页面
				this.dispose();//关闭登录页面
			}else{
				JOptionPane.showMessageDialog(this, "登录失败\n"+"该用户没有登录的权限");
			}
		}else if(e.getSource()==jb2){
			//这个是取消
			//关闭窗口
			this.dispose();
		}
		
	}
	
	
}
