package D0712;

public class FinallyTest3 {

	public static void main(String[] args) {
		//method1()�� static �޼����̹Ƿ� �ν��Ͻ� �������� ���� ȣ��
		FinallyTest3.method1();
		System.out.println("method1()�� ������ ��ġ�� main�޼���� ����");

	}
	static void method1() {
		try {
			System.out.println("method1()�� ȣ��Ǿ����ϴ�.");
			return; //���� ���� ���� �޼��带 �����Ѵ�.
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("���� �޽��� : " + e.getMessage());
		} finally {
			System.out.println("method1()�� finally���� ����Ǿ����ϴ�.");
		}
	}

}
