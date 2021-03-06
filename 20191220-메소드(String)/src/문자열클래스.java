// String: 문자열을 제어 
/*
 * java.lang => import를 생략할 수 있다. 
 * 
 * <문자열을 저장하는 방법> 
 * 	1) String 변수명="Hello Java"; 
 * 	2) String 변수명=new String("Hello Java"); // 같은 문자열 => 따로 저장 
 * 				   ====
 * 				      새로운 메모리에 저장
 * <String에서 제공하는 기능>
 * 	1) equals: 저장된 문자열이 같은지 여부 확인 (true/false)
 * 		ID/PW 비교, 로그인 시 많이 사용. (대소문자 구분) 
 * 		문자열은 (==)을 사용하면 안 됨.
 * 		ex) String s="Hello";
 * 			String s1="Hello";
 * 			String s2=new String("Hello");
 * 			if(s==s1) <== true. 문자열과 문자열. 주소가 같기 때문. 같은 공간에 저장되어 있어서 true나옴. 
 * 			if(s==s2) <== false. 내용은 같아도 'new'로 ★새로운 공간★에 저장됐기 때문.
 * 			if(s.equals(s2)) <==true. 데이터 값이 똑같기 때문. 		
 * 		=> 원형
 * 		   boolean (리턴형이 불린. 결과값이 true/false) equals(String data)  
 * 	2) substring: 문자열을 자를 때 ★빈출★
 * 		형식)
 * 		substring(int start)
 * 		substring(int start, int end)
 * 		ex) String s="0123456789";
 * 			substring(3) => "3456789";
 * 		ex) String s="112분,15세이상관람가";
 * 			substring(0,3) => "112"
 * 						==
 * 						end-1 ==> 012번을 가져옴. <== 'end-1'번 글자까지 가져옴에 유의!!
 * 	3) length: 문자열 갯수 
 * 		String s="Hello 홍길동"; // s의 length는 9임. 영문이던 한글이던 다 char하나를 2byte, 한글자임. 
 * 		ex) 디자인할 때 많이 씀. 예를 들어 타이틀 제목이 20글자 넘으면 자르고 '...' 붙이게.  
 * 	4) trim: 좌우의 공백문자를 제거
 * 		ex) String s="Hello Java"; ==> s.length : 10개, s.trim().length : 10개 (가운데 공백은 제거X)
 * 		ex) String s="   Hello Java    "; (좌공백3,우공백4) ==> s.length : 17개, s.trim().length : 10개  
 * 	5) startsWith, endsWith : 시작하는/끝나는 글자 찾는 것 
 * 		startsWith("자바"): '자바'로 시작하는 것 찾아라
 *  	endsWith("입니다"): '입니다'로 끝나는 것 찾아라  
 * 	6) indexOf, lastIndexOf: 문자가 몇 번째에 있는지 찾음.  
 * 		indexOf: 앞에서부터 문자 위치 찾음.
 * 		lastIndexOf: 뒤에서부터 찾음. 맨뒤가 0번이란건 아님. 그냥 뒤에서부터 찾는단 뜻. 확장자 가져올 때 사용하기도. 
 * 		ex) String s="Hello Java";
 * 					  0123456789
 * 			s.indexOf('a') ==> 7
 * 			s.lastindexOf('a') ==> 9  
 * 	7) split: 문자열 자르기. 지정한 대상을 경계로 잘라준다.  
 * 		ex) String s="이병헌, 하정우, 마동석, 전혜진, 수지";
 * 			String[] names=s.split(","); ==> 배열 names에 {이병헌, 하정우, 마동석, 전혜진, 수지}가 들어감
 * 			정규식 쓰면 split의 더 활용도가 높다!
 * 			====
 * 				정규식은 나중에 다룹니다. 
 * 				ex) IP 주소 
 * 					211.238.142.181
 * 					2111.238.142.180
 * 					211.238.142.1
 * 					211.238.142.23
 * 					211.238.142.0
 * 					211.238.1.1
 * 					IP주소 정규식 : [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3} 
 * 					// 1)0에서 9까지 있는 숫자, 2)1자리에서 3자리까지 있다 3).이 있다(\\.) : 패턴 
 * 				ex) 이메일 주소 정규식 : ^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
 * 	8) toUpperCase, toLowerCase
 * 		toUpperCase: 대문자로 변환
 * 		toLowerCase: 소문자로 변환
 * 		ex) String s="Hello";
 * 			s.toUpperCase() ==> HELLO
 * 			s.toLowerCase() ==> hello 
 * 	9) replace, replaceAll 
 * 		replace: 변경 
 * 		ex) String s="Hello Java";  
 * 			s.replace('a','b'); ==> Hello Jbvb 
 * 		relaceAll도 정규식이 붙는다.
 * 		ex) String s="113분"; 
 * 			s.replaceAll("[^0-9]","") ==> 숫자를 제외하고(숫자가 아닌 것은) 모든애들을 없는걸로 바꿔라(숫자 빼고 다 지워라) ==> 결과: 113
 * 			s.replaceAll("[^0-9$]","") ==> 숫자로 시작해서 숫자로 끝나는 것 빼고는 다 지워라.
 * 			s.replaceAll("[^가-힣]","") ==> 한글 빼고  다 지워라 
 * 			s.replaceAll("[^A-Za-z]","") ==> 영문 빼고  다 지워라
 * 	10) contains(): 포함된 문자를 찾는 경우
 * 	
*/

import java.util.Scanner;
public class 문자열클래스 {
	
	static String[] autoComplete(String data) {
		String[] ss= {
				"자바와 JSP","자바 프로그래밍","스프링 5","Ajax와 JSP","Redux와 ",
				"자바 오라클","MVC 구조","코틀린과 안드로이드","코틀린 프로그램","스프링 데이터",
				"혼자 배우는 자바","지능형웹 프로그램","웹 프로그램","자바 웹프로그램","오라클자바"};
		String[] findData=new String[10];
		int j=0;
		for(int i=0;i<ss.length;i++) {
			//if(ss[i].startsWith(data)) 
			if(ss[i].contains(data)) 
			{
				findData[j]=ss[i];
				j++;
			}
		}
		return findData;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.println("검색어 입력:");
		String data=scan.next();

		String[] fd=autoComplete(data);
		for(int i=0;i<fd.length;i++) {
			if(fd[i]!=null) {
				System.out.println(fd[i]);
			}
		}
	}

}

