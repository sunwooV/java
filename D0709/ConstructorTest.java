package D0709;

public class ConstructorTest { //생성자를 여러번 정의할 수 있다.
	int a, b, c, d, e, f, g;
	ConstructorTest(){ 
		a = 1; b = 2; c = 3;
		d = 4; e = 5; f = 6; g = 7;
	}
	
	ConstructorTest(int x){
		this(); //ConstructorTest() 생성자의 변수값들을 호출한다.
		d = x;
		//this(); -> error, 생성자 안에서 다른 생성자를 호출하려면 첫번째 라인에서 호출해야 한다.
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d); //10
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
	}
	
	ConstructorTest(int x, int y){
		this(x); //ConstructorTest(int x) 생성자를 호출
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
		ConstructorTest rEx = new ConstructorTest(10, 20); //ConstructorTest(int x, int y) 생성자를 호출
	}

}
