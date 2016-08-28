/**
 * ��Ա�࣬��һ��������Ӱ˵Ķ���д�����Ϊ�˷��㣬�Ͳ��ٶ�����
 */
package com.dragon.tank;

import java.util.*;
import java.awt.Color;

class Boom extends Thread{
	boolean islive=true;
	int x,y;
	//���캯��
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
	//����
	boolean islive=true;
	//̹�˵�����x��y
	int x=0;
	int y=0;
	//̹�˵ķ���Ĭ�����·�
	char fx='s';
	//̹��ӵ�е��ٶȣ�Ĭ��Ϊ1
	int speed=1;
	//̹��ӵ�е���ɫ,Ĭ��Ϊ��ɫ
	Color color=Color.cyan;
	//̹��ӵ���ӵ�
	Zidan z=null;
	
	//̹���ӵ����ٶ�
	int shotSpeed=1;
	
	
	//̹�˵Ĺ��캯��
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
	
	//̹�˵Ŀ���ĺ���
	public void fire(){
		
		//ÿ�ο����newһ���ӵ�
		if(this.fx=='w'){
			//��Ū���ӵ�������
			
			z=new Zidan(x, y-20,this.fx,this.shotSpeed);
			//Ȼ���new���ӵ����뵽zz����
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
	//�����ӵ�������
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
	
	//�ж���û��ײ��ǽ�ڵĺ���
	public boolean crawall(){
		
		if(x<=20||x>=MyPanel.blackweigth-20||y<=20||y>=MyPanel.blackheight-20){
			
			return true;
		}else{
			return false;
		}
	}
	
	
	//�ı䷽��ĺ���
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

//�ӵ���
class Zidan implements Runnable{
	//�ӵ�����
	int x,y;
	//�����ӵ�̹�˵ķ���
	char fx;
	//�����ӵ��ٶ�
	int shotSpeed;
	//�Ƿ���ŵĲ���
	boolean isLive=true;
	//���캯��
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
		//�ж��ӵ���û������ǽ
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
			
			//�ӵ��ƶ��ĺ���
			this.zidanGOGO();
			
			if(this.isLive==false){
				//����ӵ��������˳��߳�
				break;
			}
		}
	}
	
	//�ж��ӵ��Ƿ�����ǽ�ĺ���,����ǽ�ͷ���true��û�����ͷ���false
	public boolean craswall(){
		boolean b=false;//�ȼٶ�û��ײǽ
		
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
