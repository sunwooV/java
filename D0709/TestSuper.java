package D0709;

class Employee{
	
	 Employee(){ //������ �ڵ����� �⺻���� �����ȴ�. Employee(){} ,java.lang.Object ����
	 System.out.println("���� �θ� constructor");
	 }
	 
	
	Employee(int a){ //������ �ڵ����� �⺻���� �����ȴ�. Employee(){} ,java.lang.Object ����
		System.out.println(a+ "���� �θ� constructor");
	}
}

class Manager extends Employee{
	Manager(){ //������
		//super(1); //Employee(int a) ȣ��
		super(); //default ������ ȣ��  Employee()
		System.out.println("���� �ڽ� constructor");
	}
}

public class TestSuper {

	public static void main(String[] args) {
		Manager m = new Manager(); //constructor ���� ȣ��
	}

}
