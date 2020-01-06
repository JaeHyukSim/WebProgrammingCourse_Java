class Board{
	public void write() {
		System.out.println("Board:wirte(): �۾���");
	}
	public void content() {
		System.out.println("Board:content(): ���뺸��");
	}
	public void update() {
		System.out.println("Board:update(): �����ϱ�");
	}
	public void list() {
		System.out.println("Board:list() ��� ���");
	}
	public void find() {
		System.out.println("Board:find(): ã��");
	}
	public void delete() {
		 System.out.println("Board:delete(): �����ϱ�");
	}
}
// <������ ����>
/* 
 * <�������̵�>
 *  ���� ���� : �������̵�
 *  =============== ������ �״�� ������ �´�.  
 *    1) ����� �޴´�.
 *    2) �޼ҵ���� ����
 *    3) �Ű������� ����
 *    4) �������� ����
 *    5) Ȯ���� ����, �߼Ҵ� �Ұ����ϴ�. ��
 *    	 (����������) 
 *    	 private < default < protected < public 
 *    	 ex) class A
 *    		 {
 *    			void display(){}
 *    		 }
 *    		 class B extends A
 *   		 {
 *   			void display(){} (O)
 *   			protected void display(){} (O)
 *   			public void display(){} (O)
 *   			private void display(){} (X) //5) ȹ���� ����, ��Ҵ� �Ұ����ϱ� ����! 
 *   		 }
 *    
*/
class GalleryBoard extends Board{
	
	@Override
	public void write() {
		System.out.println("GalleryBoard:wirte(): �۾���+�̹��� ���ε�");
	}
	
	@Override
	public void content() {
		System.out.println("GalleryBoard:wirte(): ���뺸��  + ���� �ٿ�ε� ");
	}
	
	
}
class DataBoard extends Board{
	
	@Override
	public void write() {
		System.out.println("GalleryBoard:wirte(): �۾���+�̹��� ���ε�");
	}
	
	public void write(int a) 
	{
		System.out.println("DataBoard:write(int a)"); 
		// �׳� ���ο� Ŭ������ �߰��� ��. ��� AŬ������ ���� �����ϱ� AŬ���� �� ��X
		// �Ű������� �ٸ��� �ٸ� Ŭ������!!
	}
	
	@Override
	public void content() {
		System.out.println("GalleryBoard:wirte(): ���뺸�� + ���� �ٿ�ε�");
	}
	
}
public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * GalleryBoard gb = new GalleryBoard(); gb.list(); gb.write(); gb.content();
		 * gb.update(); gb.delete(); gb.find();
		 * 
		 * DataBoard db = new DataBoard(); db.list(); db.write(); db.content();
		 * db.update(); db.delete(); db.find();
		 */
		Board gb = new GalleryBoard(); 
		// gb�� ������ �ִ� �޼ҵ常 (6��) ���پ� �� �ִ�. 
		// ������ GalleryBoard�� �߰��� ������ ���پ� �� ����. 
		//�տ��� ����Ŭ����, �ڿ��� ���� Ŭ����..
		/*
		 * ������
		 * ====
		 * ����Ŭ���� ������ = ����Ŭ���� ������ (�߰��� ���� ��츸 ����) ��
		*/
		gb.list();
		gb.write();
		gb.content();
		gb.update();
		gb.delete();
		gb.find();
		
		gb = new DataBoard();
		gb.list();
		gb.write();
		gb.content();
		gb.update();
		gb.delete();
		gb.find();
		
		/*
		 *  ���� Ŭ������ ����Ŭ������ �����ڸ� �����ϰ� �Ǹ�
		 *  ex) Board gb = new DataBoard(); ==> ����� �޼ҵ常 ȣ���� ����. 
		 *  	int a 			int a,b 
		 *  	=========================== ����� �޴� ��쿡�� �̷��� �� �� ����. 
		 *   	=> ���� Ŭ������ ���� Ŭ������ �߰��� �޼ҵ峪 �������� ������ �� ���� �ڡڡ�
		 *   	=> ���� : Ŭ���� Ÿ���� Ȯ��!
		 *    	=> �޼ҵ� : ������ Ȯ��! 
		 *    	
		*/
		
	}

}
