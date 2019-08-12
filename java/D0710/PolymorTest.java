 package D0710;

class A6{
	public void aaa() {
		System.out.println("aaa");
	}
	public void bbb() {
		System.out.println("bbb");
	}
}

class B6 extends A6 {
	public void bbb() {
		System.out.println("bbb1");
	}
	public void ccc() {
		System.out.println("ccc");
	}
}



public class PolymorTest {

	public static void main(String[] args) {
		A6 ap = new B6(); //다형적 표현
		ap.aaa(); //aaa
		ap.bbb(); //bbb1
		//ap.ccc(); //접근 가능한 멤버는 Data Type에 의해 결정, Compile error 발생

	}

}
