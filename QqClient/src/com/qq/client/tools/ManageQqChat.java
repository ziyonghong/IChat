package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqChat;

/*
 * ����һ�������û�����������
 */
public class ManageQqChat {
   private static HashMap hm=new HashMap<String,QqChat>();
   
   //����
   public static void addQqChat(String loginIdAndFriendId,QqChat qqChat){
	   hm.put(loginIdAndFriendId,qqChat);
   }
   //ȡ��
   public static QqChat getQqChat(String loginIdAndFriendId){
	   return (QqChat)hm.get(loginIdAndFriendId);
   }
}
