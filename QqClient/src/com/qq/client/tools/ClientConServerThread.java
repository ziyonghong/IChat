package com.qq.client.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
/*
 * ����ͻ��˺ͷ������˱���ͨѶ���߳�
 */
import java.net.Socket;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class ClientConServerThread extends Thread {
	private Socket s;
	// ���캯��

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			// ��ͣ�Ķ�ȡ�ӷ�������������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				// System.out.println("��ȡ���ӷ�������������Ϣ" + m.getSender() + " �� " +
				// m.getGetter() + "����" + m.getCon());

				if (m.getMesType().equals(MessageType.message_comm_mes)) {

					// �ѷ�������õ���Ϣ����ʾ������ʾ�Ľ���
					QqChat qqChat = ManageQqChat.getQqChat(m.getGetter() + " " + m.getSender());
					// ��ʾ
					qqChat.showMessage(m);
				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {
					System.out.println("�ͻ��˽��յ�" + m.getMesType());
					String con = m.getCon();
					String friends[] = con.split(" ");
					// ���ظ�������̵��Ǹ���Ҳ���Ǹոյ�setter
					String getter = m.getGetter();

					System.out.println("getter=" + getter);
					// �޸���Ӧ�ĺ����б�
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);

					// �������ߺ���
					if (qqFriendList != null) {
						qqFriendList.updateFriend(m);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
