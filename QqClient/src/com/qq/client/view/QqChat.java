package com.qq.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qq.client.model.QqClientConServer;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

/*
 * 与好友聊天的界面
 * 因为客户端要处于读取的状态，因此要把它做成一个线程
 */
public class QqChat extends JFrame implements ActionListener{
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;// 客户端的编号
	String friendId;// 和正在聊天的好友的编号

	public static void main(String[] args) {
		// 测试
		// QqChat qqChat=new QqChat("1");
	}

	public QqChat(String ownerId, String friend) {
		this.ownerId = ownerId;
		this.friendId = friend;
		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);

		this.add(jta, "Center");
		this.add(jp, "South");
		this.setTitle(ownerId + " 正在和 " + friend + " 聊天");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);

	}
	
	//写一个方法，让它显示消息
	public void showMessage(Message m){
		String info = m.getSender() + " 对 " + m.getGetter() + " 说 " + m.getCon() + "\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb) {
			// 如果用户点击了“发送按钮”
			Message m = new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new Date().toString());

			// 发送给服务器

			try {
				// 发出去要拿到一个输出流
				ObjectOutputStream oos = new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}

/*	public void run() {
		while (true) {

			try {
				// 读取(如果读不到就等待)
				ObjectInputStream ois = new ObjectInputStream(QqClientConServer.s.getInputStream());
				Message m = (Message) ois.readObject();

				// 显示
				String info = m.getSender() + " 对 " + m.getGetter() + " 说 " + m.getCon() + "\r\n";
				this.jta.append(info);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}*/
