/*
 * 这是一个可以动态加载一个图片做背景的JPanel
 * 自己做的一个方便使用的工具类
 */
package tools;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;

public class ImagePanel extends JPanel{

	Image im;
	
	//构造函数，用来指定这个JPanel的大小
	public ImagePanel(Image im){
		this.im=im;
		//希望大小是自适应图片大小
		int w=this.getWidth();
		int h=this.getHeight();
		this.setSize(w,h);
	}
	
	//画出背景的函数
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}
}
