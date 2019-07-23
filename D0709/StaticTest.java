package D0709;

public class StaticTest {
	private static int x;
	
	static {
		System.out.println("Static...");
		x = 0;
	
	}
	
	public static void Test() {
		System.out.println("Test...");
	}
	
	public StaticTest(String arg) {
		System.out.println("»ý¼ºÀÚ" + arg);
	}
	
	
	public static void main(String[] args) {
		System.out.println("Main...");
		StaticTest st1 = new StaticTest("1");
		StaticTest st2 = new StaticTest("2");
		StaticTest st3 = new StaticTest("3");


	}

}
