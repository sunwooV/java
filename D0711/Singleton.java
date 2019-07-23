package D0711;

public class Singleton {
	private Singleton() {}
	private static Singleton instance = null;
	public static synchronized Singleton getInstance() { //단 하나의 쓰레드만 이것을 실행할 수 있다. -> synchronized(동기화)
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}


}
