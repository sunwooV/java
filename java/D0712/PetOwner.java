package D0712;

public class PetOwner {

	public void start() {
		Vet v = new Vet();
		Dog d = new Dog();
		Cat c = new Cat();
		v.giveShot(d);
		v.giveShot(c);
	}
	
	
	public static void main(String[] args) {
		PetOwner p = new PetOwner();
		p.start();
	}

}

class Vet{ //���ǻ�
	public void giveShot(Animal a) {//�ֻ縦 ���´�.
		a.makeNoise(); //�������� �ٸ� ���Ҹ��� ������.
	}
}

class Animal{
	//�� ������ �� �޼ҵ带 Overriding�Ѵ�.
	public void makeNoise() {}
}

class Dog extends Animal{
	public void makeNoise() {
		System.out.println("�۸�!");
	}
}
class Cat extends Animal{
	public void makeNoise() {
		System.out.println("�߿�~");
	}
}
