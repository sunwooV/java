package D0709;

interface EmployeeType{
	public int getAmount();
}

class Engineer implements EmployeeType{
	public int getAmount() {
		return 100;
	}
}

class Manager2 implements EmployeeType{
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
		setType(type);
	}
	public void setType(EmployeeType type) {
		this.type = type;
	}
	public void getAmount() {
		type.getAmount();
	}
	
	public void main(String[] args) {
		System.out.println(type.getAmount());
	}
}
