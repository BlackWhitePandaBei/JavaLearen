/**
 * 成员类，把一大堆杂七杂八的东西写到这里，为了方便，就不再多打包了
 */
package com.dragon.tank;

import java.util.*;
import java.awt.Color;

class Boom extends Thread{
	boolean islive=true;
	int x,y;
	//构造函数
	public Boom(int x,int y){
		this.x=x;
		this.y=y;
		this.islive=true;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true){
			try {
				Thread.sleep(600);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			this.islive=false;
			break;
		}
		
	}
}


class Tank{
	//生死
	boolean islive=true;
	//坦克的坐标x和y
	int x=0;
	int y=0;
	//坦克的方向，默认是下方
	char fx='s';
	//坦克拥有的速度，默认为1
	int speed=1;
	//坦克拥有的颜色,默认为青色
	Color color=Color.cyan;
	//坦克拥有子弹
	Zidan z=null;
	
	//坦克子弹的速度
	int shotSpeed=1;
	
	
	//坦克的构造函数
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	
	
	
	
	public char getFx() {
		return fx;
	}
	public void setFx(char fx) {
		this.fx = fx;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}


class Hero extends Tank{
	boolean islive=true;
	Vector<Zidan> zz=new Vector<Zidan>();
	Zidan z=null;
	char fx='w';
	Color color=Color.yellow;
	int speed=6;
	int shotSpeed=5;
	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	//坦克的开火的函数
	public void fire(){
		
		//每次开火就new一个子弹
		if(this.fx=='w'){
			//先弄到子弹的坐标
			
			z=new Zidan(x, y-20,this.fx,this.shotSpeed);
			//然后把new的子弹加入到zz里面
			zz.add(z);
			
		}
		if(this.fx=='s'){
			
			z=new Zidan(x, y+20,this.fx,this.shotSpeed);
			zz.add(z);
			
		}
		if(this.fx=='a'){
			
			z=new Zidan(x-20, y,this.fx,this.shotSpeed);
			zz.add(z);
			
		}
		if(this.fx=='d'){
			
			z=new Zidan(x+20, y,this.fx,this.shotSpeed);
			zz.add(z);
			
		}
		Thread t=new Thread(z);
		t.start();
		
		
		
	}
	
		
	public char getFx() {
		return fx;
	}
	public void setFx(char fx) {
		this.fx = fx;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}


class EnemyTank extends Tank implements Runnable{
	//存入子弹的向量
	Vector<Zidan> zz=new Vector<Zidan>();
	Zidan z=null;
	boolean b;
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.changedir();
		this.b=this.crawall();
		z=new Zidan(x, y, fx, shotSpeed);
		Thread t2=new Thread(z);
		t2.start();
		zz.add(z);
		
	}
	
	public void gogo(){
		
		int steps=(int)(Math.random()*100);
		if(this.fx=='w'){
			for(int i=0;i<steps;i++){
				if(y>20){
					y--;
				}
				if(this.crawall()){
					break;
				}
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.changedir();
		}
		if(this.fx=='s'){
			for(int i=0;i<steps;i++){
				if(y<MyPanel.blackheight-20){
					y++;
				}
				if(this.crawall()){
					break;
				}
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.changedir();
		}
		if(this.fx=='a'){
			for(int i=0;i<steps;i++){
				if(x>20){
					x--;
				}
				
				if(this.crawall()){
					break;
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.changedir();
		}
		if(this.fx=='d'){
			for(int i=0;i<steps;i++){
				if(x<MyPanel.blackweigth-20){
					x++;
				}
				
				if(this.crawall()){
					break;
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.changedir();
		}
		
		this.b=false;
		
	}
	
	//判断有没有撞到墙壁的函数
	public boolean crawall(){
		
		if(x<=20||x>=MyPanel.blackweigth-20||y<=20||y>=MyPanel.blackheight-20){
			
			return true;
		}else{
			return false;
		}
	}
	
	
	//改变方向的函数
	public void changedir(){
		int dir=(int)(Math.random()*4);
		if(dir==0){
			this.fx= 'w';
		}
		else if(dir==1){
			this.fx= 's';
		}
		else if(dir==2){
			this.fx= 'a';
		}
		else if(dir==3){
			this.fx= 'd';
		}else{
			this.fx= 's';
		}
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(50);
				
				if(!b){
					this.gogo();
				}
				
				if(zz.size()<1&&this.islive==true){
					this.z.fx=this.fx;
					Zidan z=new Zidan(x, y, this.z.fx, shotSpeed);
					
					Thread t=new Thread(z);
					t.start();
					zz.add(z);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			if(this.islive==false){
				break;
			}
		
		}
		
	}
	
}

//子弹类
class Zidan implements Runnable{
	//子弹坐标
	int x,y;
	//发射子弹坦克的方向
	char fx;
	//发射子弹速度
	int shotSpeed;
	//是否活着的布尔
	boolean isLive=true;
	//构造函数
	public Zidan(int x,int y,char fx,int shotSpeed) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.fx=fx;
		this.shotSpeed=shotSpeed;
	}
	
	public void zidanGOGO(){
		if(fx=='w'){
			y-=shotSpeed;
		}
		if(fx=='s'){
			y+=shotSpeed;
		}
		if(fx=='a'){
			x-=shotSpeed;
		}
		if(fx=='d'){
			x+=shotSpeed;
		}
		//判断子弹有没有碰到墙
		if(this.craswall()){
			this.isLive=false;
		}
		

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			//子弹移动的函数
			this.zidanGOGO();
			
			if(this.isLive==false){
				//如果子弹死亡，退出线程
				break;
			}
		}
	}
	
	//判断子弹是否碰到墙的函数,碰到墙就返回true，没碰到就返回false
	public boolean craswall(){
		boolean b=false;//先假定没有撞墙
		
		if(x<=0||x>=MyPanel.blackweigth||y<=0||y>=MyPanel.blackheight){
			b=true;
		}
		
		return b;
	}
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getShotSpeed() {
		return shotSpeed;
	}

	public char getFx() {
		return fx;
	}
	public void setFx(char fx) {
		this.fx = fx;
	}
	public void setShotSpeed(int shotSpeed) {
		this.shotSpeed = shotSpeed;
	}
	public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}	
	
}
