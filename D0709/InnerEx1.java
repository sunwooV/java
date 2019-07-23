package D0709;

//내부 클래스
public class InnerEx1 {
	int m = 0;
	class InstanceInner{
		int iv = 100;
		int t = m; //Outer의 멤버변수 접근가능
		//static int cv = 100; //에러! static 변수를 선언할 수 없다.
		final static int CONST = 100;
		
	}
	static class StaticInner{
		int iv = 200;
		//int t = m; //에러 발생
		static int cv = 200; //static 클래스만 static 멤버를 정의할 수 있다.
	}
	void myMethod() {
		class LocalInner{
			int iv = 300;
			int t = m; //Outer의 멤버변수 접근가능
			//static int cv = 300; //에러! static 변수를 선언할 수 없다.
			final static int CONST = 300; //final static은 상수이므로 허용
		}
	}
	public static void main(String[] args) {
		System.out.println(InstanceInner.CONST);
		System.out.println(StaticInner.cv);

	}

}
