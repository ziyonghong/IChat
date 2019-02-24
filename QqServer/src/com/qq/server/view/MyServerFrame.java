package com.qq.server.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.qq.server.model.MyQqServer;

/*
 * 服务器端的控制界面，可以完成启动/关闭服务器
 * 管理和监控用户
 */
public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1,jb2;
	public static void main(String[] args) {
		MyServerFrame myServerFrame=new MyServerFrame();
				
	}
	
	public MyServerFrame(){
		jp1=new JPanel();
		jb1=new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			new MyQqServer();
		}
		
	}
}
