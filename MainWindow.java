/*
 * һ���Ƚϸ��ӵ��������������
 * ��ɽ����˳�򡣴��ϵ��£�������.
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
	//������Ҫ�����
	Image titelIcon,timeBG;//ImageIconָͼ��ͼ��,����ò�����ϽǵĲ���Icon������ֱ�Ӷ���ȥһ��Image
	//�˵���һ������
	JMenuBar jmb;//���ϲ�Ĳ˵���
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;//�˵��µĸ����ֶ�
	JMenuItem jmm1,jmm2,jmm3,jmm4,jmm5;//�����ֶ��µ�ѡ��
	//ͼ��
	ImageIcon jmm1_iocn,jmm2_iocn,jmm3_iocn,jmm4_iocn,jmm5_iocn;
	//�˵�����Ĺ�����
	JToolBar jtb;//������������
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//��Ҫ��5��JPanel
	JPanel p1,p2,p3,p4,p5;
	//��ʾʱ���JLabel
	JLabel showtime;
	//p1����µ�8����ǩ
	JLabel p1_lab1,p1_lab2,p1_lab3,p1_lab4,p1_lab5,p1_lab6,p1_lab7,p1_lab8;
	//p2����µı�ǩ
	JLabel p2_lab1,p2_lab2;
	//javax.swing���е�Timer�࣬���Զ�ʱ��Ĵ���Action�¼����������������һЩ����
	javax.swing.Timer t;
	//p1����µı���ͼƬ
	ImagePanel p1_panel;
	Image p1_bg=null;
	//���p1��p4�Ĳ�ִ���
	JSplitPane jsp1=null;
	CardLayout card2=null;
	CardLayout crad3=null;
	public static void main(String[] args) {
		
		MainWindow mw=new MainWindow();
	}
	
	/**
	 * ���ù��캯����һ�ѽ���Ķ����ܷ�����������ȫŪ�˳���
	 */
	public void myJiemian(){
		//�������
				try {
					titelIcon=ImageIO.read(new File("image/tv_dark.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//����ͼ��
				jmm1_iocn=new ImageIcon("image/cabin.png");
				jmm2_iocn=new ImageIcon("image/sign_explain.png");
				jmm3_iocn=new ImageIcon("image/down.png");
				jmm4_iocn=new ImageIcon("image/sign_copyright.png");
				jmm5_iocn=new ImageIcon("image/spanner_blue.png");
				//��jm1���˵��ֶ�
				jm1=new JMenu("ϵͳ����");
				jm1.setFont(myTools.f1);
				//����jm1�Ĳ˵�ѡ��
				jmm1=new JMenuItem("�л��û�",jmm1_iocn);//ֱ�ӾͿ��԰�ͼ��Ž�ѡ����
				jmm1.setFont(myTools.f2);
				jmm2=new JMenuItem("�л����տ����",jmm2_iocn);
				jmm2.setFont(myTools.f2);
				jmm3=new JMenuItem("��¼����",jmm3_iocn);
				jmm3.setFont(myTools.f2);
				jmm4=new JMenuItem("������",jmm4_iocn);
				jmm4.setFont(myTools.f2);
				jmm5=new JMenuItem("�˳�",jmm5_iocn);
				jmm5.setFont(myTools.f2);
				//���뵽jm1
				jm1.add(jmm1);
				jm1.add(jmm2);
				jm1.add(jmm3);
				jm1.add(jmm4);
				jm1.add(jmm5);
				//��jm2���˵��ֶ�
				jm2=new JMenu("���¹���");
				jm2.setFont(myTools.f1);
				//��jm3���˵��ֶ�
				jm3=new JMenu("�˵�����");
				jm3.setFont(myTools.f1);
				//��jm4���˵��ֶ�
				jm4=new JMenu("����ͳ��");
				jm4.setFont(myTools.f1);
				//��jm5���˵��ֶ�
				jm5=new JMenu("�ɱ����ⷿ");
				jm5.setFont(myTools.f1);
				//��jm6���˵��ֶ�
				jm6=new JMenu("����");
				jm6.setFont(myTools.f1);
				//�������ϼ��˵�
				jmb=new JMenuBar();
				//���ֶβ˵����뵽���ϼ��˵�
				jmb.add(jm1);
				jmb.add(jm2);
				jmb.add(jm3);
				jmb.add(jm4);
				jmb.add(jm5);
				jmb.add(jm6);
				//JFrame�м������ϼ��˵�
				this.setJMenuBar(jmb);//�˵��ļ�����䣬����˵���䲻Ҫ���
				//�������������
				jtb=new JToolBar();
				//���ù������������ƶ����ٷ�˵���ǲ�������
				jtb.setFloatable(false);
				jb1=new JButton(new ImageIcon("image/jb1.gif"));//��JButton��ʾһ��ͼƬ
				jb2=new JButton(new ImageIcon("image/jb2.gif"));
				jb3=new JButton(new ImageIcon("image/jb3.gif"));
				jb4=new JButton(new ImageIcon("image/jb4.gif"));
				jb5=new JButton(new ImageIcon("image/jb5.gif"));
				jb6=new JButton(new ImageIcon("image/jb6.gif"));
				jb7=new JButton(new ImageIcon("image/jb7.gif"));
				jb8=new JButton(new ImageIcon("image/jb8.gif"));
				jb9=new JButton(new ImageIcon("image/jb9.gif"));
				jb10=new JButton(new ImageIcon("image/jb10.gif"));
				//�Ѱ�ť���뵽������jtb
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
				
				
				//����p1���
				p1=new JPanel(new BorderLayout());
				try {
					p1_bg=ImageIO.read(new File("image/p1_bg.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p1_panel=new ImagePanel(p1_bg);
				p1_panel.setLayout(new GridLayout(8,1));//����Ϊ8��1�е����񲼾�
				//��ʼ��8��Label
				p1_lab1=new JLabel(new ImageIcon("image/dongtai.gif"));//����ͼ��
				p1_lab2=new JLabel("�� �� �� ��",new ImageIcon("image/xinxin.png"),0);//��ǩ�м������ֺ�ͼƬ��0��������������
				p1_lab2.setFont(myTools.f3);
				p1_lab2.setEnabled(false);
				//��ǩע�����
				p1_lab2.addMouseListener(this);
				p1_lab2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab3=new JLabel("�� ¼ �� ��",new ImageIcon("image/theboy.png"),0);
				p1_lab3.setFont(myTools.f3);
				p1_lab3.setEnabled(false);
				p1_lab3.addMouseListener(this);
				p1_lab3.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab4=new JLabel("�� �� �� ��",new ImageIcon("image/xoo.png"),0);
				p1_lab4.setFont(myTools.f3);
				p1_lab4.setEnabled(false);
				p1_lab4.addMouseListener(this);
				p1_lab4.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab5=new JLabel("�� �� ͳ ��",new ImageIcon("image/wowo.png"),0);
				p1_lab5.setFont(myTools.f3);
				p1_lab5.setEnabled(false);
				p1_lab5.addMouseListener(this);
				p1_lab5.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab6=new JLabel("�ɱ����ⷿ",new ImageIcon("image/45678.png"),0);
				p1_lab6.setFont(myTools.f3);
				p1_lab6.setEnabled(false);
				p1_lab6.addMouseListener(this);
				p1_lab6.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab7=new JLabel("ϵ ͳ �� ��",new ImageIcon("image/abc.png"),0);
				p1_lab7.setFont(myTools.f3);
				p1_lab7.setEnabled(false);
				p1_lab7.addMouseListener(this);
				p1_lab7.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p1_lab8=new JLabel("�� �� �� ��",new ImageIcon("image/ghj.png"),0);
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
				
				
				
				//����p2,p3,p4�������,p2��p3�Ǳ�p4��������
				card2=new CardLayout();
				crad3=new CardLayout();
				p4=new JPanel(new BorderLayout());
				p2=new JPanel(card2);
				p3=new JPanel(crad3);
				
				
				//p2��������
				p2_lab1=new JLabel(new ImageIcon("image/migi.png"));
				p2_lab1.addMouseListener(this);
				p2_lab1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				p2_lab2=new JLabel(new ImageIcon("image/hidari.png"));
				p2_lab2.addMouseListener(this);
				p2_lab2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//����label���뵽p2��
				p2.add(p2_lab1,"migi");
				p2.add(p2_lab2,"hidari");
				
				
				//����p3
				//�ȸ�P3����һ��������Ŀ�Ƭ
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
				
				
				//��һ����ִ��ڣ����p1��p4
				jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,p1,p4);
				//HORIZONTAL_SPLIT�������ָ�
				//ָ����ߵ����ռ���,����д����ռ����Ļ���ȵ�1/4
				jsp1.setDividerLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/4);
				//�ѱ߽�������Ϊ0��Ҳ���ǲ���ʾ
				jsp1.setDividerSize(0);
				
				
				//��p2��p3���뵽p4
				p4.add(p2,"West");//p2�����
				p4.add(p3,"Center");
				//����p5���ײ�����壬����״̬������״̬������ʾ��ǰʱ��
				p5=new JPanel(new BorderLayout());
				t=new Timer(1000,this);//ÿ��1000���룬����һ��ActionEvent
				//������ʱ��
				t.start();
				//��ʾ��ǰʱ�����showtime�ı�ǩ�У�����Ҫ�������util��
				showtime=new JLabel(Calendar.getInstance().getTime().toLocaleString());
				showtime.setFont(myTools.f2);
				try {
					timeBG=ImageIO.read(new File("image/bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImagePanel ip1=new ImagePanel(timeBG);
				ip1.setLayout(new BorderLayout());//JPanel���ò���
				
				ip1.add(showtime,"East");
				p5.add(ip1);
	}
	//���캯��
	public MainWindow(){
		//���ý��溯��
		this.myJiemian();
		
		//�м����һ�ѵ����
		this.add(jsp1,"Center");
		//�ѹ��������뵽JFrame�ı���
		this.add(jtb,"North");
		//��p5�ŵ�JFrame���ϲ�
		this.add(p5,"South");
		//���ô�����Сͼ�����ʾ,���Ͻ�Сͼ��
		this.setIconImage(titelIcon);
		//���ô�����ʾ
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h-30);
		this.setTitle("����������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}

	/**
	 * �¼������ĺ���
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.showtime.setText("��ǰʱ�䣺"+Calendar.getInstance().getTime().toLocaleString()+"    ");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==p1_lab2){
			//������¹���ı�ǩ������p3�ĵڶ��ſ�Ƭ
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
		// ���������ǩ���ѱ�ǩ��ɸ���״̬
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
		// ������뿪��ǩ���ѱ�ǩ�ٱ�ػҰ�������״̬
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
