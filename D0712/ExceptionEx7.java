package D0712;

public class ExceptionEx7 {
	public static void main(String[] args) {
		System.out.println(1);
		System.out.println(2);
		try {
			System.out.println(3);
			System.out.println(0/0); //예외 발생
			System.out.println(4); //실행되지 않는다.
		} catch (ArithmeticException ae) { //좁은 범위
			if(ae instanceof ArithmeticException)
				System.out.println("true");
			System.out.println("ArithmeticException");
		} catch (Exception e) { //큰 범위
			System.out.println("Exception");
		}
		System.out.println(6);
	}
}