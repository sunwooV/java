package D0715;

public class D0715 {

	public static void main(String[] args) throws Exception {
		Programmer p = new Programmer("프로그래머", 30);
		
		p.printInfo();

		Project[] project = new Project[10];

		project[0] = new Project("프로젝트1", 10, "삼성");
		project[1] = new Project("프로젝트2", 12, "LG");
		project[2] = new Project("프로젝트3", 9, "Sk");
		project[3] = new Project("프로젝트4", 10, "KT");
		project[4] = new Project("프로젝트5", 10, "쌍용");
		project[5] = new Project("프로젝트6", 12, "대우");

		p.joinProject(project[1]);
		System.out.println("현재 진행중인 프로젝트 : " + p.getNowProject().getName());

		try {
			p.addProjectHistory(project[0]);
			p.addProjectHistory(project[1]);
			p.addProjectHistory(project[2]);
			p.addProjectHistory(project[3]);
			p.addProjectHistory(project[4]);
			p.addProjectHistory(project[5]); //예외 발생
			p.printProjectHistory();
			System.out.println(p.getTotalHistory());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class Programmer {
	static int Tyear = 0, Tmonth = 0;
	
	static int num = 0;
	static int sum = 0;
	private int age;
	private String name;
	private Project Now;
	private Project[] history;
	
	Programmer(String name, int age) {
		this.name = name;
		this.age = age;
		this.history = new Project[5];
	}
	
	//Programmer 기본사항 반환
	void printInfo() {
		System.out.println("이름 : " + name + ", 나이 : " + age);
		System.out.println("-------------------------");
	}

	// 현재 진행중인 Project를 설정
	void joinProject(Project p) {
		this.Now = p;
	}

	// Project경력을 추가
	void addProjectHistory(Project p) throws Exception {
		if (num >= 5) {
			throw new Exception("Project History는 5개까지 추가가능합니다");
		}
		history[num++] = p;

	}

	// 현재 Project를 리턴한다.
	Project getNowProject() {
		return this.Now;
	}

	// Project 경력을 출력한다.
	void printProjectHistory() {
		System.out.println("===Project 경력===");
		for (int i = 0; i < num; i++) {
			System.out.println("[" + (i + 1) + "] " + history[i].getName() + ", " + history[i].getMonth() + "개월");
		}
	}

	void setTotalHistory(Project[] p) {
		for (int i = 0; i < num; i++) {
			sum += history[i].getMonth();
		}
	}

	// 모든 Project의 총 경력의 합을 리턴한다.(년, 월)
	String getTotalHistory() {
		this.setTotalHistory(history);
		Tyear = sum / 12;
		Tmonth = sum % 12;
		return "총 경력 : " + Tyear + "년 " + Tmonth + "개월";
	}

}

class Project {
	private String name;
	private int month;
	private String company;

	Project(String name, int month, String company) {
		setMonth(month);
		setName(name);
		setCompany(company);
	}

	// Project명 리턴
	String getName() {
		return name;
	}

	// Project명 설정
	void setName(String name) {
		this.name = name;
	}

	// Project 수행기간을 월로 리턴
	int getMonth() {
		return month;
	}

	// Project 수행기간을 설정
	void setMonth(int month) {
		this.month = month;
	}

	String getCompany() {
		return company;
	}

	void setCompany(String company) {
		this.company = company;
	}

}