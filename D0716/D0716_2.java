package D0716;

class CompanyMan {
	private String name;
	private int age;
	private String[] hobby = new String[3];
	private Company nowCompany;
	private Company[] historyCompany = new Company[10];
	private int len=0, len2=0, sum = 0;;
	
	CompanyMan(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	CompanyMan(String name, int age, Company nowCompany){
		this.name = name;
		this.age = age;
		this.nowCompany = nowCompany;
	}
	
	//취미 추가 메소드
	public boolean setHobby(String hob) {
		hobby[len] = hob;
		len++;
		return true;
	}
	
	//취미 삭제 메소드
	public boolean delHobby(String hob) {
		for(int i=0;i<len;i++) {
			if(hobby[i].equals(hob)) {
				String temp = hobby[i+1];
				hobby[i+1] = null;
				hobby[i] = temp;
				len = len-1;
			}
			else {
				System.out.println("<설정되어있지 않은 취미가 입력되어서 삭제가 실행되지 않았습니다.>");
				break;
			}
		}
		return true;
	}
	
	public void printHobby() {
		for(int i=0;i<len;i++) {
			System.out.println((i+1) + "." + hobby[i]);
		}
	}
	
	//현재 근무 회사 등록 - 현재 근무 회사가 변경되면 historyCompany에 이력을 추가
	public boolean setNowCompany(Company company) {

		if(nowCompany != company) {
			historyCompany[len2++] = company;
			this.nowCompany = company;
		}
		

		return true;
	}
	
	//총 회사근무이력의 기간 리턴
	public int getTotalPeriod() {
		
		for(int i=0;i<len2;i++) {
			sum += historyCompany[i].getPeriod();
		}
		return sum;
	}
	
	//근무한 회사 출력
	public void printHistoryCompany() {
		System.out.println("---근무했던 회사 LIST---");
		for(int i=0;i<len2-1;i++) {
			System.out.println((i+1) + "." + historyCompany[i].getName());
		}
	}
	
	public void printNowCompany() {
		System.out.println("---현재 근무 중인 회사---");
		System.out.println(historyCompany[len2-1].getName());
	}
	
}

class  Company {
	private String name;
	private int period;
	
	Company(String name){
		setName(name);
	}
	
	Company(String name, int period){
		setName(name);
		setPeriod(period);
	}
	
	String getName() {
		return name;
	}
	
	int getPeriod() {
		return period;
	}
	
	void setName(String name){
		this.name = name;
	}
	
	void setPeriod(int period) {
		this.period = period;
	}
}

public class D0716_2 {

	public static void main(String[] args) {
		CompanyMan cm = new CompanyMan("홍길동", 23);
		
		Company[] c = new Company[5];
		c[0] = new Company("sk", 3);
		c[1] = new Company("kt", 5);
		c[2] = new Company("lg", 10);

		Company cp = new Company("samsung");
		
		cm.setNowCompany(c[0]);
		cm.setNowCompany(c[1]);
		cm.setNowCompany(c[2]);
		cm.setNowCompany(cp);
		
		cm.printHistoryCompany();
		System.out.println("\n총 경력 : " + cm.getTotalPeriod());
		
		System.out.println();
		cm.printNowCompany();
		
		System.out.println();
		System.out.println("---취미 추가 후---");
		cm.setHobby("bolling");
		cm.setHobby("baseball");
		cm.printHobby();
		
		System.out.println();
		System.out.println("---취미 삭제 후---");
		cm.delHobby("bolling");
		cm.printHobby();
		
		

	}

}
