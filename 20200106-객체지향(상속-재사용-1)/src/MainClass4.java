// �߻� Ŭ������ ���� => ��� �޼ҵ尡 ������ ���� 
// ��ӿ� �ΰ����� ����.  
// 1. �޼ҵ常 ���� �� (extends) 2. implements 
interface Ani{
	void working();
	void eating();
}
class Human implements Ani{ // �������̽��� ��� 1. 'implements' ��� 

	@Override
	public void working() {
		// TODO Auto-generated method stub
		System.out.println("�ι߷� �ȴ´�");
	}

	@Override
	public void eating() {
		// TODO Auto-generated method stub
		System.out.println("������ �Դ´�");
	}
	
}
public class MainClass4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
