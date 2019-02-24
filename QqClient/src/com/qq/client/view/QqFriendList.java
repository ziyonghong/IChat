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
 * QQ�����б� ����Ƭ���֣�
 */
public class QqFriendList extends JFrame implements ActionListener, MouseListener {

	// �����һ�ſ�Ƭ���ҵĺ��ѣ�
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	JScrollPane jsp1;
     String owner;
	// ����ڶ��ſ�Ƭ��İ���ˣ�
	JPanel jpmsr1, jpmsr2, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	JScrollPane jsp2;
	JLabel[] jbls;
	// ������JFrame���ó�CardLayout����
	CardLayout cl;

	public static void main(String[] args) {
		
		//QqFriendList qqFriendList = new QqFriendList();
	}
	//�������ߺ��ѵ����
	public void updateFriend(Message m){
		String onLineFriend[]=m.getCon().split(" ");
		
		for(int i=0;i<onLineFriend.length;i++){
		
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}

	//ownerId�Լ���ID
	public QqFriendList(String ownerId) {
		this.owner=ownerId;
		// �����һ�ſ�Ƭ(��ʾ�����б�)
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb1.addActionListener(this);
		jphy_jb2 = new JButton("İ����");
		jphy_jb2.addActionListener(this);

		jphy_jb3 = new JButton("������");
		jphy1 = new JPanel(new BorderLayout());
		// �ٶ���50������
		jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));// 4,4�������м��

		// ��jphy2����ʼ��50������
		 jbls = new JLabel[50];
		for (int i = 0; i < 50; i++) {
			jbls[i] = new JLabel(i + 1 + "", new ImageIcon("image/5.png"), JLabel.LEFT);
			jbls[i].setEnabled(false);//Ĭ�ϲ�����
			if(jbls[i].getText().equals(ownerId)){
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}

		jphy3 = new JPanel(new GridLayout(2, 1));
		// ��������ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);

		jsp1 = new JScrollPane(jphy2);

		// ��jphy1��ʼ��
		jphy1.add(jphy_jb1, "North");
		jphy1.add(jsp1, "Center");
		jphy1.add(jphy3, "South");

		// ����ڶ��ſ�Ƭ
		jpmsr_jb1 = new JButton("�ҵĺ���");
		jpmsr_jb2 = new JButton("İ����");
		jpmsr_jb3 = new JButton("������");
		jpmsr1 = new JPanel(new BorderLayout());
		// �ٶ���20��İ����
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));// 4,4�������м��

		// ��jphy2����ʼ��20��İ����
		JLabel[] jbls2 = new JLabel[20];
		for (int i = 0; i < 20; i++) {
			jbls2[i] = new JLabel(i + 1 + "", new ImageIcon("image/5.png"), JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
		}

		jpmsr3 = new JPanel(new GridLayout(2, 1));
		// ��������ť���뵽jpmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);

		jsp2 = new JScrollPane(jpmsr2);

		// ��jpmsr1��ʼ��
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jsp2, "Center");
		jpmsr1.add(jpmsr_jb3, "South");

		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		// this.add(jphy1, "Center");
		
		//�ڴ�����ʾ�Լ��ı��
		this.setTitle(owner);
		this.setSize(160, 500);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ��������İ���˰�ť������ʾ�ڶ��ſ�Ƭ
		if (e.getSource() == jphy_jb2) {
			cl.show(this.getContentPane(), "2");
		} else if (e.getSource() == jpmsr_jb1) {
			cl.show(this.getContentPane(), "1");
		}
	}

	public void mouseClicked(MouseEvent e) {
		// ��Ӧ�û�˫�����¼������õ����ѵı��
		if (e.getClickCount() == 2) {
         //�õ��ú��ѵı��
			String friendNo=((JLabel)e.getSource()).getText();
			System.out.println("��ϣ���� "+friendNo+" ����");
			
			QqChat qqChat=new QqChat(this.owner,friendNo);
//			Thread t=new Thread(qqChat);
//			t.start();
			
			//�����������뵽������
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
		jl.setForeground(Color.RED);// ����ǰ��ɫ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.BLACK);// ����ǰ��ɫ

	}
}
