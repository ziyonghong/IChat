package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.qq.client.tools.ManageQqChat;
import com.qq.common.Message;

/*
 * QQ好友列表 （卡片布局）
 */
public class QqFriendList extends JFrame implements ActionListener, MouseListener {

	// 处理第一张卡片（我的好友）
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	JScrollPane jsp1;
     String owner;
	// 处理第二张卡片（陌生人）
	JPanel jpmsr1, jpmsr2, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	JScrollPane jsp2;
	JLabel[] jbls;
	// 把整个JFrame设置成CardLayout布局
	CardLayout cl;

	public static void main(String[] args) {
		
		//QqFriendList qqFriendList = new QqFriendList();
	}
	//更新在线好友的情况
	public void updateFriend(Message m){
		String onLineFriend[]=m.getCon().split(" ");
		
		for(int i=0;i<onLineFriend.length;i++){
		
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}

	//ownerId自己的ID
	public QqFriendList(String ownerId) {
		this.owner=ownerId;
		// 处理第一张卡片(显示好友列表)
		jphy_jb1 = new JButton("我的好友");
		jphy_jb1.addActionListener(this);
		jphy_jb2 = new JButton("陌生人");
		jphy_jb2.addActionListener(this);

		jphy_jb3 = new JButton("黑名单");
		jphy1 = new JPanel(new BorderLayout());
		// 假定有50个好友
		jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));// 4,4代表行列间距

		// 给jphy2，初始化50个好友
		 jbls = new JLabel[50];
		for (int i = 0; i < 50; i++) {
			jbls[i] = new JLabel(i + 1 + "", new ImageIcon("image/5.png"), JLabel.LEFT);
			jbls[i].setEnabled(false);//默认不在线
			if(jbls[i].getText().equals(ownerId)){
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}

		jphy3 = new JPanel(new GridLayout(2, 1));
		// 把两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);

		jsp1 = new JScrollPane(jphy2);

		// 对jphy1初始化
		jphy1.add(jphy_jb1, "North");
		jphy1.add(jsp1, "Center");
		jphy1.add(jphy3, "South");

		// 处理第二张卡片
		jpmsr_jb1 = new JButton("我的好友");
		jpmsr_jb2 = new JButton("陌生人");
		jpmsr_jb3 = new JButton("黑名单");
		jpmsr1 = new JPanel(new BorderLayout());
		// 假定有20个陌生人
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));// 4,4代表行列间距

		// 给jphy2，初始化20个陌生人
		JLabel[] jbls2 = new JLabel[20];
		for (int i = 0; i < 20; i++) {
			jbls2[i] = new JLabel(i + 1 + "", new ImageIcon("image/5.png"), JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
		}

		jpmsr3 = new JPanel(new GridLayout(2, 1));
		// 把两个按钮加入到jpmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);

		jsp2 = new JScrollPane(jpmsr2);

		// 对jpmsr1初始化
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jsp2, "Center");
		jpmsr1.add(jpmsr_jb3, "South");

		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		// this.add(jphy1, "Center");
		
		//在窗口显示自己的编号
		this.setTitle(owner);
		this.setSize(160, 500);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 如果点击了陌生人按钮，就显示第二张卡片
		if (e.getSource() == jphy_jb2) {
			cl.show(this.getContentPane(), "2");
		} else if (e.getSource() == jpmsr_jb1) {
			cl.show(this.getContentPane(), "1");
		}
	}

	public void mouseClicked(MouseEvent e) {
		// 响应用户双击的事件，并得到好友的编号
		if (e.getClickCount() == 2) {
         //得到该好友的编号
			String friendNo=((JLabel)e.getSource()).getText();
			System.out.println("你希望和 "+friendNo+" 聊天");
			
			QqChat qqChat=new QqChat(this.owner,friendNo);
//			Thread t=new Thread(qqChat);
//			t.start();
			
			//把聊天界面加入到管理类
			ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
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
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.RED);// 设置前景色

	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.BLACK);// 设置前景色

	}
}
