package D0712;

public class MathEx {

	public static void main(String[] args) {
		//절대값 반환
		int I = Math.abs(-10); //10
		System.out.println("i = " + I);
		double D = Math.abs(-10.0); //10.0
		System.out.println("d = " + D);
		
		//올림
		double d = Math.ceil(10.1); //11.0
		double d2 = Math.ceil(-10.1); //-10.0
		double d3 = Math.ceil(10.000015); //11.0
		System.out.println(d + ", " + d2 + ", " + d3);
		
		//버림
		double dd = Math.floor(10.8); //10.0
		double dd2 = Math.floor(-10.8); //-11.0
		System.out.println(dd + ", " + dd2);
		
		//큰 값 반환
		double d11 = Math.max(9.5, 9.50001); //9.50001
		int i = Math.max(0,-1); //0
		System.out.println(d11 + ", " + i);
		
		//작은 값 반환
		double d22 = Math.min(9.5, 9.50001); //9.5
		int i22 = Math.min(0, -1); //-1
		System.out.println(d22 + ", " + i22);
		
		//0.0 ~ 1.0범위의 임의의 double 반환
		double ddd = Math.random(); //0.0 <= ddd < 1.0
		int iii = (int)(Math.random()*10)+1; // 1 <= iii < 11
		System.out.println(ddd + ", " + iii);
		
		//가장 가까운 정수값을 double형으로 반환
		double d4 = Math.rint(5.5); //6.0
		double d5 = Math.rint(5.1); //5.0
		double d6 = Math.rint(-5.5); //-6.0
		double d7 = Math.rint(-5.1); //-5.0
		System.out.println(d4 + ", " + d5 + ", " + d6 + ", " + d7);
		
		//소수점 첫째자리에서 반올림한 long값을 반환
		long l = Math.round(5.5); //6
		long l2 = Math.round(5.11); //5
		long l3 = Math.round(-5.5); //-5
		long l4 = Math.round(-5.1); //-5
		double d8 = 90.7552; 
		double d9 = Math.round(d8*100)/100.0; //90.76
		System.out.println(l + ", " + l2 + ", " + l3 + ", " + l4 + ", " + d9);
		
		
		double val = 90.7552;
		System.out.println("round("+ val +")=" + Math.round(val)); // 반올림
		val *= 100;
		System.out.println("round("+ val +")=" + Math.round(val)); // 반올림
		System.out.println("round("+ val +")/100 =" + Math.round(val)/100); // 반올림
		System.out.println("round("+ val +")/100.0=" + Math.round(val)/100.0); // 반올림
		System.out.println();
		System.out.printf("ceil(%3.1f)=%3.1f%n", 1.1, Math.ceil(1.1)); // 올림
		System.out.printf("floor(%3.1f)=%3.1f%n", 1.5, Math.floor(1.5)); // 버림
		System.out.printf("round(%3.1f)=%d%n", 1.1, Math.round(1.1)); // 반올림
		
		System.out.printf("round(%3.1f)=%d%n", 1.5, Math.round(1.5)); // 반올림
		System.out.printf("rint(%3.1f)=%f%n", 1.5, Math.rint(1.5)); // 반올림
		System.out.printf("round(%3.1f)=%d%n", -1.5, Math.round(-1.5)); // 반올림
		System.out.printf("rint(%3.1f)=%f%n", -1.5, Math.rint(-1.5)); // 반올림
		System.out.printf("ceil(%3.1f)=%f%n", -1.5, Math.ceil(-1.5)); // 올림
		System.out.printf("floor(%3.1f)=%f%n", -1.5, Math.floor(-1.5)); // 버림
	}

}
