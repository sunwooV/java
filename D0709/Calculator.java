package D0709;

import java.util.Scanner;

//사칙연산
public class Calculator {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("A값과 B값, 연산자를 입력하세요.");

		int result;
		
		System.out.print("A값 : ");
		int a = sc.nextInt();
		System.out.print("B값 : ");
		int b = sc.nextInt();
		System.out.print("연산자(+,-,*,/) : ");
		String yeonsan = sc.next();
		
		if(yeonsan.equals("+")) {
			result = a + b;
			System.out.println("결과 = " + result);
		}
		else if(yeonsan.equals("-")) {
			result = a - b;
			System.out.println("결과 = " + result);
		}
		else if(yeonsan.equals("*")) {
			result = a * b;
			System.out.println("결과 = " + result);
		}
		else if(yeonsan.equals("/")) {
			result = (int) a / b;
			System.out.println("결과 = " + result);
		}
		else {
			System.out.println("잘못된 연산자를 입력하였습니다.");
		}
		
		
	}

}
