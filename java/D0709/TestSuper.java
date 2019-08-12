package D0709;

class Employee{
	
	 Employee(){ //생략시 자동으로 기본형이 생성된다. Employee(){} ,java.lang.Object 생성
	 System.out.println("나는 부모 constructor");
	 }
	 
	
	Employee(int a){ //생략시 자동으로 기본형이 생성된다. Employee(){} ,java.lang.Object 생성
		System.out.println(a+ "나는 부모 constructor");
	}
}

class Manager extends Employee{
	Manager(){ //생성자
		//super(1); //Employee(int a) 호출
		super(); //default 생성자 호출  Employee()
		System.out.println("나는 자식 constructor");
	}
}

public class TestSuper {

	public static void main(String[] args) {
		Manager m = new Manager(); //constructor 라인 호출
	}

}
