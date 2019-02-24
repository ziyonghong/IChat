package com.qq.client.tools;
/*
 * 管理好友界面类
 */

import java.util.HashMap;

import com.qq.client.view.QqFriendList;

public class ManageQqFriendList {
	private static HashMap hm=new HashMap<String,QqFriendList>();

	public static void addQqFriendList(String qqId,QqFriendList qqFriendList){
		hm.put(qqId, qqFriendList);
	}
	public static QqFriendList getQqFriendList(String qqId){
		return (QqFriendList)hm.get(qqId);
	}
}
