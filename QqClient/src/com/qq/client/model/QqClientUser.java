package com.qq.client.model;

import java.io.ObjectOutputStream;
import java.net.Socket;
import com.qq.common.*;
/*
 * 后台验证 :（和服务器交换的类）
 */
public class QqClientUser {

	public boolean checkUser(User u) {
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
	 
	

}
