package D0709;

class Card{
	final int NUMBER; //������� ����� �Բ� �ʱ�ȭ ���� �ʰ� �����ڿ��� �� �ѹ��� �ʱ�ȭ�� �� �ִ�.
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
	
	public String toString() { //���� �ֻ��� Ŭ���� Object �޼ҵ� �� �ϳ��̴�. 
		return KIND + " " + NUMBER;
	}
}

public class FinalCardTest {

	public static void main(String[] args) {
		Card c = new Card("HEART", 10);
		//c.NUMBER = 5; //NUMBER�� final�� �����߱� ������ ���� �ٲ� �� ����.
		System.out.println(c.KIND);
		System.out.println(c.NUMBER);
		System.out.println(c); // System.out.printLn(c.toString()); �� ���� �ǹ�
 
	}

}
