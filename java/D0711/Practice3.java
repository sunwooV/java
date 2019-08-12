package D0711;

interface Hobby{
	String setHobby(Hobby h);
}

class Tennis implements Hobby{
	@Override
	public String setHobby(Hobby h) {
		return "�״Ͻ�";
	}
	
}

class Piano implements Hobby{

	@Override
	public String setHobby(Hobby h) { 
		return "�ǾƳ�";
	}
}

class Parent{
	public Hobby h;
	int age;

	public void setAge(int age) {
		this.age = age;
	}
	public int getAge(int age) {
		return age;
	}
	
	public String setHobby(Hobby h) {
		this.h = h;
		return h.setHobby(h);
	}
	
}

class Child extends Parent{
	String name;
	
	public Child(String name) {
		this.name = name;
	}
	
	public String toString() {
		return h.setHobby(h);
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Child) {
			return name == ((Child)obj).name;
		}else {
			return false;
		}
	}
}


public class Practice3 {

	public static void main(String[] args) {
		Child c = new Child("ȫ�浿");
		Parent p = c;
		c.setAge(30); //30
//		System.out.println(c.getAge(30));
		
		Tennis t = new Tennis();
		Hobby h = t;
		c.setHobby(t); //�״Ͻ�
//		System.out.println(c.setHobby(t)); 
		
		Piano p2 = new Piano();
		Hobby h2 = p2;
		c.setHobby(p2);
//		System.out.println(c.setHobby(p2)); //�ǾƳ�
		
		Child c2 = new Child("ȫ�浿");
		
		System.out.println(c); //�ǾƳ�
		System.out.println(c.equals(c2)); //true
	}
}
