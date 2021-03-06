// ~VO (데이터형 클래스) => 캡슈로하
/*
 *  변수 : 읽기, 쓰기 => 메소드화 처리 
*/
/*
백두산 (2019)
평점6.7/10
드라마/액션
2019.12.19 개봉
128분, 12세이상관람가
(감독) 이해준, 김병서
(주연) 이병헌, 하정우, 마동석, 전혜진, 수지
예매 1위누적관객6,789,642명
예매하기갤러리독점
대한민국 관측 역사상 최대 규모의 백두산 폭발 발생.
갑작스러운 재난에 한반도는 순식간에 아비규환이 되고,
남과 북 모두를 집어삼킬 추가 폭발이 예측된다.
*/
/*
 * 변수 초기화
 *  1) 생성자
 *  2) 초기화 블록
 *  3) setter
 *  
 *  ex1) 잘못된 예시
 *  class A
 *  {
 * 		int a;
 * 		for(int i=0; i<10; i++)   //Error. 클래스 영역에서 선언이 아니라 구현(for문)을 했음. 불가능. 
 * 		{
 * 			a=i; 
 * 		} 
 *  }
 *  
 *  ex2) 올바른 예시
 *  class A
 *  {
 * 		int 10;
 * 		A(){
 * 			for(int i=0; i<10; i++)   //Correct. 생성자 안에다가는 구현 가능. 초기화 블록을 사용했음. 
 * 			{
 * 			a=i; 
 * 			} 
 * 		}
 *  }
 *  
 *  ex3) 초기화 블록 예시 - static 블록 
 *  class A
 *  {
 *  	static int a=10;
 *  	static int b=20;
 *  		static 
 *  		{
 *  			for(int i=0; i<10;i++){
 *  				a = i;
 *  			}
 *  			b=i;
 *  		}
 *  }
*/
public class MovieVO {
	private String title;
	private double score;
	private String genre;
	private String regdate;
	private int time;
	private String grade;
	private String director;
	private String actor;
	private int showUser;
	private String story;
	
	// 클래스형 데이터 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public int getShowUser() {
		return showUser;
	}
	public void setShowUser(int showUser) {
		this.showUser = showUser;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
	
}

















