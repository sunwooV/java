package D0709;

class A {
	private int x = 100;
	private int y = 200;
}

class B extends A {
	private int r = 300;
	public void print() {
		System.out.println("BŬ����");
	}
}


public class InheritTest {

	public static void main(String[] args) {
		B bb = new B();
		//�ƹ��� ����� ���� �� �� ����. => �������� private �̱� �����̴�.
		//System.out.println(bb.r);
		
	}

}