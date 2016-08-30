package com.dragon.tank;
import javax.swing.*;

import java.util.*;
import java.awt.*;
public class MyPanel extends JPanel{
//	public static void mian(String args[]){
//		//调试用的main
//	}
	//定义黑色边框的大小
	static int blackweigth=700;
	static int blackheight=500;
	//定义hero坦克
	Hero hero=null;
	//定义敌人坦克组
	Vector<EnemyTank> enemyTanks=new Vector<EnemyTank>();
	//定义敌人坦克的数量,初始默认为6个
	int enmSize=6;
	//存入当前屏幕上的坦克数量
	int nowNum=0;
	//存入一共出现的坦克数量
	int enmNum=0;
	//存入玩家击败的坦克数量
	int bitNum=0;
	//图片
	Image im=null;
	//定义炸弹的集合
	Vector<Boom> bb=new Vector<Boom>();
	
	
	//构造函数
	public MyPanel(){
		hero=new Hero(100, 400);
		
		//敌人多少坦克就循环几回
		for(int i=1;i<=enmSize;i++){
			//创建一个敌人对象
			EnemyTank et=new EnemyTank(i*100, 50);
			//屏幕上加1
			this.nowNum++;
			//总出现数加1
			this.enmNum++;
			//把敌人对象加入到Vector里面
			enemyTanks.add(et);
			Thread t=new Thread(et);
			t.start();
		}
		
	}
	
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		//初始化图片
		im=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boom.jpg"));
		//把背景画成黑色
		g.setColor(Color.black);
		g.fillRect(0, 0, blackweigth,blackheight);
		
		//画炸弹
		this.drawBoom(g);
		//画hero
		if(hero.islive){
			this.drawHeroTank(hero.getX(),hero.getY(), g);
		}
		
		//画敌人坦克
		this.drawEnemyTank(g);
		//画子弹,现在只考虑hero的子弹
		this.drawZidan(g);
		
		
			
		//把重绘函数放在paint里面，窗口会不停进行重绘
		this.repaint();
		
	}
	
	
	public void drawBoom(Graphics g){
		//取出向量中的炸弹然后画
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
	
	//画子弹的函数
	//先只考虑hero的发射子弹
	public void drawZidan(Graphics g){
		//取出向量集合中的每颗子弹然后画
		for(int i=0;i<hero.zz.size();i++){
			//z代表取出的子弹
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
			//如果子弹死亡，把子弹从zz中删除
			if(z.isLive==false){
				hero.zz.remove(z);
			}
			
		}
		
		
	}
	
	
	
	public void drawHeroTank(int x,int y,Graphics g){
		this.hitme();
		g.setColor(Color.yellow);
		if(hero.getFx()=='w'){
			//方向向上的坦克
			g.fill3DRect(x-8, y-10, 16, 20, false);//中间的方块
			g.fill3DRect(x-18, y-15, 10, 30, false);//左边的方块
			g.fill3DRect(x+8, y-15, 10, 30, false);//右边的方块
			g.fillOval(x-4, y-4, 8, 8);//中间的圆
			g.drawLine(x, y, x, y-20);//中间的炮筒
		}else if(hero.getFx()=='s'){
			//方向向下的坦克
			g.fill3DRect(x-8, y-10, 16, 20, false);
			g.fill3DRect(x-18, y-15, 10, 30, false);
			g.fill3DRect(x+8, y-15, 10, 30, false);
			g.fillOval(x-4, y-4, 8, 8);
			g.drawLine(x, y, x, y+20);
		}else if(hero.getFx()=='a'){
			//方向向左的坦克
			g.fill3DRect(x-10, y-8, 20, 16, false);
			g.fill3DRect(x-15, y-18, 30, 10, false);
			g.fill3DRect(x-15, y+8, 30, 10, false);
			g.fillOval(x-4, y-4, 8, 8);
			g.drawLine(x, y, x-20,y );
		}else if(hero.getFx()=='d'){
			//方向向右的坦克
			g.fill3DRect(x-10, y-8, 20, 16, false);
			g.fill3DRect(x-15, y-18, 30, 10, false);
			g.fill3DRect(x-15, y+8, 30, 10, false);
			g.fillOval(x-4, y-4, 8, 8);
			g.drawLine(x, y, x+20,y );
		}
	}
	
	
	
	//这个函数画出敌人坦克的形状
	public void drawEnemyTank(Graphics g){
		this.hit();
		
		//有多少敌人坦克就话几次
		for(int i=0;i<enemyTanks.size();i++){
			//如果坦克活着就画
			EnemyTank et=enemyTanks.get(i);
			if(et.islive){
				//设置敌人坦克颜色
				g.setColor(et.getColor());
				//获得敌人坦克的方向
				char fx=et.getFx();
				if(fx=='w'){
					g.fill3DRect(et.getX()-8, et.getY()-10, 16, 20, false);//中间的方块
					g.fill3DRect(et.getX()-18, et.getY()-15, 10, 30, false);//左边的方块
					g.fill3DRect(et.getX()+8, et.getY()-15, 10, 30, false);//右边的方块
					g.fillOval(et.getX()-4, et.getY()-4, 8, 8);//中间的圆
					g.drawLine(et.getX(), et.getY(), et.getX(), et.getY()-20);//中间的炮筒
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
			//画出坦克的子弹
			for(int j=0;j<et.zz.size();j++){
				//遍历zz集合
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
				//如果子弹死亡，把子弹从zz中删除
				if(z.isLive==false){
					et.zz.remove(z);
				}
			}
		}
	}
	
	public void hitme(){
		
		for(int i=0;i<enemyTanks.size();i++){
			//取出每个敌人坦克
			EnemyTank et=enemyTanks.get(i);
			for(int j=0;j<et.zz.size();j++){
				//取出每个子弹
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
			//取出子弹
			Zidan z=hero.zz.get(i);
			if(z.isLive){
				//取出每个坦克，判断于子弹撞到没有
				for(int j=0;j<this.enemyTanks.size();j++){
					//取出坦克
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
				//子弹死翘翘
				z.isLive=false;
				//坦克死翘翘
				t.islive=false;
				
				//new一个boom,然后把boom加入到bb中
				Boom boom=new Boom(t.x, t.y);
				bb.add(boom);
				boom.start();
				
			}
			
			break;
		case 'a':
		case 'd':
			if(z.x>t.x-15&&z.x<t.x+15&&z.y>t.y-18&&z.y<t.y+18){
				b=true;
				//子弹死翘翘
				z.isLive=false;
				//坦克死翘翘
				t.islive=false;
				//new一个boom,然后把boom加入到bb中
				Boom boom=new Boom(t.x, t.y);
				bb.add(boom);
				boom.start();
			}
			break;
		}
		return b;
	}
	
	
	
	
}
