package D0710;

interface AnimalType {
	public String bark();
//	public void bark();
}

class Dog implements AnimalType {

	@Override
	public String bark() {
		return "港 港!";
	}
	
//	public void bark() {
//		System.out.println("港 港!");
//	}
}

class Cat implements AnimalType {

	@Override
	public String bark() {
		return "衬 克~";
	}
	
//	public void bark() {
//		System.out.println("衬 克~");
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
