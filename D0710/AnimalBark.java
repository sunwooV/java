package D0710;

interface AnimalType {
	public String bark();
//	public void bark();
}

class Dog implements AnimalType {

	@Override
	public String bark() {
		return "�� ��!";
	}
	
//	public void bark() {
//		System.out.println("�� ��!");
//	}
}

class Cat implements AnimalType {

	@Override
	public String bark() {
		return "�� ��~";
	}
	
//	public void bark() {
//		System.out.println("�� ��~");
//	}

}

class Animal {
	private AnimalType type;

	public Animal(AnimalType type) {
		setType(type);
	}

	public void setType(AnimalType type) {
		this.type = type;
	}

	public String bark() {
		return type.bark();
	}
	
//	public void bark() {
//		type.bark();
//	}
	
}

public class AnimalBark {


	public static void main(String[] args) {
		
		
		Cat c = new Cat();
		Dog d = new Dog();

		Animal a = new Animal(c);
		
		System.out.println(a.bark());
//		a.bark();
		
		
	}
}
