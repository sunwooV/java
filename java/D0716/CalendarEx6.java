package D0716;

import java.util.Calendar;

public class CalendarEx6 {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("Usage : java CalendarEx6 2015 9");
			return;
		}
		int year = Integer.parseInt(args[0]);
		int month = Integer.parseInt(args[1]);
		
		int START_DAY_OF_WEEK = 0;
		int END_DAY = 0;
		
		Calendar sDay = Calendar.getInstance(); //시작일
		Calendar eDay = Calendar.getInstance(); //끝일
		
		//월의 경우 0 부터 11까지의 값을 가지므로 1을 빼주어야 한다.
		//예를 들어, 2004년 11월 1일은 sDay.set(2004, 10, 1);과 같이 해줘야 한다.
		sDay.set(year, month-1, 1);
		eDay.set(year,  month, 1);
		
		//다음달의 첫날에서 하루를 빼면 현재달의 마지막 날이 된다.
		//12월 1일에서 하루를 빼면 11월 30일이 된다.
		eDay.add(Calendar.DATE, -1);

	}

}
