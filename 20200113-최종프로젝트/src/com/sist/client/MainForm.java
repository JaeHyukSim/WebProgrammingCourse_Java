package com.sist.client;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.smartcardio.Card;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
// ���� ����
import java.util.*;
import java.net.*;
import java.io.*;

import com.sist.common.Function;
//��Ű���� Ʋ���� ������ ����Ʈ���� 
import com.sist.dao.MemberDAO;
public class MainForm extends JFrame implements ActionListener, Runnable{ // <== extends Activity�� �ٲٸ� �ȵ���̵���! ���� 
	// �̹� JFrame ��� �޾����Ƿ� ��� �Ұ� ==> ������ ��� �Ұ����ϹǷ� implements Runnable �Ѵ�! 
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	CardLayout card=new CardLayout(); // ������ â�� �״�� ���ΰ� tab �����ϱ� ���ؼ�. 
	
	// ���� ����� ���õ� ���̺귯��
	Socket s; // ���� ����
	OutputStream out; //������ �����͸� ���� (��û)
	BufferedReader in; //�������� ������ �����͸� �޴´�
	/*
	 * 1) ������ ���� ������ ������ => �̺�Ʈ �߻� 
	 *  - ex) ��ư Ŭ�� �� ==> �̷� �̺�Ʈ�� �߻��� ������ ������ �����ָ� ��. 
	 *        ==> �׷��� �������� �޾Ƽ� ���õ� ��������� �޼����� ������. 
	 * 2) �������� ������ ������ => Thread => ��� (Function) 
	*/
	MainForm(){
		//���� �������� â�� ���� ��� ==> �α���â>WR(WatingRoom)â 
		setLayout(card);
		add("LOGIN",login);
		add("WR",wr);
		//add("Center",login);
		
		setSize(1024,768);
		setVisible(true); // ������ ������
		login.b1.addActionListener(this);
		wr.tf.addActionListener(this);
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
		}
		catch(Exception e) {}
		new MainForm();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// �α��ι�ư ������ â ��ȯ 
		// ��й�ȣ�� 
		if(e.getSource()==login.b1) {
			String id=login.tf.getText();
			if(id.length()<1)
			{
				JOptionPane.showMessageDialog(this,"ID�� �Է��ϼ���");
				login.tf.requestFocus();
				return;
			}
			String pwd=String.copyValueOf(login.pf.getPassword()); 
			// ��й�ȣ�� �ݵ�� , getTExt���ƴ϶� getPassword�� ���� �;�!!
			if(pwd.length()<1)
			{
				JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
				login.pf.requestFocus(); //������ ��Ŀ�� �� 
				return;
			}
			
			// ó�� 
			MemberDAO dao=new MemberDAO();
			String result=dao.isLogin(id,pwd);
			if(result.equals("NOID")) 
			{
				JOptionPane.showMessageDialog(this, "ID�� �������� �ʽ��ϴ�.");
				login.tf.setText("");
				login.pf.setText("");
				login.tf.requestFocus();
			}
			else if(result.equals("NOPWD")) 
			{
				login.pf.setText("");
				login.pf.requestFocus();
				JOptionPane.showMessageDialog(this, "��й�ȣ�� Ʋ���ϴ�");
			}
			else 
			{
				connection(result);
			}
		}
		else if(e.getSource()==wr.tf) //���� ä�� 
		{
			// �Էµ� ���ڿ� �б�
			String msg=wr.tf.getText();
			if(msg.length()<1) //�Է� �� �� ��� 
			{
				wr.tf.requestFocus();
				return;
			}
			// if���� �� �ɷ��� ==> ���������� �޽��� �Է��� ���  ==> ������ ����
			try
			{
				out.write((Function.WAITCHAT+"|"+msg+"\n").getBytes());
			}catch(Exception ex) {}
			
			wr.tf.setText("");
		}

	}
	
	// ������ ���� - �α��� ��ư ������ �� ȣ���Ű�� �� 
	public void connection(String userData) 
	{
		try
		{
			s=new Socket("localhost",3355); // ���� ���� : '��ȭ�ɱ�' ���� ����! ���߿� "localhost"�� "���� ���� IP"�� �ٲٸ� ��
			// �۽�(OutputStream) / ����(BufferedReader)  - ����) �۽��� �׻� ������, ������ �׻� �����尡. 
			// �۽�
			out=s.getOutputStream();
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			// �α��� ������ ������ 
			// 100|hong|ȫ�浿|����|����\n   �̷��� �Ѿ
			out.write((Function.LOGIN+"|"+userData+"\n").getBytes()); 
			// 1����Ʈ �����ε� �ѱ�(2byte)�� 1byte�� ����  (���� ��Ʈ�p ����� ������ 1����Ʈ��)			
		}catch(Exception ex) {}
		// �����κ��� �����͸� �б� ����
		new Thread(this).start();
	}
	
	// �����κ��� �����͸� �����ϴ� ���
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// �������� �� ��� ó���� ���ΰ� (������)
		try
		{
			while(true)
			{
				String msg=in.readLine(); // msg�� �������� 100|hong|ȫ�浿|����|����\n �䷸�� ���ƿ� 
				StringTokenizer st=new StringTokenizer(msg,"|"); // StringTokenizer�� | �������� ���� �ڸ���. 
				int protocol=Integer.parseInt(st.nextToken());
				switch(protocol)
				{
					case Function.LOGIN:
					{
						String[] data= {
							st.nextToken(), //id
							st.nextToken(), //�̸�
							st.nextToken(), //����
							st.nextToken() //��ġ
						};
						wr.model2.addRow(data); //model2�� �ι�° ���̺�(��������Ʈ)��!
						break;
					} 
					case Function.MYLOG: //�������� MYLOG(â �ٲ��)�� ���ϸ� �������� 
					{
						String id=st.nextToken();
						setTitle(id);
						card.show(getContentPane(),"WR");
						break;
					}
					case Function.WAITCHAT:
					{
						wr.tp.append(st.nextToken()+"\n");
						break;
					}
				}
			}
		}catch(Exception ex) {}
	}

	
}
