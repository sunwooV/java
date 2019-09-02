package D0717;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class Army {
	private String name;
	private int age;
	private String grade; // 계급
	private String strDt, endDt;
	private Date nextHoliday;
	private Army[] sub; // 부하직원
	private int price;

	Army(){
		
	}
	Army(String name, int age, String grade, String strDt, String endDt) {
		setName(name);
		setAge(age);
		setGrade(grade);
		setStrDt(strDt);
		setEndDt(endDt);
	}

	Army(String name, int age, String grade, String strDt, String endDt, Army[] sub) {
		this(name, age, grade, strDt, endDt);
		setSub(sub);
	}

	String getName() {
		return name;
	}

	int getAge() {
		return age;
	}

	String getGrade() {
		return grade;
	}

	String getStrDt() {
		return strDt;
	}

	String getEndDt() {
		return endDt;
	}

	Army[] getSub() {
		return sub;
	}

	void setName(String name) {
		this.name = name;
	}

	void setAge(int age) {
		this.age = age;
	}

	void setGrade(String grade) {
		this.grade = grade;
	}

	void setStrDt(String strDt) {
		this.strDt = strDt;
	}

	void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	void setSub(Army[] sub) {
		this.sub = sub;
	}

	public int getOverTimePayByGrade() { // grade별 야근 수당 리턴;
		switch (grade) {
		case "일병":
			price = 100000;
			break;
		case "상병":
			price = 200000;
			break;
		case "병장":
			price = 300000;
			break;
		default:
			System.out.println("계급을 잘못 입력하셨습니다.");
			price = 0;
			break;
		}
		return price;

	}

	public void printSubOverTimePay() { // 부하별 야근수당을 출력한다.
		DecimalFormat df = new DecimalFormat("#,###");
		
		
		for (int i = 0; i < sub.length; i++) {
			int price = sub[i].getOverTimePayByGrade();
			if(sub[i] instanceof SpecialArmy) {
				price += ((SpecialArmy)sub[i]).getBonus();
			}
			System.out.println("{" + sub[i].getName() + "} 의 야근 수당은 " + df.format(price) + "입니다.");
		}
	}

	public int getThisMMPeriod() { // 이번달의 남은 근무기간을 리턴한다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar day = Calendar.getInstance();
		String c = sdf.format(day.getTime());
		String Today = c.substring(6, 8);
		int date = Integer.parseInt(Today);//오늘 날짜 일수
		int max = day.getMaximum(Calendar.DAY_OF_MONTH);
		
		return max - date;
	}

	@SuppressWarnings("finally")
	public long getPeriod() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		long SubDateDays = 0;
		try {
			java.util.Date start = sdf.parse(strDt);
			java.util.Date end = sdf.parse(endDt);
			long SubDate = end.getTime() - start.getTime();
			SubDateDays = SubDate / (24 * 60 * 60 * 1000);
			SubDateDays = Math.abs(SubDateDays);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return SubDateDays;
		}
	}

	public int getSubTotalPeriod() throws ParseException { // 부하들의 총 근무기간을 리턴한다.
		long sum = 0;
		for (int i = 0; i < sub.length; i++) {
			sum += sub[i].getPeriod();
		}
		return (int) sum;
	}

	public boolean checkSub(Army[] s) { //모두 본인의 부하인지 리턴한다.
		boolean yon = false;
		for(int i=0;i<s.length;i++) {
			for(int j=0;j<sub.length;j++) {
				if((sub[j].name.equals(s[i].name)) && (sub[j].age == s[i].age) && (sub[j].grade.equals(s[i].grade))) {
					yon = true;
				}
				else yon = false;
			}
		}
		return yon;
		
	}
	
	public void returnSub(Army[] sub) {
		this.sub = sub;
	}
	
	public void printSub() throws ParseException {
		SimpleDateFormat before = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat after = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date startD = null;
		java.util.Date endD = null;
		
		for(int i=0;i<sub.length;i++) {
			if(sub[i] instanceof SpecialArmy) {
				System.out.print("우수   ");
			}
			else {
				System.out.print("        ");
			}
			startD = before.parse(sub[i].getStrDt());
			endD = before.parse(sub[i].getEndDt());
			
			String start = after.format(startD);
			String end = after.format(endD);
			
			System.out.println(sub[i].getGrade() + " " + sub[i].getName() + " " + start + " ~ " + end);
		}
	}
}

class SpecialArmy extends Army { // 우수한 군인 - 보너스가 추가된다.
	private int bonus; //야근수당에 bonus가 추가된다.
	SpecialArmy(String name, int age, String grade, String strDt, String endDt, int bonus){
		setName(name);
		setAge(age);
		setGrade(grade);
		setStrDt(strDt);
		setEndDt(endDt);
		setBonus(bonus);
	}
	SpecialArmy(String name, int age, String grade, String strDt, String endDt, int bonus, Army[] sub){
		this(name, age, grade, strDt, endDt, bonus);
		setSub(sub);
	}
	void setBonus(int bonus) {
		this.bonus = bonus;
	}
	int getBonus() {
		return bonus;
	}

}

public class Practice1 {

	public static void main(String[] args) throws ParseException {
		
		Army[] a_sub = new Army[2];
		Army[] b_sub = new Army[2];
		
		a_sub[0] = new Army("신길동", 20, "일병", "20190701", "20210102");
		a_sub[1] = new SpecialArmy("김상병", 23, "상병", "20180801", "20200302", 200000);
		
		b_sub[0] = new Army("신동길", 20, "일병", "20190701", "20210102");
		b_sub[1] = new SpecialArmy("김병상", 23, "상병", "20180801", "20200302", 200000);
		
		Army a1 = new Army("홍길동", 23, "병장", "20190101", "20201231", a_sub);
		
		
		System.out.print(a1.getName() + "은 " + a1.getGrade() + "이므로 야근수당은 ");
		System.out.println(a1.getOverTimePayByGrade() + "원 입니다.");
		
		System.out.println("\n=== 부하 직원 List ===");
		a1.printSub();
		
		System.out.println("\n=== 부하 직원 야근 수당 ===");
		a1.printSubOverTimePay();
		
		System.out.println("\n<이번 달 남은 근무 일수>");
		System.out.println(a1.getThisMMPeriod() + "일 남았습니다.");
		
		System.out.println();
		System.out.println("홍길동은 a팀을 부하로 두고 있는가? ");
		System.out.println(a1.checkSub(a_sub));
		System.out.println("홍길동은 b팀을 부하로 두고 있는가? ");
		System.out.println(a1.checkSub(b_sub));
		
		System.out.println("\n<부하의 총 남은 일수>");
		System.out.println(a1.getSubTotalPeriod() + "일");


	}

}
