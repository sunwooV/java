package D0717;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class Army {
	private String name;
	private int age;
	private String grade; // ���
	private String strDt, endDt;
	private Date nextHoliday;
	private Army[] sub; // ��������
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

	public int getOverTimePayByGrade() { // grade�� �߱� ���� ����;
		switch (grade) {
		case "�Ϻ�":
			price = 100000;
			break;
		case "��":
			price = 200000;
			break;
		case "����":
			price = 300000;
			break;
		default:
			System.out.println("����� �߸� �Է��ϼ̽��ϴ�.");
			price = 0;
			break;
		}
		return price;

	}

	public void printSubOverTimePay() { // ���Ϻ� �߱ټ����� ����Ѵ�.
		DecimalFormat df = new DecimalFormat("#,###");
		
		
		for (int i = 0; i < sub.length; i++) {
			int price = sub[i].getOverTimePayByGrade();
			if(sub[i] instanceof SpecialArmy) {
				price += ((SpecialArmy)sub[i]).getBonus();
			}
			System.out.println("{" + sub[i].getName() + "} �� �߱� ������ " + df.format(price) + "�Դϴ�.");
		}
	}

	public int getThisMMPeriod() { // �̹����� ���� �ٹ��Ⱓ�� �����Ѵ�.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar day = Calendar.getInstance();
		String c = sdf.format(day.getTime());
		String Today = c.substring(6, 8);
		int date = Integer.parseInt(Today);//���� ��¥ �ϼ�
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

	public int getSubTotalPeriod() throws ParseException { // ���ϵ��� �� �ٹ��Ⱓ�� �����Ѵ�.
		long sum = 0;
		for (int i = 0; i < sub.length; i++) {
			sum += sub[i].getPeriod();
		}
		return (int) sum;
	}

	public boolean checkSub(Army[] s) { //��� ������ �������� �����Ѵ�.
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
				System.out.print("���   ");
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

class SpecialArmy extends Army { // ����� ���� - ���ʽ��� �߰��ȴ�.
	private int bonus; //�߱ټ��翡 bonus�� �߰��ȴ�.
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
		
		a_sub[0] = new Army("�ű浿", 20, "�Ϻ�", "20190701", "20210102");
		a_sub[1] = new SpecialArmy("���", 23, "��", "20180801", "20200302", 200000);
		
		b_sub[0] = new Army("�ŵ���", 20, "�Ϻ�", "20190701", "20210102");
		b_sub[1] = new SpecialArmy("�躴��", 23, "��", "20180801", "20200302", 200000);
		
		Army a1 = new Army("ȫ�浿", 23, "����", "20190101", "20201231", a_sub);
		
		
		System.out.print(a1.getName() + "�� " + a1.getGrade() + "�̹Ƿ� �߱ټ����� ");
		System.out.println(a1.getOverTimePayByGrade() + "�� �Դϴ�.");
		
		System.out.println("\n=== ���� ���� List ===");
		a1.printSub();
		
		System.out.println("\n=== ���� ���� �߱� ���� ===");
		a1.printSubOverTimePay();
		
		System.out.println("\n<�̹� �� ���� �ٹ� �ϼ�>");
		System.out.println(a1.getThisMMPeriod() + "�� ���ҽ��ϴ�.");
		
		System.out.println();
		System.out.println("ȫ�浿�� a���� ���Ϸ� �ΰ� �ִ°�? ");
		System.out.println(a1.checkSub(a_sub));
		System.out.println("ȫ�浿�� b���� ���Ϸ� �ΰ� �ִ°�? ");
		System.out.println(a1.checkSub(b_sub));
		
		System.out.println("\n<������ �� ���� �ϼ�>");
		System.out.println(a1.getSubTotalPeriod() + "��");


	}

}
