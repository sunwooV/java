package D0719;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

class Plane {
	private String model;
	private String airline;
	private HashSet fixHis = new HashSet(); //수리 이력
	
	Plane(String model, String airline){
		this.model = model;
		this.airline = airline;
	}
	
	//수리 이력을 추가한다.
	public void addFixHis(FixHis his) {
		//같은 수리이력을 추가할 수 없다(airport/date/cmt)
		
		fixHis.add(his);
	}
	
	//수리 이력을 출력한다.
	public void printFixtHis() throws ParseException {
		//수리이력을 출력: 공항-날자-사고내용

		Iterator it = fixHis.iterator();
		
		while(it.hasNext()) {
			FixHis next = (FixHis)it.next();
			System.out.println(next.getAirport() + " - " + next.FormatDate() + " - " + next.getCmt());
		}
	}
}

class FixHis { //수리이력 클래스
	private String airport; //공항
	private String date; //수리 일자
	private String cmt; //수리 내용
	
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
		Plane p = new Plane("보잉777", "아시아나");
		
		FixHis f = new FixHis("김포공항", "20170905", "비행기 날개 엔진");
		FixHis f1 = new FixHis("김포공항", "20170905", "비행기 날개 엔진"); //중복 -> 입력 x
		FixHis f2 = new FixHis("인천공항", "20170805", "TV 모니터");
		
		p.addFixHis(f);
		p.addFixHis(f1);
		p.addFixHis(f2);
		
		System.out.println("======= 수리 이력 =======");
		p.printFixtHis();

	}

}
