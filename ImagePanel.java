/*
 * ����һ�����Զ�̬����һ��ͼƬ��������JPanel
 * �Լ�����һ������ʹ�õĹ�����
 */
package tools;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;

public class ImagePanel extends JPanel{

	Image im;
	
	//���캯��������ָ�����JPanel�Ĵ�С
	public ImagePanel(Image im){
		this.im=im;
		//ϣ����С������ӦͼƬ��С
		int w=this.getWidth();
		int h=this.getHeight();
		this.setSize(w,h);
	}
	
	//���������ĺ���
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}
}
