package D0716;

import java.text.ParseException;

//java.util.Date : ����Ŭ ����Ÿ�԰� �����Ұ�
//java.sql.Date : ����Ŭ ����Ÿ�԰� ��������

public class DateTest {

	public static void main(String[] args) throws ParseException {
		//String -> java.sql.Date ��ȯ
		String day = "2016-11-22"; //������ ���Ѿ� ��
		java.sql.Date d = java.sql.Date.valueOf(day);
		
		//String -> java.sql.Timestamp ��ȯ
		String day2 = "2016-11-22 11:22:30.0"; //������ ���Ѿ� ��
		java.sql.Timestamp t = java.sql.Timestamp.valueOf(day2);
		
		//String -> java.until.Date ��ȯ
		String day3 = "20161122"; //SimpleDateFormat �����ڿ� ���޵Ǵ� ���İ� ��ġ�ؾ� ��
		java.util.Date d3 = new java.text.SimpleDateFormat("yyyyMMdd").parse(day3);
		
		//java.util.Date -> java.sql.Date ��ȯ
		java.util.Date u = new java.util.Date();
		java.sql.Date s = new java.sql.Date(u.getTime());

	}

}
