package com.qq.client.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.qq.client.model.QqClientUser;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
import com.qq.client.tools.*;;

/*
 * 功能：qq客户端登录界面
 */
public class QqClientLogin extends JFrame implements ActionListener {

	// 定义北部组件
	JLabel jbl1;
	// 定义中部组件
	// 中部有三个JPanel，有一个选项卡窗口

	JTabbedPane jtp;
	JPanel jp2, jp3, jp4;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;

	// 定义南部组件
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;

	public static void main(String[] args) {
		QqClientLogin qqClientLogin = new QqClientLogin();
	}

	public QqClientLogin() {
		// 处理北部
		jbl1 = new JLabel(new ImageIcon("image/aa.png"));

		// 处理中部
		jp2 = new JPanel(new GridLayout(3, 3));// 网格布局

		jp2_jbl1 = new JLabel("QQ号码", JLabel.CENTER);
		jp2_jbl2 = new JLabel("QQ密码", JLabel.CENTER);
		jp2_jbl3 = new JLabel("忘记密码");
		jp2_jbl3.setForeground(Color.BLUE);
		jp2_jbl4 = new JLabel("申请密码保护");
		jp2_jb1 = new JButton(new ImageIcon("image/4.png"));
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("隐身登录");
		jp2_jcb2 = new JCheckBox("记住密码");

		// 把控件按顺序加入的jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		// 创建选项卡窗口
		jtp = new JTabbedPane();
		jtp.add("QQ号码", jp2);
		jp3 = new JPanel();
		jtp.add("手机号码", jp3);
		jp4 = new JPanel();
		jtp.add("电子邮件", jp4);
		// 处理南部
		jp1 = new JPanel();// 默认流式布局
		jp1_jb1 = new JButton(new ImageIcon("image/1.png"));
		// 响应用户点击登录
		jp1_jb1.addActionListener(this);

		jp1_jb2 = new JButton(new ImageIcon("image/2.png"));
		jp1_jb3 = new JButton(new ImageIcon("image/3.png"));
		// 把三个按钮加入到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);

		this.add(jbl1, "North");
		this.add(jp1, "South");
		this.add(jtp, "Center");
		this.setSize(470, 340);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		// 如果用户点击登录
		if (e.getSource() == jp1_jb1) {
			QqClientUser qqClientUser = new QqClientUser();
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			// 验证成功则进入QQ好友界面，同时关闭登录界面
			if (qqClientUser.checkUser(u)) {
				// checkUser（）已启动线程

				try {
					//把创建好友列表的语句提前，否在是不能成功的
					QqFriendList qqFriendList=new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqFriendList);

					// 发送一个要求放回在线好友的请求包，应在验证登录成功后
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread
							.getClientConServerThread(u.getUserId()).getS().getOutputStream());

					// 做一个Message包
					Message m = new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
				   //指明我要的是这个qq号的好友情况，表明是几号
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

//				//登录成功要信息包成功后，创建一个好友列表
//				QqFriendList qqFriendList=new QqFriendList(u.getUserId());
//				ManageQqFriendList.addQqFriendList(u.getUserId(), qqFriendList);
				
				// 关闭登录界面
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "用户名或密码错误");
			}
		}

	}
}
