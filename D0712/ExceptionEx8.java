package D0712;

public class ExceptionEx8 {

	public static void main(String[] args) {
		System.out.println(1);
		System.out.println(2);
		try {
			System.out.println(3);
			System.out.println(0/0); //예외발생
			System.out.println(4); //실행되지 않는다.
		} catch(ArithmeticException ae) {
			ae.printStackTrace();
			//메소드가 내부적으로 예외 결과를 화면에 출력한다. 
			//printStackTrace는 가장 자세한 예외 정보를 제공한다.

			System.out.println("예외메시지 : " + ae.getMessage());
			//오류에 대한 기본적인 내용을 출력해준다. 상세하지 않다.

		}
		System.out.println(6);

	}

}
