package D0712;

public class MathEx {

	public static void main(String[] args) {
		//���밪 ��ȯ
		int I = Math.abs(-10); //10
		System.out.println("i = " + I);
		double D = Math.abs(-10.0); //10.0
		System.out.println("d = " + D);
		
		//�ø�
		double d = Math.ceil(10.1); //11.0
		double d2 = Math.ceil(-10.1); //-10.0
		double d3 = Math.ceil(10.000015); //11.0
		System.out.println(d + ", " + d2 + ", " + d3);
		
		//����
		double dd = Math.floor(10.8); //10.0
		double dd2 = Math.floor(-10.8); //-11.0
		System.out.println(dd + ", " + dd2);
		
		//ū �� ��ȯ
		double d11 = Math.max(9.5, 9.50001); //9.50001
		int i = Math.max(0,-1); //0
		System.out.println(d11 + ", " + i);
		
		//���� �� ��ȯ
		double d22 = Math.min(9.5, 9.50001); //9.5
		int i22 = Math.min(0, -1); //-1
		System.out.println(d22 + ", " + i22);
		
		//0.0 ~ 1.0������ ������ double ��ȯ
		double ddd = Math.random(); //0.0 <= ddd < 1.0
		int iii = (int)(Math.random()*10)+1; // 1 <= iii < 11
		System.out.println(ddd + ", " + iii);
		
		//���� ����� �������� double������ ��ȯ
		double d4 = Math.rint(5.5); //6.0
		double d5 = Math.rint(5.1); //5.0
		double d6 = Math.rint(-5.5); //-6.0
		double d7 = Math.rint(-5.1); //-5.0
		System.out.println(d4 + ", " + d5 + ", " + d6 + ", " + d7);
		
		//�Ҽ��� ù°�ڸ����� �ݿø��� long���� ��ȯ
		long l = Math.round(5.5); //6
		long l2 = Math.round(5.11); //5
		long l3 = Math.round(-5.5); //-5
		long l4 = Math.round(-5.1); //-5
		double d8 = 90.7552; 
		double d9 = Math.round(d8*100)/100.0; //90.76
		System.out.println(l + ", " + l2 + ", " + l3 + ", " + l4 + ", " + d9);
		
		
		double val = 90.7552;
		System.out.println("round("+ val +")=" + Math.round(val)); // �ݿø�
		val *= 100;
		System.out.println("round("+ val +")=" + Math.round(val)); // �ݿø�
		System.out.println("round("+ val +")/100 =" + Math.round(val)/100); // �ݿø�
		System.out.println("round("+ val +")/100.0=" + Math.round(val)/100.0); // �ݿø�
		System.out.println();
		System.out.printf("ceil(%3.1f)=%3.1f%n", 1.1, Math.ceil(1.1)); // �ø�
		System.out.printf("floor(%3.1f)=%3.1f%n", 1.5, Math.floor(1.5)); // ����
		System.out.printf("round(%3.1f)=%d%n", 1.1, Math.round(1.1)); // �ݿø�
		
		System.out.printf("round(%3.1f)=%d%n", 1.5, Math.round(1.5)); // �ݿø�
		System.out.printf("rint(%3.1f)=%f%n", 1.5, Math.rint(1.5)); // �ݿø�
		System.out.printf("round(%3.1f)=%d%n", -1.5, Math.round(-1.5)); // �ݿø�
		System.out.printf("rint(%3.1f)=%f%n", -1.5, Math.rint(-1.5)); // �ݿø�
		System.out.printf("ceil(%3.1f)=%f%n", -1.5, Math.ceil(-1.5)); // �ø�
		System.out.printf("floor(%3.1f)=%f%n", -1.5, Math.floor(-1.5)); // ����
	}

}
