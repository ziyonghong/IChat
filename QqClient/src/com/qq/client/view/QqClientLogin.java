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
 * ���ܣ�qq�ͻ��˵�¼����
 */
public class QqClientLogin extends JFrame implements ActionListener {

	// ���山�����
	JLabel jbl1;
	// �����в����
	// �в�������JPanel����һ��ѡ�����

	JTabbedPane jtp;
	JPanel jp2, jp3, jp4;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;

	// �����ϲ����
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;

	public static void main(String[] args) {
		QqClientLogin qqClientLogin = new QqClientLogin();
	}

	public QqClientLogin() {
		// ������
		jbl1 = new JLabel(new ImageIcon("image/aa.png"));

		// �����в�
		jp2 = new JPanel(new GridLayout(3, 3));// ���񲼾�

		jp2_jbl1 = new JLabel("QQ����", JLabel.CENTER);
		jp2_jbl2 = new JLabel("QQ����", JLabel.CENTER);
		jp2_jbl3 = new JLabel("��������");
		jp2_jbl3.setForeground(Color.BLUE);
		jp2_jbl4 = new JLabel("�������뱣��");
		jp2_jb1 = new JButton(new ImageIcon("image/4.png"));
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("�����¼");
		jp2_jcb2 = new JCheckBox("��ס����");

		// �ѿؼ���˳������jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		// ����ѡ�����
		jtp = new JTabbedPane();
		jtp.add("QQ����", jp2);
		jp3 = new JPanel();
		jtp.add("�ֻ�����", jp3);
		jp4 = new JPanel();
		jtp.add("�����ʼ�", jp4);
		// �����ϲ�
		jp1 = new JPanel();// Ĭ����ʽ����
		jp1_jb1 = new JButton(new ImageIcon("image/1.png"));
		// ��Ӧ�û������¼
		jp1_jb1.addActionListener(this);

		jp1_jb2 = new JButton(new ImageIcon("image/2.png"));
		jp1_jb3 = new JButton(new ImageIcon("image/3.png"));
		// ��������ť���뵽jp1
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
		// ����û������¼
		if (e.getSource() == jp1_jb1) {
			QqClientUser qqClientUser = new QqClientUser();
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			// ��֤�ɹ������QQ���ѽ��棬ͬʱ�رյ�¼����
			if (qqClientUser.checkUser(u)) {
				// checkUser�����������߳�

				try {
					//�Ѵ��������б�������ǰ�������ǲ��ܳɹ���
					QqFriendList qqFriendList=new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqFriendList);

					// ����һ��Ҫ��Ż����ߺ��ѵ��������Ӧ����֤��¼�ɹ���
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread
							.getClientConServerThread(u.getUserId()).getS().getOutputStream());

					// ��һ��Message��
					Message m = new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
				   //ָ����Ҫ�������qq�ŵĺ�������������Ǽ���
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

//				//��¼�ɹ�Ҫ��Ϣ���ɹ��󣬴���һ�������б�
//				QqFriendList qqFriendList=new QqFriendList(u.getUserId());
//				ManageQqFriendList.addQqFriendList(u.getUserId(), qqFriendList);
				
				// �رյ�¼����
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "�û������������");
			}
		}

	}
}
