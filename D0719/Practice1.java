package D0719;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Car {
	private String name;
	private String model;
	private int efficiency; // 1000cc
	private int distance; // 1��Ű��
	private HashMap fixHis = new HashMap(); // �����̷�
	private String nowOwner;
	private HashSet accidentHis = new HashSet(); // ����̷�

	Car(String name, int efficiency, int distance, String nowOwner) {
		this.name = name;
		this.efficiency = efficiency;
		this.distance = distance;
		this.nowOwner = nowOwner;
	}

	// ��� �̷��� �߰��Ѵ�.
	public void addAccidentHis(String info) throws Exception {
		String[] in = info.split("/");
		String location = in[0];
		String date = in[1];
		String time = in[2];
		Accident accident = new Accident(location, date, time);
		
		if(Util.dateCheck(date + " " + time, "yyyyMMdd HH:mm")) {
			accidentHis.add(accident);
		}
		else {
			throw new Exception("��¥ Format�� ��Ȯ���� �ʽ��ϴ�.");
		}
	
	}

	// ��� �̷��� ����Ѵ�.
	public void printAccidentHis() throws ParseException {

		Iterator it = accidentHis.iterator();

		while (it.hasNext()) {
			Accident next = (Accident)it.next();
			System.out.println("{" + next.getLocation() + "} - {"
					+ next.FormatDate() + "} ? {" + next.getTime() + "}");

		}
	}

	// ���� �̷��� �߰��Ѵ�.
	public void addFixHis(String date, String item, String fixcmt) throws Exception { // fixcmt :�����̷�
		Fix fix = new Fix(date, item); //fixHis�� key
		//date�� item�� fixcmt�� �߰��Ѵ�.
		//date�� item�� �ߺ��� �� ����.
		
		if (fixHis.containsKey(fix)) {
			throw new Exception("���� ��¥�� ���� ������ǰ�� �ߺ��� �� �����ϴ�.");
		} else {
			fixHis.put(fix, fixcmt);
		}

		
	}

	// ���� �̷��� ����Ѵ�.
	public void printFixHis() {
		Set set = fixHis.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println(e.getKey().toString() + " ? " + e.getValue());
		}
	}
	
	public String toString() {
		return "�� �̸�: " + name + ", ����: " + efficiency + "cc, ���� �Ÿ�: " + distance + ", ���� ����: " + nowOwner;
	}

}

class Accident {
	private String location;
	private String date;
	private String time;

	Accident(String location, String date, String time) throws Exception {
		this.location = location;
		this.date = date;
		this.time = time;
	}

	void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}
	
	public String FormatDate() throws ParseException {
		SimpleDateFormat befor = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat after = new SimpleDateFormat("yyyy.MM.dd");
		java.util.Date afterDate = befor.parse(date);
		
		return after.format(afterDate);
	}

	public boolean equals(Object obj) {
		String his = location + date + time;
		String now = ((Accident) obj).getLocation() + ((Accident) obj).getDate() + ((Accident) obj).getTime();

		if (obj != null && (Accident) obj instanceof Accident) {
			return now.equals(his);
		} else
			return false;
	}

	public int hashCode() {
		return Objects.hash(location, date, time);
	}
	
}

class Fix {
	private String date, item;
	
	Fix(String date, String item){
		this.date = date;
		this.item = item;
	}
	
	void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	void setItem(String item) {
		this.item = item;
	}
	
	public String getItem() {
		return item;
	}
	
	public boolean equals(Object obj) {
		String tmp = date + item;
		String now = ((Fix)obj).getDate() + ((Fix)obj).getItem();
		
		if (obj != null && (Fix) obj instanceof Fix) {
			return tmp.equals(now);
		} else
			return false;
	}

	public int hashCode() {
		return Objects.hash(date, item);
	}
	
	public String toString() {
		return date + " ? " + item;
	}

}

class Util{
	public static boolean dateCheck(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
		sdf.setLenient(false);
		try {
			sdf.parse(date);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}

public class Practice1 {

	public static void main(String[] args) throws Exception {
		Car c = new Car("truck", 2000, 1000, "�ż���");
		
		System.out.println(c);
		
		System.out.println();
		System.out.println("===== ��� �̷� =====");
		try {
			c.addAccidentHis("��ǻ�Ÿ�/20190602/08:00");
			c.addAccidentHis("��ǻ�Ÿ�/20190602/08:00"); //�ߺ� -> ����x
			c.addAccidentHis("��Ÿ����Ÿ�/20190501/15:00");
			c.printAccidentHis();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("===== ���� �̷� =====");
		try {
			c.addFixHis("20180101", "������ǰ", "�����̷�");
//			c.addFixHis("20180101", "������ǰ", "�����̷�"); //�ߺ� -> exceptionó��
			c.addFixHis("20180101", "������ǰ2", "�����̷�2");
			c.printFixHis();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
