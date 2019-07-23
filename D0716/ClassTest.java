package D0716;

public class ClassTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		A a = new B();
		//Class Ŭ������ �ν��Ͻ��� �ڹٻ󿡼� Class���� ǥ���Ѵ�.
		Class c1 = a.getClass(); //Object�� runtime���� class�� ��ȯ�Ѵ�.
		System.out.println("class name: " + c1.getSuperclass());
		//���ο� instance�� �����Ѵ�.
		B b = (B)(c1.newInstance());
		b.m2();
		//B��� �̸��� Ŭ���� ��ü�� ��ȯ, package���� ǥ��
		Class c2 = Class.forName("day6.B");
		//B instance�� A�� ����Ű�� override�� B.m()�� ȣ��
		((A)c2.newInstance()).m();
		System.out.println("abc".getClass().getName());

	}

}

class A{
	public int i;
	public A() {
		System.out.println("A ������");
	}
	public void m() {
		System.out.println("A.m()");
	}
}

class B extends A{
	public B() {
		System.out.println("B ������");
	}
	public void m() {
		System.out.println("B.m()");
	}
	public void m2() {
		System.out.println("B.m2()");
	}
}