package com.sist.server;
import java.io.*;
import java.net.*;
import java.util.*;
import com.sist.common.*;

public class Server implements Runnable{
	
	// 1. ���� ���� 
	// ���� => ����ó�� => ServerSocket  
	// �� Ŭ���̾�Ʈ���� ��Ŵ�� (Thread) => Socket <=> Socket 
	private ServerSocket ss;
	private final int PORT=3355;
	
	// ������ ���� ���� 
	private Vector<Client> waitVc=new Vector<Client>();
	// �� ���� ���� ���� 
	private Vector<Room> roomVc=new Vector<Room>();
	
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
				Client client = new Client(s);
				client.start();
			}
		}catch(Exception ex) {}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		new Thread(server).start();

	}
	// ����� ����ϴ� �κ� (�� Ŭ���̾�Ʈ���� ���� �۾��� �Ѵ�) 
	class Client extends Thread 
	{
		String id,name,sex,pos;
		int avatar;
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
		
		//Ŭ���̾�Ʈ�� ���
		public void run()
		{
			// 100|hong|ȫ�浿|����\n �� �Ѿ��
			try
			{
				while(true)
				{
					String msg=in.readLine(); // Ŭ���̾�Ʈ�� ������ �� (��û)
					System.out.println("Client=>"+msg); // ��Ŷ ������ �����. 
					StringTokenizer st=new StringTokenizer(msg,"|"); 
					int protocol = Integer.parseInt(st.nextToken());
					switch(protocol)
					{
						case Function.LOGIN: //�α��εƴٸ� ���� String id,name,sex,pos;�� ���� �����ؾ� 
						{
							id=st.nextToken();
							name=st.nextToken();
							sex=st.nextToken();
							avatar=Integer.parseInt(st.nextToken()); 
							// nextToken�� �� �ᵵ �Ǵµ�, �ʰ��ϸ� ������ ��. Array��ȣ�� ������ ����� �� ��.(arrayindexoutofboundsexception �Ͼ)
							// 4���� ���´µ� �������� �ʴ� 5��°���� ȣ���ϸ� ������. 
							pos="����";
							// �� �� ���� ������ ��� ������� ������ 
							
							messageAll(Function.LOGIN+"|"+id+"|"+name+"|"+sex+"|"+pos);
							waitVc.add(this);
							messageTo(Function.MYLOG+"|"+id); // MYLOG ==> â�� �ٲ�. MYLOG���� id�� ���´�.  
							for(Client user:waitVc)
							{
								messageTo(Function.LOGIN+"|"+user.id+"|"+user.name+"|"+user.sex+"|"+user.pos); //user:���沨. 
							}
							
							// �̹� �����Ǿ� �ִ� �� ���� ���� 
							for(Room room:roomVc)
							{
								messageTo(Function.MAKEROOM+"|" //messageTo : �α����� ������Ը� ���� 
										+room.roomName+"|"
										+room.roomState+"|"
										+room.current+"/"+room.maxcount);
							}
							
							break;
						}
						case Function.WAITCHAT:
						{
							messageAll(Function.WAITCHAT+"|["+name+"]"+st.nextToken()); 
							//name :���� ��� �̸�. this.name���� this ������ ��. �ƹ��͵� �� ���� name�̷��Ÿ� ���������, user.xxx�� ���沨. 
							break;
						}
						case Function.EXIT:
						{
							String mid=id;
							// this.id���� this ������ ��. out.write�� ���� �� ������ this�� ���� ����.
							// id�� ���� �ͼ� ������ �㿡, ������ ����� �����ִ� ������� �갡 �����ܰ� �˷���.
							for(int i=0;i<waitVc.size();i++) // index��ȣ�� �ʿ� ������ for-each ��������. index ��ȣ �ʿ��ϸ� �׳� �Ϲ����� for������ �ۼ��� ��. 
							{
								Client user=waitVc.get(i);
								if(mid.equals(user.id))
								{
									// ������ ����
									messageTo(Function.MYEXIT+"|"); 
									// �ڿ� ���� ��� "|" ����� - �ֳĸ� ���й��� "|"�� ���� ������ ���� ���� �� �� ����...
									// ���⼭ msg ����� �ִµ�, �ؿ� ���� messageTo ���� msg �ڿ� \n ����
									// �׷��Ƿ� �� �κп����� \n �� �൵ ��. ���߿� messageTo �޼ҵ忡�� \n ��������. 
									waitVc.remove(i);
									// �ݱ� (��� ����)
									in.close();
									out.close();
									break;
								}
							}	
							// ��ü �޽��� => ������ ������ ���̺��� ���� 
							messageAll(Function.EXIT+"|"+mid);
							break;
						}
						case Function.MAKEROOM:
						{
							Room room = new Room(st.nextToken(),st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken()));
							// MainForm���� �������� �Ʒ��� ���� �������� ���¾��� . ������ ���Ŀ� �� ���缭 room�� �־��ش�. 
							// out.write((Function.MAKEROOM+"|"+rn+"|"+rs+"|"+rp+"|"+inwon+"\n").getBytes());
							room.userVc.add(this);
							
							roomVc.add(room);
							pos=room.roomName; //pos�� �� �̸����� �ٲ��.
							
							messageAll(Function.MAKEROOM+"|"
									+room.roomName+"|"
									+room.roomState+"|"
									+room.current+"/"+room.maxcount); // �ο��� '1/6' �̷��� �����Բ� 
							break;
						}
					}
					
				}
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





