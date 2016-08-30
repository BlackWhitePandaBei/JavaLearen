package com.dragon.tank;
import javax.swing.*;

import java.util.*;
import java.awt.*;
public class MyPanel extends JPanel{
//	public static void mian(String args[]){
//		//�����õ�main
//	}
	//�����ɫ�߿�Ĵ�С
	static int blackweigth=700;
	static int blackheight=500;
	//����hero̹��
	Hero hero=null;
	//�������̹����
	Vector<EnemyTank> enemyTanks=new Vector<EnemyTank>();
	//�������̹�˵�����,��ʼĬ��Ϊ6��
	int enmSize=6;
	//���뵱ǰ��Ļ�ϵ�̹������
	int nowNum=0;
	//����һ�����ֵ�̹������
	int enmNum=0;
	//������һ��ܵ�̹������
	int bitNum=0;
	//ͼƬ
	Image im=null;
	//����ը���ļ���
	Vector<Boom> bb=new Vector<Boom>();
	
	
	//���캯��
	public MyPanel(){
		hero=new Hero(100, 400);
		
		//���˶���̹�˾�ѭ������
		for(int i=1;i<=enmSize;i++){
			//����һ�����˶���
			EnemyTank et=new EnemyTank(i*100, 50);
			//��Ļ�ϼ�1
			this.nowNum++;
			//�ܳ�������1
			this.enmNum++;
			//�ѵ��˶�����뵽Vector����
			enemyTanks.add(et);
			Thread t=new Thread(et);
			t.start();
		}
		
	}
	
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		//��ʼ��ͼƬ
		im=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boom.jpg"));
		//�ѱ������ɺ�ɫ
		g.setColor(Color.black);
		g.fillRect(0, 0, blackweigth,blackheight);
		
		//��ը��
		this.drawBoom(g);
		//��hero
		if(hero.islive){
			this.drawHeroTank(hero.getX(),hero.getY(), g);
		}
		
		//������̹��
		this.drawEnemyTank(g);
		//���ӵ�,����ֻ����hero���ӵ�
		this.drawZidan(g);
		
		
			
		//���ػ溯������paint���棬���ڻ᲻ͣ�����ػ�
		this.repaint();
		
	}
	
	
	public void drawBoom(Graphics g){
		//ȡ�������е�ը��Ȼ��
		for(int i=0;i<bb.size();i++){
			Boom boom=bb.get(i);
			if(boom!=null&&boom.islive==true){
				g.drawImage(im,boom.x-40,boom.y-35,85,70,this);
				
			}
			
			if(boom.islive==false){
				bb.remove(boom);
				
			}
		}
	}
	
	//���ӵ��ĺ���
	//��ֻ����hero�ķ����ӵ�
	public void drawZidan(Graphics g){
		//ȡ�����������е�ÿ���ӵ�Ȼ��
		for(int i=0;i<hero.zz.size();i++){
			//z����ȡ�����ӵ�
			Zidan z=hero.zz.get(i);
			if(z!=null&&z.isLive==true){
				g.setColor(Color.white);
		
				if(hero.z.fx=='w'){
					
					g.fill3DRect(z.x-1, z.y-5, 3, 5, false);
		
				}
				else if(hero.z.fx=='s'){
					
					g.fill3DRect(z.x-1, z.y, 3, 5, false);
		
				}
				else if(hero.z.fx=='a'){
					
					g.fill3DRect(z.x-5, z.y-1, 5, 3, false);
		
				}
				else if(hero.z.fx=='d'){
					
					g.fill3DRect(z.x, z.y-1, 5, 3, false);
		
				}
			}
			//����ӵ����������ӵ���zz��ɾ��
			if(z.isLive==false){
				hero.zz.remove(z);
			}
			
		}
		
		
	}
	
	
	
	public void drawHeroTank(int x,int y,Graphics g){
		this.hitme();
		g.setColor(Color.yellow);
		if(hero.getFx()=='w'){
			//�������ϵ�̹��
			g.fill3DRect(x-8, y-10, 16, 20, false);//�м�ķ���
			g.fill3DRect(x-18, y-15, 10, 30, false);//��ߵķ���
			g.fill3DRect(x+8, y-15, 10, 30, false);//�ұߵķ���
			g.fillOval(x-4, y-4, 8, 8);//�м��Բ
			g.drawLine(x, y, x, y-20);//�м����Ͳ
		}else if(hero.getFx()=='s'){
			//�������µ�̹��
			g.fill3DRect(x-8, y-10, 16, 20, false);
			g.fill3DRect(x-18, y-15, 10, 30, false);
			g.fill3DRect(x+8, y-15, 10, 30, false);
			g.fillOval(x-4, y-4, 8, 8);
			g.drawLine(x, y, x, y+20);
		}else if(hero.getFx()=='a'){
			//���������̹��
			g.fill3DRect(x-10, y-8, 20, 16, false);
			g.fill3DRect(x-15, y-18, 30, 10, false);
			g.fill3DRect(x-15, y+8, 30, 10, false);
			g.fillOval(x-4, y-4, 8, 8);
			g.drawLine(x, y, x-20,y );
		}else if(hero.getFx()=='d'){
			//�������ҵ�̹��
			g.fill3DRect(x-10, y-8, 20, 16, false);
			g.fill3DRect(x-15, y-18, 30, 10, false);
			g.fill3DRect(x-15, y+8, 30, 10, false);
			g.fillOval(x-4, y-4, 8, 8);
			g.drawLine(x, y, x+20,y );
		}
	}
	
	
	
	//���������������̹�˵���״
	public void drawEnemyTank(Graphics g){
		this.hit();
		
		//�ж��ٵ���̹�˾ͻ�����
		for(int i=0;i<enemyTanks.size();i++){
			//���̹�˻��žͻ�
			EnemyTank et=enemyTanks.get(i);
			if(et.islive){
				//���õ���̹����ɫ
				g.setColor(et.getColor());
				//��õ���̹�˵ķ���
				char fx=et.getFx();
				if(fx=='w'){
					g.fill3DRect(et.getX()-8, et.getY()-10, 16, 20, false);//�м�ķ���
					g.fill3DRect(et.getX()-18, et.getY()-15, 10, 30, false);//��ߵķ���
					g.fill3DRect(et.getX()+8, et.getY()-15, 10, 30, false);//�ұߵķ���
					g.fillOval(et.getX()-4, et.getY()-4, 8, 8);//�м��Բ
					g.drawLine(et.getX(), et.getY(), et.getX(), et.getY()-20);//�м����Ͳ
				}else if(fx=='s'){
					g.fill3DRect(et.getX()-8, et.getY()-10, 16, 20, false);
					g.fill3DRect(et.getX()-18, et.getY()-15, 10, 30, false);
					g.fill3DRect(et.getX()+8, et.getY()-15, 10, 30, false);
					g.fillOval(et.getX()-4, et.getY()-4, 8, 8);
					g.drawLine(et.getX(), et.getY(), et.getX(), et.getY()+20);
				}else if(fx=='a'){
					g.fill3DRect(et.getX()-10, et.getY()-8, 20, 16, false);
					g.fill3DRect(et.getX()-15, et.getY()-18, 30, 10, false);
					g.fill3DRect(et.getX()-15, et.getY()+8, 30, 10, false);
					g.fillOval(et.getX()-4, et.getY()-4, 8, 8);
					g.drawLine(et.getX(), et.getY(), et.getX()-20,et.getY() );
				}else if(fx=='d'){
					g.fill3DRect(et.getX()-10, et.getY()-8, 20, 16, false);
					g.fill3DRect(et.getX()-15, et.getY()-18, 30, 10, false);
					g.fill3DRect(et.getX()-15, et.getY()+8, 30, 10, false);
					g.fillOval(et.getX()-4, et.getY()-4, 8, 8);
					g.drawLine(et.getX(), et.getY(), et.getX()+20,et.getY() );
				}
			}
			//����̹�˵��ӵ�
			for(int j=0;j<et.zz.size();j++){
				//����zz����
				Zidan z=et.zz.get(j);
				if(z.isLive){
					g.setColor(Color.white);
			
					if(et.z.fx=='w'){
						
						g.fill3DRect(z.x-1, z.y-5, 3, 5, false);
			
					}
					else if(et.z.fx=='s'){
						
						g.fill3DRect(z.x-1, z.y, 3, 5, false);
			
					}
					else if(et.z.fx=='a'){
						
						g.fill3DRect(z.x-5, z.y-1, 5, 3, false);
			
					}
					else if(et.z.fx=='d'){
						
						g.fill3DRect(z.x, z.y-1, 5, 3, false);
			
					}
				}
				//����ӵ����������ӵ���zz��ɾ��
				if(z.isLive==false){
					et.zz.remove(z);
				}
			}
		}
	}
	
	public void hitme(){
		
		for(int i=0;i<enemyTanks.size();i++){
			//ȡ��ÿ������̹��
			EnemyTank et=enemyTanks.get(i);
			for(int j=0;j<et.zz.size();j++){
				//ȡ��ÿ���ӵ�
				Zidan z=et.zz.get(j);
				if(hero.islive){
					if(ping(z, hero)){
						hero.islive=false;
					}	
				}
				
			}
		}
		
		
		
	}
	
	public boolean hit(){
		boolean b=false;
		for(int i=0;i<this.hero.zz.size();i++){
			//ȡ���ӵ�
			Zidan z=hero.zz.get(i);
			if(z.isLive){
				//ȡ��ÿ��̹�ˣ��ж����ӵ�ײ��û��
				for(int j=0;j<this.enemyTanks.size();j++){
					//ȡ��̹��
					EnemyTank et=enemyTanks.get(j);
					if(et.islive){
						this.ping(z,et);
						
						b=true;
					}
				}
			}
		}
		return b;
	}
	
	public boolean ping(Zidan z,Tank t){
		boolean b=false;
		
		switch (t.fx) {
		case 'w':
		case 's':
			if(z.x>t.x-18&&z.x<t.x+18&&z.y>t.y-15&&z.y<t.y+15){
				b=true;
				//�ӵ�������
				z.isLive=false;
				//̹��������
				t.islive=false;
				
				//newһ��boom,Ȼ���boom���뵽bb��
				Boom boom=new Boom(t.x, t.y);
				bb.add(boom);
				boom.start();
				
			}
			
			break;
		case 'a':
		case 'd':
			if(z.x>t.x-15&&z.x<t.x+15&&z.y>t.y-18&&z.y<t.y+18){
				b=true;
				//�ӵ�������
				z.isLive=false;
				//̹��������
				t.islive=false;
				//newһ��boom,Ȼ���boom���뵽bb��
				Boom boom=new Boom(t.x, t.y);
				bb.add(boom);
				boom.start();
			}
			break;
		}
		return b;
	}
	
	
	
	
}
