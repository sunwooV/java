package D0719;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

class Plane {
	private String model;
	private String airline;
	private HashSet fixHis = new HashSet(); //���� �̷�
	
	Plane(String model, String airline){
		this.model = model;
		this.airline = airline;
	}
	
	//���� �̷��� �߰��Ѵ�.
	public void addFixHis(FixHis his) {
		//���� �����̷��� �߰��� �� ����(airport/date/cmt)
		
		fixHis.add(his);
	}
	
	//���� �̷��� ����Ѵ�.
	public void printFixtHis() throws ParseException {
		//�����̷��� ���: ����-����-�����

		Iterator it = fixHis.iterator();
		
		while(it.hasNext()) {
			FixHis next = (FixHis)it.next();
			System.out.println(next.getAirport() + " - " + next.FormatDate() + " - " + next.getCmt());
		}
	}
}

class FixHis { //�����̷� Ŭ����
	private String airport; //����
	private String date; //���� ����
	private String cmt; //���� ����
	
	FixHis(String airport, String date, String cmt){
		this.airport = airport;
		this.date = date;
		this.cmt = cmt;
	}
	
	void setAirport(String airport) {
		this.airport = airport;
	}
	
	public String getAirport() {
		return airport;
	}
	
	void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	void setCmt(String cmt) {
		this.cmt = cmt;
	}
	
	public String getCmt() {
		return cmt;
	}
	
	public String FormatDate() throws ParseException {
		SimpleDateFormat befor = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat after = new SimpleDateFormat("yyyy.MM.dd");
		java.util.Date afterDate = befor.parse(date); 
		return after.format(afterDate);
	}
	
	public boolean equals(Object obj) {
		String tmp = airport + date + cmt;
		String now = ((FixHis)obj).getAirport() + ((FixHis)obj).getDate() + ((FixHis)obj).getCmt();
		
		if(obj!=null && obj instanceof FixHis) {
			return now.equals(tmp);
		}
		else return false;
	}
	
	public int hashCode() {
		return Objects.hash(airport,date,cmt);
	}
}

public class Practice2 {

	public static void main(String[] args) throws ParseException {
		Plane p = new Plane("����777", "�ƽþƳ�");
		
		FixHis f = new FixHis("��������", "20170905", "����� ���� ����");
		FixHis f1 = new FixHis("��������", "20170905", "����� ���� ����"); //�ߺ� -> �Է� x
		FixHis f2 = new FixHis("��õ����", "20170805", "TV �����");
		
		p.addFixHis(f);
		p.addFixHis(f1);
		p.addFixHis(f2);
		
		System.out.println("======= ���� �̷� =======");
		p.printFixtHis();

	}

}
