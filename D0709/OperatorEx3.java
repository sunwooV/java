package D0709;

public class OperatorEx3 {

	public static void main(String[] args) {
		int x = 15;
		System.out.println(10 > x && x++ < 20); //false
		System.out.println("x = " + x); //15 -> �������ڶ� ���� �׿��� false�̹Ƿ� ���� �� x++�� ������� �ʾҴ�.
		System.out.println(10 < x || x++ < 20); //true
		System.out.println("x = " + x); //15 -> �������ڶ� ���� �׿��� true�̹Ƿ� ���� �� x++�� ������� �ʾҴ�.
		
		System.out.println(10 > x & x++ < 20); //false (��Ʈ������)
		System.out.println("x = " + x); //16
		System.out.println(10 < x | x++ < 20); //true
		System.out.println("x = " + x); //17
		
		
		//���ǿ�����: ���ǽ� ? ��1 : ��2
		int y,z;
		int absX,absY, absZ;
		char signX, signY, signZ;
		
		x = 10;
		y = -5;
		z = 0;
		
		absX = x >= 0 ? x : -x;
		absY = y >= 0 ? y : -y;
		absZ = z >= 0 ? z : -z;
		
		System.out.println(absX + " " + absY + " "  + absZ); //10 5 0
	}

	
	
}
