package D0719;

public class SyncThread {

	public static void main(String[] args) {
		User user = new User();
		
		//3���� ������ ��ü ����
		UserThread p1 = new UserThread(user, "A1");
		UserThread p2 = new UserThread(user, "B2");
		UserThread p3 = new UserThread(user, "C3");
		
		//������ �����ٸ� : �켱���� �ο�
		p1.setPriority(p1.MAX_PRIORITY);
		p2.setPriority(p2.NORM_PRIORITY);
		p3.setPriority(p3.MIN_PRIORITY);
		
		System.out.println("---------------");
		System.out.println("sychronized ������� ���");
		System.out.println("---------------");
		
		//������ ����
		p1.start();
		p2.start();
		p3.start();
	}

}

//Heap������ ��� ������ �������� ���
class User {
	private int userNo = 0;
	
	//�Ӱ� ������ �����ϴ� synchronized�޼ҵ�
	public synchronized void add(String name) {
		System.out.println(name + " : " + userNo++ + "��° ���");
	}
}

class UserThread extends Thread {
	User user;
	
	UserThread(User user, String name){
		super(name);
		this.user = user;
	}
	
	public void run() {
		try {
			for(int i=0;i<3;i++) {
				user.add(getName());
				sleep(500);
			}
		} catch(InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}