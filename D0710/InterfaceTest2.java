package D0710;

interface interA2{
	void aaa();
	public abstract void bbb();
}

class interB2 implements interA2{
	//A2�� aaa��, public abstract�̹Ƿ� public���� �����ؾ� ��
	//�ڽ��� �θ𺸴� ���� ���������ڸ� ������ �Ѵ�.
	public void aaa() {
		System.out.println("aaa �޼���");
	}
	
	public void bbb() {
		System.out.println("bbb �޼���");
	}
}



public class InterfaceTest2 {

	public static void main(String[] args) {
		interB2 bp = new interB2();
		bp.aaa();
		bp.bbb();
	}

}
