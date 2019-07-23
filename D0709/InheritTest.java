package D0709;

class A {
	private int x = 100;
	private int y = 200;
}

class B extends A {
	private int r = 300;
	public void print() {
		System.out.println("B클래스");
	}
}


public class InheritTest {

	public static void main(String[] args) {
		B bb = new B();
		//아무런 멤버에 접근 할 수 없다. => 변수들이 private 이기 때문이다.
		//System.out.println(bb.r);
		
	}

}