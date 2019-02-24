package com.qq.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.qq.common.*;

/*
 * qq服务器，它在监听，等待某个客户端来连接
 */
public class MyQqServer {

	public MyQqServer() {
		try {
			// 在8888监听
			System.out.println("我是服务器");
			ServerSocket ss = new ServerSocket(8888);
			// 阻塞，等待连接
			while (true) {
				Socket s = ss.accept();
				// 接受客户端发来的信息
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User) ois.readObject();
				System.out.println("服务器接收到用户名" + u.getUserId());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if (u.getPasswd().equals("123456")) {
					// 返回一个成功登录的信息包

					m.setMesType("1");
					oos.writeObject(m);

					// 这里就单开一个线程，让该线程与该客户端保持通讯
					SerConClientThread ssct = new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), ssct);
					// 启动与该客户端通信的线程
					ssct.start();

					// 并且通知其他在线用户
					ssct.notifyOther(u.getUserId());

				} else {
					m.setMesType("2");
					oos.writeObject(m);
					// 关闭连接
					s.close();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
