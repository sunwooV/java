package D0709;

import java.util.Scanner;

public class PayCal {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("��å(���/�븮/����/����)�� �⺻���� �������� : ");
		String jik = sc.nextLine();
		int money = sc.nextInt();
		
		
		int jikMoney;
		double pay;
		double real;
		
		if(jik.equals("����")) {
			jikMoney = 200000;
		}
		else if(jik.equals("����")) {
			jikMoney = 500000;
		}
		else {
			jikMoney = 0;
		}
		
		pay = money * 0.1;
		
		real = money + jikMoney - pay;
		
		System.out.println("�Ǽ��ɾ� = " + real);

	}

}
