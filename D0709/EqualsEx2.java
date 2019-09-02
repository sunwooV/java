package D0709;

class Person2{
	long id;
	//Object�� equals�� Overriding
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Person2) { //obj�� Person2�� Instance
			return id == ((Person2)obj).id; //���� ������ ��
		}else {
			return false;
		}
	}
	
	Person2(long id){
		this.id = id;
	}
}
public class EqualsEx2 {

	public static void main(String[] args) {
		Person2 p1 = new Person2(8011081111222L);
		Person2 p2 = new Person2(8011081111222L);
		
		if(p1==p2) //�ٸ���
			System.out.println("p1�� p2�� ���� ����Դϴ�.");
		else
			System.out.println("p1�� p2�� �ٸ� ����Դϴ�.");
		
		if(p1.equals(p2)) //����
			System.out.println("p1�� p2�� ���� ����Դϴ�.");
		else
			System.out.println("p1�� p2�� �ٸ� ����Դϴ�.");

	}

}
