package D0711;

class Father{
	String name;
	int age;
	String job;
	
	public Father(String name, int age, String job){
		this.name = name;
		this.age = age;
		this.job = job;
	}
	
	public void goToWork(int time) {
		System.out.println(name + "이 " + time + "시에 출근합니다.");
	}
	
	public void goOffWork(int time) {
		System.out.println(name + "이 " + time + "시에 퇴근합니다.");
	}
	
	public void Info() {
		System.out.println(age + "살 이고, 직업은 " + job + " 입니다.");
	}

}

public class Practice1 {
	
	public static void main(String[] args) {
		Father f = new Father("홍길동", 20, "회사원");
		f.goToWork(8); //홍길동이 8시에 출근합니다.
		f.goOffWork(18); //홍길동이 18시에 퇴근합니다.
		f.Info(); //20살 이고, 직업은 회사원 입니다.
	}

}
