package D0708;

public class Dog {
	String name;

	public static void main(String[] args) {
		Dog dog1 = new Dog();
		dog1.bark();
		dog1.name = "Bart";
		
		//Dog ���� ����ϴ�.
		Dog[] myDogs = new Dog[3];
		//���� �� ���� ���� �ֽ��ϴ�.
		myDogs[0] = new Dog();
		myDogs[1] = new Dog();
		myDogs[2] = dog1;
		
		//�迭 ���۷����� �Ἥ Dog��ü�� �����մϴ�.
		myDogs[0].name = "Fred";
		myDogs[1].name = "Marge";
		
		System.out.println("������ ���� �̸�:");
		System.out.println(myDogs[2].name);
		
		int x = 0;
		while(x<myDogs.length) {
			myDogs[x].bark();
			x = x + 1;
		}
		
	}

	public void bark() {
		this.name = name;
		System.out.println(name + "�� ��! �ϰ� ¢���ϴ�.");
	}

	public void eat() {}

	public void chaseCat() {}

}
