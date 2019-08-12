package D0709;

public class OperatorEx3 {

	public static void main(String[] args) {
		int x = 15;
		System.out.println(10 > x && x++ < 20); //false
		System.out.println("x = " + x); //15 -> 논리연산자라서 앞쪽 항에서 false이므로 뒷쪽 항 x++가 실행되지 않았다.
		System.out.println(10 < x || x++ < 20); //true
		System.out.println("x = " + x); //15 -> 논리연산자라서 앞쪽 항에서 true이므로 뒷쪽 항 x++가 실행되지 않았다.
		
		System.out.println(10 > x & x++ < 20); //false (비트연산자)
		System.out.println("x = " + x); //16
		System.out.println(10 < x | x++ < 20); //true
		System.out.println("x = " + x); //17
		
		
		//조건연산자: 조건식 ? 식1 : 식2
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
