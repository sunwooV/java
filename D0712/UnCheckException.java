package D0712;

public class UnCheckException {

	public static void main(String[] args) {
		UnCheckTest uck = new UnCheckTest();
		uck.test();
		try { //�ݵ�� ���� �ʾƵ� ������ ���� �ʴ´�.
			uck.test2();
		} catch (Exception e) {
			e.printStackTrace();
		} //ERROR �߻�
	}

}

class UnCheckTest{
	//ó���� �Ұ����ϰų� ȣ���ڿ��� ���� ó�� ������ �������� �ְ��� �� ��� ���
	public void test() throws RuntimeException{
		char[] a = new char[2];
		a[0] = 'a';
		a[1] = 'b';
		a[2] = 'c'; 
	}
	//��� ���α׷��Ӱ� ó���ؾ� �� ������ ��� ���
	public void test2() throws Exception{
		char[] a = new char[2];
		a[0] = 'a';
		a[1] = 'b';
		a[2] = 'c'; 
	}
}