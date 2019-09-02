package D0710;

interface EmployeeType{
	public int getAmount();
	
}

class Engineer implements EmployeeType{
	public int getAmount() {
		return 100;
	}
}

class Manager implements EmployeeType{
	public int getAmount() {
		return 200;
	}
}

class Salesman implements EmployeeType{
	public int getAmount() {
		return 300;
	}
}


public class Employee2 {
	private EmployeeType type;

	public Employee2(EmployeeType type) {
//		setType(type);
		this.type = type;
	}
//	public void setType(EmployeeType type) {
//		this.type = type;
//	}
	
	public int getAmount() {
		return type.getAmount();
	}
	
	public static void main(String[] args) {
		Engineer e = new Engineer();
		EmployeeType t = e;
		Employee2 m = new Employee2(t);
		System.out.println(m.getAmount());

	}

}
