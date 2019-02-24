package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;
/*
 * 管理通讯的线程类
 */
public class ManageClientThread {
	public static HashMap hm=new HashMap<String,SerConClientThread>();
	
	//向hm中添加一个客户端通讯线程
	//通过HashMap也可以知道哪些用户在线或不在线
	public static void addClientThread(String uid,SerConClientThread ct){
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid){
		return (SerConClientThread)hm.get(uid);
	}

	//返回当前在线的人的情况
	public static String getAllOnLineUserid(){
		//使用迭代器遍历HashMap
		Iterator it =hm.keySet().iterator();
		String res="";
		while(it.hasNext()){
			res+=it.next().toString()+ " ";
		}
		return res;
	}
}
