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

class Vet{ //수의사
	public void giveShot(Animal a) {//주사를 놓는다.
		a.makeNoise(); //동물마다 다른 비명소리를 지른다.
	}
}

class Animal{
	//각 동물은 이 메소드를 Overriding한다.
	public void makeNoise() {}
}

class Dog extends Animal{
	public void makeNoise() {
		System.out.println("멍멍!");
	}
}
class Cat extends Animal{
	public void makeNoise() {
		System.out.println("야옹~");
	}
}
