package D0716;

class Project {
	private String name;
	private String startDt;
	private String endDt;
	private String location;
	private Developer[] dev;
	private Developer[] programmer = new Developer[10];
	private int sum=0;
	private int i=0;
	
	Project(String name, String startDt, String endDt, String location){
		this.name = name;
		this.startDt = startDt;
		this.endDt = endDt;
		this.location = location;
	}
	
	Project(String name, String startDt, String endDt, String location, Developer[] dev){
		this(name, startDt, endDt, location);
		this.dev = dev;
	}
	
	
	public void process() {
		plan();
//		join(d);
		design();
		develop();
	}
	
	public void plan() {
		System.out.println("Project �غ�");
	}
	
	public void join(Developer d) {
		//Project�� ������ Add
		programmer[i++] = d;
	}
	
	public void design() {}
	public void develop() {}
	
	public int getMMByDeveloper() {
		//Project�� ������ �������� �� ���� ������
		for(int i=0;i<dev.length;i++) {
			sum += dev[i].getPeriod();
		}
		return sum;
	}
	
	public void printDevelper() {
		System.out.println("\n====������====");
		for(int i=0;i<dev.length;i++) {
			if(dev[i] instanceof HighDeveloper) {
				System.out.println("��� ������ " + dev[i].getName() + " / Ư�� " + ((HighDeveloper) dev[i]).getSkill());
			}
			else if(dev[i] instanceof Developer){
				System.out.println("�Ϲ� ������ " + dev[i].getName());
			}
			
		}
	}
}

class HrProject extends Project {

	HrProject(String name, String startDt, String endDt, String location, Developer[] dev) {
		super(name, startDt, endDt, location, dev);
	}

	public void design() {
		System.out.println("HR ���� �۾� ����");
	}
	
	public void develop() {
		System.out.println("HR ���� �۾� ����");
	}
	
}

class Developer {
	private String name;
	private int period;
	Developer developer;
	
	Developer(String name, int period){
		setName(name);
		setPeriod(period);
	}
	
	String getName(){
		return name;
	}
	
	int getPeriod() {
		return period;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setPeriod(int period) {
		this.period = period;
	}
}

class HighDeveloper extends Developer {
	private String specialSkill;
	HighDeveloper highDeveloper;

	HighDeveloper(String name, int period) {
		super(name, period);
	}
	
	HighDeveloper(String name, int period, String specialSkill){
		this(name, period);
		setSkill(specialSkill);
	}
	
	String getSkill() {
		return specialSkill;
	}
	
	void setSkill(String specialSkill) {
		this.specialSkill = specialSkill;
	}
	
}

public class D0716 {
	public static void main(String[] args) {
		Developer[] dev = new Developer[3];
		dev[0] = new Developer("ȫ�浿",10);
		dev[1] = new Developer("��ƹ���", 12);
		dev[2] = new HighDeveloper("�����", 20, "JAVA PROGRAMMING");
		
		Project p = new HrProject("project1", "170710", "180702", "����", dev);
		
		p.join(dev[0]);
		p.join(dev[1]);
		
		p.process();
		p.printDevelper();
		System.out.println();
		System.out.println("�� ���� : " + p.getMMByDeveloper() + "����");
		
	}
}
