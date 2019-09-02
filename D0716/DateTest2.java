package D0716;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTest2 {

	public static void main(String[] args) {
		System.out.println(transformDate("20190501"));

	}
	
	public static Date transformDate(String date) {
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
		
		//Date�� �����ϱ� ���ؼ��� ��¥ ������ yyyy-mm-dd�� �����ؾ� �Ѵ�.
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		
		java.util.Date tempDate = null;
		
		try {
			//���� yyyymmdd�� �� ��¥ �������� java.util.Date��ü�� �����.
			tempDate = beforeFormat.parse(date);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		//java.util.Date�� yyyy-mm-dd �������� �����Ͽ� String �� ��ȯ�Ѵ�.
		String transDate = afterFormat.format(tempDate);
		
		//��ȯ�� String ���� Date�� �����Ѵ�.
		Date d = Date.valueOf(transDate);
		
		return d;
	}

}
