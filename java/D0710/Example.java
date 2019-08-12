package D0710;

class Parent{
	public int a = 100;
	
}

class Child extends Parent{
	public int a =200;
	public int getA() {
		return this.a;
	}
}

public class Example {

	public static void main(String[] args) {
		Child c = new Child();
		Parent p = new Child();
		
		System.out.println(c.a);
		System.out.println(p.a);
	}

}

