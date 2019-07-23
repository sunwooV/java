package D0716;

import java.util.Calendar;

public class CalendarEx4 {

	public static void main(String[] args) {
		Calendar date = Calendar.getInstance(); //Singleton
		
		date.set(2005, 7, 31); //2005년 8월 31일
		System.out.println(toString(date));
		System.out.println("= 1일 후 =");
		date.add(Calendar.DATE, 1);
		System.out.println(toString(date));
		
		System.out.println("= 6달 전 =");
		date.add(Calendar.MONTH, -6);
		System.out.println(toString(date));
		
		System.out.println("= 31일 후(roll) =");
		date.roll(Calendar.DATE, 31);
		System.out.println(toString(date));
		
		System.out.println("= 31일 후(add) =");
		date.add(Calendar.DATE, 31);
		System.out.println(toString(date));
		
//		예를 들어 add메서드로 날짜필드(Calendar.DATE)의 값을 31만큼 증가시켰다면 
//		다음 달로 넘어가므로 월 필드(Calendar.MONTH)의 값도 1 증가하지만, 
//		roll메서드는 같은 경우에 월 필드의 값 은 변하지 않고 일 필드의 값만 바뀐다.


	}
	
	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH)+1)+"월 "
				+ date.get(Calendar.DATE) + "일";
	}

}
