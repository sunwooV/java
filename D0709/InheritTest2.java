package D0709;

class A1{
	protected int x = 100;
	protected void disp() {
		
	}
}

class B1 extends A1 {
	//private int x = 200;
	public int x = 200;
	public int y = 100;
	public void disp() {
		System.out.println("A1 클래스의 x = " + super.x);
		System.out.println("B1 클래스의 x = " + this.x);
	}
	
	public void disp2() {}
}

public class InheritTest2 {

	public static void main(String[] args) {
		B1 bp = new B1();
		System.out.println(bp.x); //200
		bp.disp();
		
		A1 ap = new B1();
		System.out.println(ap.x); //100
		ap.disp();

	}

}
