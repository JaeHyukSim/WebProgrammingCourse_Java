import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.event.*;
public class MainClass6 extends JFrame implements ActionListener,MouseListener{
	// JFrame-Ŭ���� , ActionListener-�������̽� ==> �������̽��� ��� ���ÿ� �� �� �ִ�. 
	// �������̽��� ���� ����  
	JButton b = new JButton("Click");
	public MainClass6() {
		// ��ġ
		add("North", b); // JFrame�� ������ �ִ� �޼ҵ���� ����ϰ� �ϴ� ��...
		setSize(480,  300);
		setVisible(true);
		//b.addActionListener(this);
		b.addMouseListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainClass6();
	}

	@Override
	public void actionPerformed(ActionEvent e) { // ��ư ������ �� ó�����ִ� �Լ� 
		// TODO Auto-generated method stub
		if(e.getSource()==b) //Ŭ���� ��ư�� b��� 
		{  
			System.out.println("B��ư Ŭ��!!");
		}
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
