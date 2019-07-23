package D0718;

public class RunnableTest {
	
	public static void main(String[] args) {
		//Runnable interface를 implements한 class를 인자로 넣어서 Thread 생성
		Thread primeThread = new Thread(new PrimeThread(30));
		System.out.println("PrimeThread:" + primeThread);
		primeThread.setName("PrimeThread");
		System.out.println("PrimeThread:" + primeThread);
		primeThread.start(); //run()메소드 실행
	}

}

class PrimeThread implements Runnable {
	int number;
	String name;
	PrimeThread(int n){
		name = null;
		number = n;
	}
	PrimeThread(String s, int n){
		name = s;
		number = n;
	}
	public void run() {
		int n = 3;
		while(n<number) {
			if(isPrimeNumber(n)) {
				System.out.println(name + ":" + n+"is prime number");
			}
			n++;
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {}
		}
	}
	public boolean isPrimeNumber(int n) {
		int i;
		for(i=2;i<=(n/2);i++) {
			if((n%i) == 0)
				return false;
		}
		return true;
	}
}