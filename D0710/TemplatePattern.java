package D0710;

//Template ����
//� �۾��� ó���ϴ� �Ϻκ��� ���� Ŭ������ ĸ��ȭ�� ��ü ���� �����ϴ� ������ �ٲ��� �����鼭 Ư�� �ܰ迡�� �����ϴ� ������ �ٲٴ� ����
//��, ��ü�����δ� �����ϸ鼭 �κ������δ� �ٸ� �������� ������ �޼����� �ڵ� �ߺ��� �ּ�ȭ �� �� �����ϴ�.

abstract class CaffeineReferage{
	final void prepareRecipe() {
		this.boilWater();
		this.brew();
		this.pourInCup();
		this.addcndiments();
	}
	
	abstract void brew();          
	abstract void addcndiments();  //�ݵ�� ������ �Ǿ���Ѵ�.
	void boilWater() {
		System.out.println("�� ���̴� ��");
	}
	void pourInCup() {
		System.out.println("�ſ� ������ ��"); //�����ǰ� �ʿ��ϴٸ� �������ص� �ȴ�.
	}
}

class Coffee extends CaffeineReferage{
//	void boilWater() { //�����Ǹ� �ص� �ȴ�.
//		System.out.println("�� ���δ�");
//	}
	void brew() {
		System.out.println("���͸� ���� Ŀ�Ǹ� ������� ��");
	}
	void addcndiments() {
		System.out.println("������ ������ �߰��ϴ� ��");
	}
}

class Tee extends CaffeineReferage{
	void brew() {
		System.out.println("���� ������� ��");
	}
	void addcndiments() {
		System.out.println("������ �߰��ϴ� ��");
	}
}

public class TemplatePattern {

	public static void main(String[] args) {
		Coffee c = new Coffee();
		Tee t = new Tee();
		
		c.prepareRecipe();

		System.out.println();
		
		t.prepareRecipe();

	}

}
