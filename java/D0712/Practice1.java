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
		System.out.println("�б�����");
	}
	
	void fight(Child c) {
		System.out.println(c.name + "�� �ο����ϴ�.");
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
		System.out.println("���̵�� �б�����.");
	}
	void callChild() {
		for(int i = 0;i<childs.length;i++) {
			System.out.println((i+1) + ". " + childs[i].name + "�� ���� ���ϴ�.");
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
		c[0] = new Child("ȫ�浿", 12);
		c[1] = new Child("ȫ�ڵ�", 10);
		c[2] = new Child("ȫ����", 9);

		Mother m = new Mother("����", 30);
		
		m.goToSchool(); //���̵�� �б�����.
		m.setChild(c); 
		m.callChild();
		System.out.println(m.getChild());

	}

}
