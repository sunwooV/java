package D0709;

public class ConstructorTest { //�����ڸ� ������ ������ �� �ִ�.
	int a, b, c, d, e, f, g;
	ConstructorTest(){ 
		a = 1; b = 2; c = 3;
		d = 4; e = 5; f = 6; g = 7;
	}
	
	ConstructorTest(int x){
		this(); //ConstructorTest() �������� ���������� ȣ���Ѵ�.
		d = x;
		//this(); -> error, ������ �ȿ��� �ٸ� �����ڸ� ȣ���Ϸ��� ù��° ���ο��� ȣ���ؾ� �Ѵ�.
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d); //10
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
	}
	
	ConstructorTest(int x, int y){
		this(x); //ConstructorTest(int x) �����ڸ� ȣ��
		e = y;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d); //10
		System.out.println(e); //20
		System.out.println(f);
		System.out.println(g);
	}
	
	public static void main(String[] args) {
		ConstructorTest rEx = new ConstructorTest(10, 20); //ConstructorTest(int x, int y) �����ڸ� ȣ��
	}

}
