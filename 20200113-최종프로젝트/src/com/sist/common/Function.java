package com.sist.common;
/*
 * <��Ʈ��ũ> (CS : Client + Server)
 * 1. Server
 *  - Ŭ���̾�Ʈ�� ��û�� �޾Ƽ� ���� (���) ��
 *  - ex) Fuction.LOGIN ==> id|pwd|sex|loc ==> waitVc�� ����
 * 
 * 2. Client
 *  - ������ ��û�� �ؼ�, �����κ��� ���� �޾Ƽ� Ŭ���̾�Ʈ ���α׷��� ��� ��  
 *  - ex) Fuction.LOGIN ==> id|pwd|sex|loc ==> table�� ��� 
 * 
 * 3. ���ǻ���
 *  1) Ŭ���̾�Ʈ���� ����� ���� ��������� �Ѵ� ==> ������ �̿�. ������� ��Ÿ� ���. 
 *  2) Ŭ���̾�Ʈ ��ü���� ó������ �ʰ�, ��� ���ð�, �׻� ������ ���� ���;� �Ѵ�. 
 *   - ������ ����� �����µ�, ���Ƿ� ����� ���� �� �����Ƿ� ��ɸ��� ��ȣ�� ���س��� �� ����� �����Ѵ�.  
 *  3) ��Ʈ���� �̿��� ���α׷� 
 *     ====
 *     ���� => ����Ʈ��Ʈ�� ==> OutputStream
 *     ���� => ���ڽ�Ʈ�� ==> BufferedReader
*/
public class Function {
	// ����/������ ����� �����ִ� ��� �� �� ���� ������ �����ض�. 
	// 1) MY�� ���� '��ȯ'. ȥ�ڸ� �ٲ�� ����� �ִ� ��. 
	// 2) �������� ���� �ִ� ����� ó��.

	public static final int LOGIN=100; // ��� ��� ��� 
	public static final int MYLOG=110; // �α��� => ���Ƿ� ���� 
	
	public static final int MAKEROOM=200; // �������� ���
	public static final int ROOMIN=210; // �濡 ���� 
	public static final int ROOMOUT=220;
	public static final int ROOMADD=230;
	public static final int MYROOMOUT=240;
	public static final int WAITUPDATE=250; // ���ǿ��� ���ӹ� ����Ʈ �� ��������Ʈ ������Ʈ. 
	public static final int POSCHANGE=260; // �� ����� �濡 ���� ������ �� �̸����� �ٲ�ġ�� ����. 
	public static final int WAITCHAT=270;
	
	public static final int EXIT=900; // ������ �ִ� ����鿡�� ���� �ָ� �˷��� 
	public static final int MYEXIT=910; // ���� ���� 
	
	
}
