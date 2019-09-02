package D0715;

public class D0715 {

	public static void main(String[] args) throws Exception {
		Programmer p = new Programmer("���α׷���", 30);
		
		p.printInfo();

		Project[] project = new Project[10];

		project[0] = new Project("������Ʈ1", 10, "�Ｚ");
		project[1] = new Project("������Ʈ2", 12, "LG");
		project[2] = new Project("������Ʈ3", 9, "Sk");
		project[3] = new Project("������Ʈ4", 10, "KT");
		project[4] = new Project("������Ʈ5", 10, "�ֿ�");
		project[5] = new Project("������Ʈ6", 12, "���");

		p.joinProject(project[1]);
		System.out.println("���� �������� ������Ʈ : " + p.getNowProject().getName());

		try {
			p.addProjectHistory(project[0]);
			p.addProjectHistory(project[1]);
			p.addProjectHistory(project[2]);
			p.addProjectHistory(project[3]);
			p.addProjectHistory(project[4]);
			p.addProjectHistory(project[5]); //���� �߻�
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
	
	//Programmer �⺻���� ��ȯ
	void printInfo() {
		System.out.println("�̸� : " + name + ", ���� : " + age);
		System.out.println("-------------------------");
	}

	// ���� �������� Project�� ����
	void joinProject(Project p) {
		this.Now = p;
	}

	// Project����� �߰�
	void addProjectHistory(Project p) throws Exception {
		if (num >= 5) {
			throw new Exception("Project History�� 5������ �߰������մϴ�");
		}
		history[num++] = p;

	}

	// ���� Project�� �����Ѵ�.
	Project getNowProject() {
		return this.Now;
	}

	// Project ����� ����Ѵ�.
	void printProjectHistory() {
		System.out.println("===Project ���===");
		for (int i = 0; i < num; i++) {
			System.out.println("[" + (i + 1) + "] " + history[i].getName() + ", " + history[i].getMonth() + "����");
		}
	}

	void setTotalHistory(Project[] p) {
		for (int i = 0; i < num; i++) {
			sum += history[i].getMonth();
		}
	}

	// ��� Project�� �� ����� ���� �����Ѵ�.(��, ��)
	String getTotalHistory() {
		this.setTotalHistory(history);
		Tyear = sum / 12;
		Tmonth = sum % 12;
		return "�� ��� : " + Tyear + "�� " + Tmonth + "����";
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

	// Project�� ����
	String getName() {
		return name;
	}

	// Project�� ����
	void setName(String name) {
		this.name = name;
	}

	// Project ����Ⱓ�� ���� ����
	int getMonth() {
		return month;
	}

	// Project ����Ⱓ�� ����
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