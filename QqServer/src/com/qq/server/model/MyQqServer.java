package com.qq.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.qq.common.*;

/*
 * qq�����������ڼ������ȴ�ĳ���ͻ���������
 */
public class MyQqServer {

	public MyQqServer() {
		try {
			// ��8888����
			System.out.println("���Ƿ�����");
			ServerSocket ss = new ServerSocket(8888);
			// �������ȴ�����
			while (true) {
				Socket s = ss.accept();
				// ���ܿͻ��˷�������Ϣ
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User) ois.readObject();
				System.out.println("���������յ��û���" + u.getUserId());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if (u.getPasswd().equals("123456")) {
					// ����һ���ɹ���¼����Ϣ��

					m.setMesType("1");
					oos.writeObject(m);

					// ����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ
					SerConClientThread ssct = new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), ssct);
					// ������ÿͻ���ͨ�ŵ��߳�
					ssct.start();

					// ����֪ͨ���������û�
					ssct.notifyOther(u.getUserId());

				} else {
					m.setMesType("2");
					oos.writeObject(m);
					// �ر�����
					s.close();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
