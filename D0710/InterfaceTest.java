package D0710;

interface interA1{
	int w = 10;
	static int x = 20;
	final int y = 30;
	public static final int z = 40;
}

public class InterfaceTest {

	public static void main(String[] args) {
		//interA1 ap = new interA1(); // interface는 instance를 생성할 수 없다
		//interA1.w = 100; // interface의 필드는 public static final이므로 값을 변경 할 수 없다.
		System.out.println("w = " + interA1.w); //10
		System.out.println("x = " + interA1.x); //20
		System.out.println("y = " + interA1.y); //30
		System.out.println("z = " + interA1.z); //40

	}

}
