package D0710;

//Template 패턴
//어떤 작업을 처리하는 일부분을 서브 클래스로 캡슐화해 전체 일을 수행하는 구조는 바꾸지 않으면서 특정 단계에서 수행하는 내역을 바꾸는 패턴
//즉, 전체적으로는 동일하면서 부분적으로는 다른 구문으로 구성된 메서드의 코드 중복을 최소화 할 때 유용하다.

abstract class CaffeineReferage{
	final void prepareRecipe() {
		this.boilWater();
		this.brew();
		this.pourInCup();
		this.addcndiments();
	}
	
	abstract void brew();          
	abstract void addcndiments();  //반드시 재정의 되어야한다.
	void boilWater() {
		System.out.println("물 끓이는 중");
	}
	void pourInCup() {
		System.out.println("컵에 따르는 중"); //재정의가 필요하다면 재정의해도 된다.
	}
}

class Coffee extends CaffeineReferage{
//	void boilWater() { //재정의를 해도 된다.
//		System.out.println("물 끓인다");
//	}
	void brew() {
		System.out.println("필터를 통해 커피를 우려내는 중");
	}
	void addcndiments() {
		System.out.println("설탕과 우유를 추가하는 중");
	}
}

class Tee extends CaffeineReferage{
	void brew() {
		System.out.println("차를 우려내는 중");
	}
	void addcndiments() {
		System.out.println("레몬을 추가하는 중");
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
