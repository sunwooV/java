package D0709;

interface Race{
	public String getRace();
}

class Animal{
	String name, type;
	
}

abstract class Person implements Race{
	String name;
	int age;
	public Animal[] animal;
	
	public abstract void speak();

	@Override
	public String getRace() {
		return null;
	}	
}

class KoreaPerson extends Person{
	@Override
	public void speak() {

	}
}

class JapanPerson extends Person{
	@Override
	public void speak() {
		
	}
}

public class Example {

	public static void main(String[] args) {

                 
	}
}
