package D0716;

import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormatTest {

	public static void main(String[] args) {
		DecimalFormat df2 = new DecimalFormat("#,###.##");
		System.out.println(df2.format(123456789.98)); //123,456,789.98
		
		DecimalFormat df3 = new DecimalFormat("#,###");
		System.out.println(df3.format(123456789)); //123,456,789
		
		Date today = new Date();
		SimpleDateFormat sdf4 = new SimpleDateFormat("yy-MM-dd hh:mm:ss a");
		System.out.println(sdf4.format(today));
		
		String pattern = "60#D|70#C|80<B|90#A"; //90은 A, 80은 C
		int[] scores = {91, 90, 80, 88, 70, 52, 60};
		
		ChoiceFormat form = new ChoiceFormat(pattern);
		
		for(int i=0;i<scores.length;i++) {
			System.out.println(scores[i] + ":" + form.format(scores[i]));
		}
		
		String msg = "Name: {0} \nTel: {1} \nAge:{2} \nBirthday: {3}";
		
		Object[] arguments = {"이자바", "02-123-1234", "27", "07-09"};
		
		String result = MessageFormat.format(msg, arguments);
		System.out.println(result);
	}

}
