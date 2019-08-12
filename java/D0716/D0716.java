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
		System.out.println("Project 준비");
	}
	
	public void join(Developer d) {
		//Project에 개발자 Add
		programmer[i++] = d;
	}
	
	public void design() {}
	public void develop() {}
	
	public int getMMByDeveloper() {
		//Project에 참여한 개발자의 총 월간 개월수
		for(int i=0;i<dev.length;i++) {
			sum += dev[i].getPeriod();
		}
		return sum;
	}
	
	public void printDevelper() {
		System.out.println("\n====개발자====");
		for(int i=0;i<dev.length;i++) {
			if(dev[i] instanceof HighDeveloper) {
				System.out.println("고급 개발자 " + dev[i].getName() + " / 특기 " + ((HighDeveloper) dev[i]).getSkill());
			}
			else if(dev[i] instanceof Developer){
				System.out.println("일반 개발자 " + dev[i].getName());
			}
			
		}
	}
}

class HrProject extends Project {

	HrProject(String name, String startDt, String endDt, String location, Developer[] dev) {
		super(name, startDt, endDt, location, dev);
	}

	public void design() {
		System.out.println("HR 설계 작업 수행");
	}
	
	public void develop() {
		System.out.println("HR 개발 작업 수행");
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
		dev[0] = new Developer("홍길동",10);
		dev[1] = new Developer("김아무개", 12);
		dev[2] = new HighDeveloper("고급자", 20, "JAVA PROGRAMMING");
		
		Project p = new HrProject("project1", "170710", "180702", "서울", dev);
		
		p.join(dev[0]);
		p.join(dev[1]);
		
		p.process();
		p.printDevelper();
		System.out.println();
		System.out.println("총 개월 : " + p.getMMByDeveloper() + "개월");
		
	}
}
