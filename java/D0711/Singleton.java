package D0711;

public class Singleton {
	private Singleton() {}
	private static Singleton instance = null;
	public static synchronized Singleton getInstance() { //�� �ϳ��� �����常 �̰��� ������ �� �ִ�. -> synchronized(����ȭ)
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}


}
