package D0712;

public class ExceptionEx8 {

	public static void main(String[] args) {
		System.out.println(1);
		System.out.println(2);
		try {
			System.out.println(3);
			System.out.println(0/0); //���ܹ߻�
			System.out.println(4); //������� �ʴ´�.
		} catch(ArithmeticException ae) {
			ae.printStackTrace();
			//�޼ҵ尡 ���������� ���� ����� ȭ�鿡 ����Ѵ�. 
			//printStackTrace�� ���� �ڼ��� ���� ������ �����Ѵ�.

			System.out.println("���ܸ޽��� : " + ae.getMessage());
			//������ ���� �⺻���� ������ ������ش�. ������ �ʴ�.

		}
		System.out.println(6);

	}

}
