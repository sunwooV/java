package D0709;

//���� Ŭ����
public class InnerEx1 {
	int m = 0;
	class InstanceInner{
		int iv = 100;
		int t = m; //Outer�� ������� ���ٰ���
		//static int cv = 100; //����! static ������ ������ �� ����.
		final static int CONST = 100;
		
	}
	static class StaticInner{
		int iv = 200;
		//int t = m; //���� �߻�
		static int cv = 200; //static Ŭ������ static ����� ������ �� �ִ�.
	}
	void myMethod() {
		class LocalInner{
			int iv = 300;
			int t = m; //Outer�� ������� ���ٰ���
			//static int cv = 300; //����! static ������ ������ �� ����.
			final static int CONST = 300; //final static�� ����̹Ƿ� ���
		}
	}
	public static void main(String[] args) {
		System.out.println(InstanceInner.CONST);
		System.out.println(StaticInner.cv);

	}

}
