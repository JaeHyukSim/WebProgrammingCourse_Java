package com.sist.server;
import java.io.*;
import java.net.*;
import java.util.*;
import com.sist.common.*;

public class Server implements Runnable{
	// ���� => ����ó�� => ServerSocket  
	// �� Ŭ���̾�Ʈ���� ��Ŵ�� (Thread) => Socket <=> Socket 
	// 1. ���� ���� 
	private ServerSocket ss;
	private final int PORT=8888;
	// ������ ���� ���� 
	private Vector<Client> waitVc=new Vector<Server.Client>();
	public Server() 
	{
		try 
		{
			ss=new ServerSocket(PORT); //bind, listen 
			System.out.println("Server Start...");
		}catch(Exception ex) {}
	}
	// ���� �� ó�� 
	public void run() 
	{
		try 
		{
			while(true) 
			{
				//������ �ߴٸ� => Ŭ���̾�Ʈ�� ������ �����ؾ� => IP, PORT (Socket)
				Socket s=ss.accept(); // �� ���ϰ� Ŭ���̾�Ʈ�� �Ѱ��� ������ �������� ����� �����ϴ�.
				// (Ŭ���̾�Ʈ�� ����(ip,port))�� Thread�� ���� ==> ���ڸ��� ����� �� �� �ִ�. 
			}
		}catch(Exception ex) {}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	// ����� ����ϴ� �κ� (�� Ŭ���̾�Ʈ���� ���� �۾��� �Ѵ�) 
	class Client extends Thread 
	{
		String id,name,sex,pos;
		// pos : �� ��ġ (��� �濡 ���ִ��� Ȯ��. ����Ʈ�� ����) 
		// ����� �ϱ� ���� socket�ʿ�
		Socket s; // ������. ����Ʈ����� ���� ��� ���. �ְ�ް� �� �� ���� ��. 
		// ������
		OutputStream out; // 1����Ʈ ¥��. ���� ���� ����Ʈ ������ ����. ���� ���� �ѱ۷� �ٽ� �����..,
		// �ޱ� 
		BufferedReader in;
		
		public Client (Socket s) {
			try
			{
				this.s=s;
				out=s.getOutputStream(); //Ŭ���̾�Ʈ�� ���� ��ġ =>
				in=new BufferedReader(new InputStreamReader(s.getInputStream()));
				// InputStreamReader(���ͽ�Ʈ��) => 1byte�� �޾Ƽ� 2byte�� ��ȯ  
			}catch(Exception ex) {}
		}
		// �ݺ��� �����ϱ� ���ؼ� �޼ҵ� ���
		// �������� ���� - �Ʒ��� �� �� �ϳ����� �����ؾ�.  
		//1) ���������� ���� 
		public synchronized void messageTo(String msg)
		{	//synchronized : �����鼭 �����Ű�� �� �����ִ� ��. ==> ����ȭ ���α׷�.
				
			try {
				out.write((msg+"\n").getBytes());
				// readLine() => ������ ���� =(\n)
				// ���۴��� : ��Ŷ ==> �ݵ�� \ ���� �� (������ ������ \n��) 
			}catch(Exception ex) {}
		}
		//2) ��ü������ ���� 
		public synchronized void messageAll(String msg)
		{
			try 
			{
				for(Client user:waitVc) 
				{
					user.messageTo(msg);
				}
			}catch(Exception ex) {}
		}
	}
}





