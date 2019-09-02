package D0711;

interface MyInterface2{
	default void method1() {
		System.out.println("methos1() in MyInterface");
	}
	
	default void method2() {
		System.out.println("method2() in MyInterface");
	}
	
	static void staticMethod() {
		System.out.println("staticMethod() in MyInterface");
	}
}


public class InterfacePractice {

	public static void main(String[] args) {
		

	}

}
