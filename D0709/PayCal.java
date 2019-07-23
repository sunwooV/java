package D0709;

import java.util.Scanner;

public class PayCal {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("직책(사원/대리/과장/부장)과 기본급을 적으세요 : ");
		String jik = sc.nextLine();
		int money = sc.nextInt();
		
		
		int jikMoney;
		double pay;
		double real;
		
		if(jik.equals("과장")) {
			jikMoney = 200000;
		}
		else if(jik.equals("부장")) {
			jikMoney = 500000;
		}
		else {
			jikMoney = 0;
		}
		
		pay = money * 0.1;
		
		real = money + jikMoney - pay;
		
		System.out.println("실수령액 = " + real);

	}

}
