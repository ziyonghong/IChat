package com.qq.client.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.*;

/*
 * �ͻ������ӷ������ĺ�̨
 */
public class QqClientConServer {

	/*
	 * public QqClientConServer(){ try{ Socket s=new Socket("localhost",8888);
	 * }catch(Exception e){ e.printStackTrace(); }finally{
	 * 
	 * } }
	 */
	public Socket s;
	// ���͵�һ������

	public boolean sendLoginInfoToServer(Object o) {
		boolean b = false;
		try {
			System.out.println("aa");
			s = new Socket("localhost", 8888);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			Message ms = (Message) ois.readObject();
		
			//���������֤�û���¼�ĵط�
			if (ms.getMesType().equals("1")) {
				//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(s);
				//������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(((User)o).getUserId(), ccst);
			
				b = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return b;
	}
}
