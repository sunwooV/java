package D0712;

public class ExactMethod {

	public static void main(String[] args) {
		int i = Integer.MIN_VALUE;
		
		System.out.println("i = " + i);
		System.out.println("-i = "+ (-i));
		
		try {
			//���ۿ����÷ο� �� ���ܹ߻�
			System.out.printf("negateExact(%d) = %d\n", 10, Math.negateExact(10));
			System.out.printf("negateExact(%d) = %d\n", -10, Math.negateExact(-10));
			System.out.printf("negateExact(%d) = %d\n", i, Math.negateExact(i)); //���ܹ߻�

		} catch(ArithmeticException e) {
			//i�� longŸ������ ����ȯ ������ negateExact(long a)�� ȣ��
			System.out.printf("negateExact(%d) = %d\n", (long)i, Math.negateExact((long)i));
		}
		
		
	}

}
