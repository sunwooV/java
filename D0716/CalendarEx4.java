package D0716;

import java.util.Calendar;

public class CalendarEx4 {

	public static void main(String[] args) {
		Calendar date = Calendar.getInstance(); //Singleton
		
		date.set(2005, 7, 31); //2005�� 8�� 31��
		System.out.println(toString(date));
		System.out.println("= 1�� �� =");
		date.add(Calendar.DATE, 1);
		System.out.println(toString(date));
		
		System.out.println("= 6�� �� =");
		date.add(Calendar.MONTH, -6);
		System.out.println(toString(date));
		
		System.out.println("= 31�� ��(roll) =");
		date.roll(Calendar.DATE, 31);
		System.out.println(toString(date));
		
		System.out.println("= 31�� ��(add) =");
		date.add(Calendar.DATE, 31);
		System.out.println(toString(date));
		
//		���� ��� add�޼���� ��¥�ʵ�(Calendar.DATE)�� ���� 31��ŭ �������״ٸ� 
//		���� �޷� �Ѿ�Ƿ� �� �ʵ�(Calendar.MONTH)�� ���� 1 ����������, 
//		roll�޼���� ���� ��쿡 �� �ʵ��� �� �� ������ �ʰ� �� �ʵ��� ���� �ٲ��.


	}
	
	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR) + "�� " + (date.get(Calendar.MONTH)+1)+"�� "
				+ date.get(Calendar.DATE) + "��";
	}

}
