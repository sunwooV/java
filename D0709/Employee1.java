package D0709;

public class Employee1 {
	public static final int ENGINEER = 1;
	public static final int MANAGER = 2;
	public static final int SALESMAN = 3;
	
	private int type;
	public void setType(int type) {
		this.type = type;
	}
	
	public Employee1(int type) {
		setType(type);
	}


	public int getAmount() {
		switch(type) {
		case ENGINEER:
			System.out.println("ENGINEER");
			return 100;
		case MANAGER:
			System.out.println("MANAGER");
			return 200;
		case SALESMAN:
			System.out.println("SALESMAN");
			return 300;
		}
		return 0;
	}
	
	public void main(String[] args) {
		Employee1 e = new Employee1(3);
		e.getAmount();
	}

}
