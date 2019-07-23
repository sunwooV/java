package D0712;

class Child{
	String name;
	int age;
	String hobby;
	
	Child(){
		
	}
	
	Child(String name, int age){
		this.name = name;
		this.age = age;
	}
	Child(String name, int age, String hobby){
		this.name = name;
		this.age = age;
		this.hobby = hobby;
	}
	
	void goToSchool() {
		System.out.println("학교가다");
	}
	
	void fight(Child c) {
		System.out.println(c.name + "과 싸웠습니다.");
	}
	
}

class Mother{
	String name;
	int age;
	String job;
	private Child[] childs;
	
	Mother(){
		
	}
	Mother(String name, int age){
		this.name = name;
		this.age = age;
	}
	Mother(String name, String job, Child[] childs){
		this.name = name;
		this.job = job;
		this.childs = childs;
	}
	
	void goToSchool() {
		System.out.println("아이들과 학교가다.");
	}
	void callChild() {
		for(int i = 0;i<childs.length;i++) {
			System.out.println((i+1) + ". " + childs[i].name + "이 지금 갑니다.");
		}
	}
	void setChild(Child[] c) {
		this.childs = c;
	}
	
	Child[] getChild() {
		return this.childs;
	}
}

public class Practice1 {

	public static void main(String[] args) {

		Child[] c = new Child[3];
		c[0] = new Child("홍길동", 12);
		c[1] = new Child("홍자동", 10);
		c[2] = new Child("홍가동", 9);

		Mother m = new Mother("엄마", 30);
		
		m.goToSchool(); //아이들과 학교가다.
		m.setChild(c); 
		m.callChild();
		System.out.println(m.getChild());

	}

}
