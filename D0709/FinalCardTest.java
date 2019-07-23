package D0709;

class Card{
	final int NUMBER; //상수지만 선언과 함께 초기화 하지 않고 생성자에서 단 한번만 초기화할 수 있다.
	final String KIND;
	static int width = 100;
	static int height = 250;
	
	Card(String kind, int num){
		KIND = kind;
		NUMBER = num;
	}
	
	Card(){
		this("HEART", 1);
		
	}
	
	public String toString() { //가장 최상위 클래스 Object 메소드 중 하나이다. 
		return KIND + " " + NUMBER;
	}
}

public class FinalCardTest {

	public static void main(String[] args) {
		Card c = new Card("HEART", 10);
		//c.NUMBER = 5; //NUMBER을 final로 선언했기 때문에 값을 바꿀 수 없다.
		System.out.println(c.KIND);
		System.out.println(c.NUMBER);
		System.out.println(c); // System.out.printLn(c.toString()); 과 같은 의미
 
	}

}
