package D0716;

import java.text.ParseException;

//java.util.Date : 오라클 날자타입과 연동불가
//java.sql.Date : 오라클 날자타입과 연동가능

public class DateTest {

	public static void main(String[] args) throws ParseException {
		//String -> java.sql.Date 변환
		String day = "2016-11-22"; //형식을 지켜야 함
		java.sql.Date d = java.sql.Date.valueOf(day);
		
		//String -> java.sql.Timestamp 변환
		String day2 = "2016-11-22 11:22:30.0"; //형식을 지켜야 함
		java.sql.Timestamp t = java.sql.Timestamp.valueOf(day2);
		
		//String -> java.until.Date 변환
		String day3 = "20161122"; //SimpleDateFormat 생성자에 전달되는 형식과 일치해야 함
		java.util.Date d3 = new java.text.SimpleDateFormat("yyyyMMdd").parse(day3);
		
		//java.util.Date -> java.sql.Date 변환
		java.util.Date u = new java.util.Date();
		java.sql.Date s = new java.sql.Date(u.getTime());

	}

}
