package D0711;

class Member{
	String name;
	public Member(String name) {
		setName(name);
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}

class SpecialMember extends Member{

	public SpecialMember(String name) {
		super(name);
	}
	
}


public class Practice2 {

	public static void main(String[] args) {
		SpecialMember m = new SpecialMember("ȫ�浿");
		Member s = m;
		
		s.getName();
		System.out.println(s.getName()); //ȫ�浿
	}
}
