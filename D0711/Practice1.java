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
		System.out.println(name + "�� " + time + "�ÿ� ����մϴ�.");
	}
	
	public void goOffWork(int time) {
		System.out.println(name + "�� " + time + "�ÿ� ����մϴ�.");
	}
	
	public void Info() {
		System.out.println(age + "�� �̰�, ������ " + job + " �Դϴ�.");
	}

}

public class Practice1 {
	
	public static void main(String[] args) {
		Father f = new Father("ȫ�浿", 20, "ȸ���");
		f.goToWork(8); //ȫ�浿�� 8�ÿ� ����մϴ�.
		f.goOffWork(18); //ȫ�浿�� 18�ÿ� ����մϴ�.
		f.Info(); //20�� �̰�, ������ ȸ��� �Դϴ�.
	}

}
