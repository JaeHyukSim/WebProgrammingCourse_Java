
public class Ch4_��������4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//4-4
		int num=1;
		int sum3=0;
		for(;;) {
			if(num%2==0) {
				sum3-=num;
			}
			else
				sum3+=num;
			if(sum3>100) 
				break;
			num++;
		}
		System.out.println("������ "+sum3+", �������ĸ� "+num+"����");
		System.out.println(sum3);

	}

}
