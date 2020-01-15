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
	GameRoom gr=new GameRoom();
	MakeRoom mr=new MakeRoom(); 
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
		add("GAME",gr);
		add("WR",wr);
		//add("Center",login);
		
		setBounds(448,156,1024,768); // ��� ��ǥ ����
		setVisible(true); // ������ ������
		
		setResizable(false); // â ũ�� ���� �Ұ����ϰ� (�ִ�ȭ ��ư�� ��Ȱ��ȭ��) 
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // X��ư �� ������ 		
		login.b1.addActionListener(this);
		wr.tf.addActionListener(this);
		wr.b6.addActionListener(this); // ���� ������ ��ư 
		wr.b1.addActionListener(this); // ���� �游��� ��ư 
		
		mr.b1.addActionListener(this); // �游��� ��ư => JDialog�� '�游���' ��ư  
		mr.b2.addActionListener(this); //  �游��� ��ư => JDialog�� '��� ��ư  
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
		// ���� ������ ��ư Ŭ�� �� ==> ������ Socket�� ����, Vector�� �����.  
		else if(e.getSource()==wr.b6) 
		{
			try
			{
				out.write((Function.EXIT+"|\n").getBytes());
				/*
				 * ������ => ��û 
				 *         ===
				 *         ó�� ==> ������ ó������. 
				 *         ������ ==> Ŭ���̾�Ʈ�� ��� ���. 
				*/
			}catch(Exception ex) {}
		}
		else if(e.getSource()==wr.b1) // �游��� ��ư Ŭ�� ��
		{
			
			mr.tf.setText(""); 
			// �Ź� â�� ���� �����ϴ°� �ƴϹǷ�, ���� ���� ����������, ��ư ���������� �� �������� 
			// �� ���·� ����� ���Ͽ�
			mr.rb1.setSelected(true);
			mr.box.setSelectedIndex(0);
			mr.la4.setVisible(false);
			mr.pf.setVisible(false);
			mr.pf.setText("");
			mr.tf.requestFocus();
			mr.setVisible(true);
		}
		else if(e.getSource()==mr.b1)
		{
			// 1. �� �̸� 
			String rn=mr.tf.getText();
			if(rn.length()<1)
			{
				JOptionPane.showMessageDialog(this, "�� �̸��� �Է��ϼ���");
				mr.tf.requestFocus();
				return;
			}
			for(int i=0;i<wr.model1.getRowCount();i++)
			{
				String roomName=wr.model1.getValueAt(i, 0).toString();
				if(rn.equals(roomName))
				{
					JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���Դϴ�.\n�ٽ� �Է��ϼ���.");
					mr.tf.setText("");
					mr.tf.requestFocus();
					return;
				}
			}
			
			// ���� ����� 
			String rs=""; // ���� (room status)
			String rp=""; // ��й�ȣ (room pwd) 
			if(mr.rb1.isSelected())
			{
				rs="����";
				rp=" "; //�ݵ�� ���� �ϳ� �����!�� //""�ع����� null�� �ν��ؼ�... ���� �� �� ���� ���� ���� 
			}
			else
			{
				rs="�����";
				rp=String.valueOf(mr.pf.getPassword());
			}
			
			// �ο�üũ
			int inwon=mr.box.getSelectedIndex()+2;
			// mr���� box�� ���� ��, �ο����� 2����� �����ϰ� �������� '+2' �Ѱ���. 
			// (index�� �׻� 0���� �����ϴϱ�, index=0�϶� �ο���2�� �̷��� �Ǵϱ�...) 
			
			// ������ ����
			try
			{
				out.write((Function.MAKEROOM+"|"+rn+"|"+rs+"|"+rp+"|"+inwon+"\n").getBytes());
			}catch(Exception ex) {}
			mr.setVisible(false);
			
		}
		else if(e.getSource()==mr.b2)
		{
			mr.setVisible(false);
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
					case Function.EXIT: // ����ó�� - to ���� �ִ� ���
					{
						String id=st.nextToken();
						for(int i=0;i<wr.model2.getRowCount();i++) // ���̺� ����� ��(��)�� ������ ����, �� �� �� �� �˻��غ� 
						{
							String mid=wr.model2.getValueAt(i, 0).toString(); 
							// getValueAt : ���� �о��
							// getValueAt(i,0) : id�� ���� ù��°(����ȣ 0)�� �����ϱ�
							// toString : getValueAt�� �������� Object���� �긦 String���� ������������
							if(mid.equals(id)) 
							{
								wr.model2.removeRow(i);
								break;
							}
						}
						break;
					}
					case Function.MYEXIT: // ����ó�� - to ������ ��� 
					{
						dispose(); // �޸� ȸ�� 
						System.exit(0); // ���α׷� ���� 
					}
					case Function.MAKEROOM: 
					{
						String[] data= {
								st.nextToken(), // ���̸�
								st.nextToken(), // ����� (����/�����)
								st.nextToken() // �ο� 1/6 
								};
						wr.model1.addRow(data); // �������̴ϱ� add �� ������ �� �� �� �þ 
						break;
					}
					
				}
			}
		}catch(Exception ex) {}
	}

	
}
