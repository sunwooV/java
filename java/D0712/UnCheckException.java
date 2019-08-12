package D0712;

public class UnCheckException {

	public static void main(String[] args) {
		UnCheckTest uck = new UnCheckTest();
		uck.test();
		try { //반드시 적지 않아도 오류가 나지 않는다.
			uck.test2();
		} catch (Exception e) {
			e.printStackTrace();
		} //ERROR 발생
	}

}

class UnCheckTest{
	//처리가 불가능하거나 호출자에게 예외 처리 여부의 결정권을 주고자 할 경우 사용
	public void test() throws RuntimeException{
		char[] a = new char[2];
		a[0] = 'a';
		a[1] = 'b';
		a[2] = 'c'; 
	}
	//모든 프로그래머가 처리해야 할 예외일 경우 사용
	public void test2() throws Exception{
		char[] a = new char[2];
		a[0] = 'a';
		a[1] = 'b';
		a[2] = 'c'; 
	}
}