package D0712;

class Calculator2{
	int left, right;
	
	public void setOperans(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	public void sum() {
		System.out.println(this.left + this.right);
	}
	public void avg() {
		System.out.println((this.left + this.right) /2); 
	}
}

class SubstractionableCalculator extends Calculator2{

	public void substract() {
		System.out.println(this.left - this.right);
	}
}

public class CalculatorEx {

	public static void main(String[] args) {
		SubstractionableCalculator c1 = new SubstractionableCalculator();
		c1.setOperans(10,20);
		c1.sum();
		c1.avg();
		c1.substract();
	}

}
