package D0709;

import java.util.Scanner;

//��Ģ����
public class Calculator {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("A���� B��, �����ڸ� �Է��ϼ���.");

		int result;
		
		System.out.print("A�� : ");
		int a = sc.nextInt();
		System.out.print("B�� : ");
		int b = sc.nextInt();
		System.out.print("������(+,-,*,/) : ");
		String yeonsan = sc.next();
		
		if(yeonsan.equals("+")) {
			result = a + b;
			System.out.println("��� = " + result);
		}
		else if(yeonsan.equals("-")) {
			result = a - b;
			System.out.println("��� = " + result);
		}
		else if(yeonsan.equals("*")) {
			result = a * b;
			System.out.println("��� = " + result);
		}
		else if(yeonsan.equals("/")) {
			result = (int) a / b;
			System.out.println("��� = " + result);
		}
		else {
			System.out.println("�߸��� �����ڸ� �Է��Ͽ����ϴ�.");
		}
		
		
	}

}
