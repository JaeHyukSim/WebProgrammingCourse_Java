// �� ������ ��� �����ϴ� Ŭ���� 
package com.sist.server;
import java.util.*;

public class Room {
	String roomName,roomState,roomPwd;
	int current,maxcount; // �����ο���, �ִ��ο��� 
	
	Vector<Server.Client> userVc=new Vector<Server.Client>(); // �� �ȿ� �� ����鸸 �����ϱ� ���� Vector�� ������� 
	// Vector<Client> userVc=new Vector<Client>(); ��� ���� ����. 
	// Client Ŭ������ Room Ŭ������ �ٸ� Ŭ���� �ȿ� �����Ƿ�, Server.Client��� ��ġ �˷������. 
	// Server Ŭ�������� �ڵ��� ���� Client Ŭ������ Server Ŭ���� �ȿ� �����Ƿ� Vector<Client> �̷��� �ڵ� �����ߴ� ��.
	// Vector�� index ��ȣ�� 0�� �ְ� ������ ��. 
	// 0�̴� �ְ� ������, index ��ȣ�� �ڵ� �����Ǿ� 1���̴� �ְ� 0���� �ȴ� ==> �갡 ���ο� ������ �� 
	
	// [������] 
	//  - ��Ŭ�� > Generate Constructor using Fields >> current,userVc ���� �����. 
	//  - current, userVc�� �������� ó���ؾ� �ϴϱ� ���⼭ ������ �� ������� 
	public Room(String roomName, String roomState, String roomPwd, int maxcount) { //�갡 ȣ��Ǹ� ���� ���������. //�긦 ȣ���ϴ� �ְ� ������ ��
		
		this.roomName = roomName;
		this.roomState = roomState;
		this.roomPwd = roomPwd;
		this.maxcount = maxcount;
		current=1; // ���� ����������ϱ� ���� �ο��� 1  
	}
	
	
}
