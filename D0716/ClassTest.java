package D0716;

public class ClassTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		A a = new B();
		//Class 클래스의 인스턴스는 자바상에서 Class들을 표현한다.
		Class c1 = a.getClass(); //Object의 runtime상의 class를 반환한다.
		System.out.println("class name: " + c1.getSuperclass());
		//새로운 instance를 생성한다.
		B b = (B)(c1.newInstance());
		b.m2();
		//B라는 이름의 클래스 객체를 반환, package까지 표현
		Class c2 = Class.forName("day6.B");
		//B instance를 A가 가르키로 override된 B.m()을 호출
		((A)c2.newInstance()).m();
		System.out.println("abc".getClass().getName());

	}

}

class A{
	public int i;
	public A() {
		System.out.println("A 생성자");
	}
	public void m() {
		System.out.println("A.m()");
	}
}

class B extends A{
	public B() {
		System.out.println("B 생성자");
	}
	public void m() {
		System.out.println("B.m()");
	}
	public void m2() {
		System.out.println("B.m2()");
	}
}