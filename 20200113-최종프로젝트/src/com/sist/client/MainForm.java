package com.sist.client;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.smartcardio.Card;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

//��Ű���� Ʋ���� ������ ����Ʈ���� 
import com.sist.dao.MemberDAO;
public class MainForm extends JFrame implements ActionListener { // <== extends Activity�� �ٲٸ� �ȵ���̵���! ���� 
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	CardLayout card=new CardLayout(); // ������ â�� �״�� ���ΰ� tab �����ϱ� ���ؼ�. 
	MainForm(){
		//���� �������� â�� ���� ��� ==> �α���â>WR(WatingRoom)â 
		setLayout(card);
		add("LOGIN",login);
		add("WR",wr);
		//add("Center",login);
		
		setSize(1024,768);
		setVisible(true); // ������ ������
		login.b1.addActionListener(this);
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
		}
		catch(Exception e) {}
		MainForm mf=new MainForm();
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
				JOptionPane.showMessageDialog(this, id+"�� �α��εǾ����ϴ�.");
				card.show(getContentPane(),"WR");
			}
		}

	}

	
}
