package com.suanshu;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class ConIn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame mf=new MyFrame();
	}

}

class MyFrame extends JFrame implements ActionListener{
	double zongxuefen=0;
	double zongchengji=0;
	double rrr=0;
	ArrayList<Porject> al= new ArrayList<Porject>();
	JPanel jp,jpp;
	JTextField jt1,jt2;
	JLabel jl,xuefen,chengji,result;
	JButton tianjia,qingkong;
	JButton jisuan;

	public MyFrame() {
		// TODO Auto-generated constructor stub
		
		qingkong=new JButton("清空");
		qingkong.addActionListener(this);
		jl=new JLabel("已添加科目数量："+al.size()+"");
		jp=new JPanel();
		xuefen=new JLabel("学分:");
		chengji =new JLabel("成绩:");
		jt1=new JTextField(7);

		jt2=new JTextField(7);
		tianjia=new JButton("添加");
		tianjia.addActionListener(this);
		jp.add(xuefen);
		jp.add(jt1);
		
		jp.add(chengji);
		jp.add(jt2);
		jp.add(tianjia);
		jp.add(jl);
		
		result=new JLabel("平均学分绩为：");
		result.setFont(new Font("宋体",Font.PLAIN,38));
		jp.add(result);
		this.add(jp);
		
		jpp=new JPanel();
		jpp.add(qingkong);
		this.add(jpp,"South");
		this.setSize(400,180);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("平均学分绩计算器");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tianjia){
			if(!(jt1.getText().equals("")||jt2.getText().equals(""))){
				double a=Double.parseDouble(jt1.getText());
				double b=Double.parseDouble(jt2.getText());
				Porject p=new Porject(a, b);
				al.add(p);
				
				zongxuefen+=a;
				zongchengji+=p.all;
				rrr=zongchengji/zongxuefen;
				
				jt1.setText("");
				jt2.setText("");
				
			}else{
				System.out.println("请填入数字");
			}
			
		}else if(e.getSource()==qingkong){
			al=new ArrayList<Porject>();
			 zongxuefen=0;
			 zongchengji=0;
			 rrr=0;
		}
		
		jl.setText("已添加科目数量："+al.size()+"");
		result.setText("平均学分绩为："+rrr);
		this.validate();
	}
	
}

class Porject{
	double xuefen;
	double chengji;
	double all;
	public Porject(double xuefen,double chengji) {
		// TODO Auto-generated constructor stub
		this.xuefen=xuefen;
		this.chengji=chengji;
		this.all=xuefen*chengji;
	}
}
