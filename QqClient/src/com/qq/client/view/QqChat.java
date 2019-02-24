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
 * ���������Ľ���
 * ��Ϊ�ͻ���Ҫ���ڶ�ȡ��״̬�����Ҫ��������һ���߳�
 */
public class QqChat extends JFrame implements ActionListener{
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;// �ͻ��˵ı��
	String friendId;// ����������ĺ��ѵı��

	public static void main(String[] args) {
		// ����
		// QqChat qqChat=new QqChat("1");
	}

	public QqChat(String ownerId, String friend) {
		this.ownerId = ownerId;
		this.friendId = friend;
		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("����");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);

		this.add(jta, "Center");
		this.add(jp, "South");
		this.setTitle(ownerId + " ���ں� " + friend + " ����");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);

	}
	
	//дһ��������������ʾ��Ϣ
	public void showMessage(Message m){
		String info = m.getSender() + " �� " + m.getGetter() + " ˵ " + m.getCon() + "\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb) {
			// ����û�����ˡ����Ͱ�ť��
			Message m = new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new Date().toString());

			// ���͸�������

			try {
				// ����ȥҪ�õ�һ�������
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
				// ��ȡ(����������͵ȴ�)
				ObjectInputStream ois = new ObjectInputStream(QqClientConServer.s.getInputStream());
				Message m = (Message) ois.readObject();

				// ��ʾ
				String info = m.getSender() + " �� " + m.getGetter() + " ˵ " + m.getCon() + "\r\n";
				this.jta.append(info);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}*/
