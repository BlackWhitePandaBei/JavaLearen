/**
 * 暂时是先画出坦克
 */
package com.dragon.tank;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class MyFrame extends JFrame implements KeyListener{
	
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame mf=new MyFrame();
	}
	
	//构造函数
	public MyFrame(){
		mp=new MyPanel();
		
		this.add(mp);
		this.addKeyListener(this);
		this.setSize(900,650);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W){
			if(this.mp.hero.getFx()!='w'){
				mp.hero.setFx('w');
			}
			this.heroGo();
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S){
			if(this.mp.hero.getFx()!='s'){
				mp.hero.setFx('s');
			}
			this.heroGo();
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A){
			if(this.mp.hero.getFx()!='a'){
				mp.hero.setFx('a');
			}
			this.heroGo();
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D){
			if(this.mp.hero.getFx()!='d'){
				mp.hero.setFx('d');
			}
			this.heroGo();
			
		}	
		if(e.getKeyCode()==KeyEvent.VK_J||e.getKeyCode()==KeyEvent.VK_X){
			//单按下J或者X，调用开火函数
			if(mp.hero.zz.size()<4){
				mp.hero.fire();
			}
			
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//我的坦克上下左右走的方法
	public void heroGo(){
		
		if(mp.hero.getFx()=='w'){
			if(mp.hero.y>20){
				this.mp.hero.y-=mp.hero.getSpeed();
			}
		}else if(mp.hero.getFx()=='s'){
			if(mp.hero.y<mp.blackheight-20){
				this.mp.hero.y+=mp.hero.getSpeed();
			}
		}else if(mp.hero.getFx()=='a'){
			if(mp.hero.x>20){
				this.mp.hero.x-=mp.hero.getSpeed();
			}
		}else if(mp.hero.getFx()=='d'){
			if(mp.hero.x<mp.blackweigth-20){
				this.mp.hero.x+=mp.hero.getSpeed();
			}
		}
		
	}

	
}


