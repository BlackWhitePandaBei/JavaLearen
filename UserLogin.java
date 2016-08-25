/*
 * ����һ����è�����Ŀղ��֣�Ȼ������������м�����ֿ����ɵ�¼�о�
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
	//������Ҫ�����
	JLabel jl1,jl2,jl3;
	JTextField jtf;
	JPasswordField jpf;
	JButton jb1,jb2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserLogin ul=new UserLogin();
		
	}
	
	//���캯��
	public UserLogin(){
		Container ct=this.getContentPane();
		this.setLayout(null);
		//����һ��BackImage����
		BackImage bi=new BackImage();
		
		//��ʼ�����
		jl1=new JLabel("�������û�����");
		jl1.setFont(myTools.f1);
		jl1.setBounds(80,200,150,15);
		//����
		this.add(jl1);
		jb1=new JButton("ȷ��");
		jb1.setFont(myTools.f1);
		jb1.addActionListener(this);
		jb1.setBounds(110,300,70,30);
		this.add(jb1);
		
		
		jtf=new JTextField(20);
		jtf.setFont(myTools.f1);
		jtf.setBorder(BorderFactory.createLoweredBevelBorder());
		//�°��о�
		jtf.setBounds(180,190,120,30);
		this.add(jtf);
		
		jl2=new JLabel("(Ա����)");
		jl2.setFont(myTools.f2);
		//����ǰ��ɫ
		jl2.setForeground(Color.red);
		jl2.setBounds(100,210,100,30);
		ct.add(jl2);
		
		jl3=new JLabel("���������룺");
		jl3.setFont(myTools.f1);
		jl3.setBounds(90,240,150,30);
		this.add(jl3);
		
		jpf=new JPasswordField(20);
		jpf.setBounds(180,240,120,30);
		this.add(jpf);
		
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		jb2.setFont(myTools.f1);
		jb2.setBounds(220,300,70,30);
		this.add(jb2);
		
		//����ͼƬ�����Ҫ���ڱ�ǩ�ȵȶ����ĺ���
		bi.setBounds(0, 0, 360, 360);
		this.add(bi);
		
		//��ʹ�ñ߿�
		this.setUndecorated(true);
		this.setSize(360,360);
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(w/2-180,h/2-180);
		this.setVisible(true);
		
	}
	
	//�ڲ���
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

	
	//��Ӧ�û���¼������
	public void actionPerformed(ActionEvent e) {
		//�жϵ���İ�ť���ĸ�
		if(e.getSource()==jb1){//��ť��������ť������Ӧ
			//�����ȷ��
			//ȡ���û������Ա���ź�����
			String uid=jtf.getText().trim();//trim��ȥ��ǰ��հף���StringBufferת����String����ʱ û����.trim()�ᵼ�����벻���Ľ����
			String pwd=new String(jpf.getPassword());//���������ת�����������ˣ���ס����
			UserModel um=new UserModel();
			String zhiwei=um.checkUser(uid, pwd);
			if(zhiwei.equals("����")||zhiwei.equals("����")||zhiwei.equals("�ӳ�")){
				new MainWindow();//����ҳ��
				this.dispose();//�رյ�¼ҳ��
			}else{
				JOptionPane.showMessageDialog(this, "��¼ʧ��\n"+"���û�û�е�¼��Ȩ��");
			}
		}else if(e.getSource()==jb2){
			//�����ȡ��
			//�رմ���
			this.dispose();
		}
		
	}
	
	
}
