package D0718;

class ThreadSample extends Thread{
	int number;
	ThreadSample(int n){
		super();
		number = n;
	}
	
	ThreadSample(String s, int n){
		super(s);
		number = n;
	}
	
	public void run() {
		int n = 3;
		while(n < number) {
			if(isPrimeNumber(n)) {
				System.out.println(getName() + ":" + n + "is prime number");
			}
			n++;
			try{
				sleep(100);
				
			} catch(InterruptedException e) {
			}
		}
	}
	
	public boolean isPrimeNumber(int n) {
		int i;
		
		for(i=2;i<=(n/2);i++) {
			if((n%i)==0)
				return false;
		}
		return true;
	}

}

public class ThreadTest {
	public static void main(String[] args) {
		ThreadSample primeThread = new ThreadSample(30);
		
		System.out.println("PrimeThread:" + primeThread);
		primeThread.setName("PrimeThread");
		System.out.println("Prime Thread:" + primeThread);
		primeThread.start();

	}
}