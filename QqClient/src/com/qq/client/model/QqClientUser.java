package com.qq.client.model;

import java.io.ObjectOutputStream;
import java.net.Socket;
import com.qq.common.*;
/*
 * ��̨��֤ :���ͷ������������ࣩ
 */
public class QqClientUser {

	public boolean checkUser(User u) {
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
	 
	

}
