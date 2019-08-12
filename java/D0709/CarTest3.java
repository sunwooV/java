package D0709;

class Car{
	String color; //����
	String gearType; //���ӱ� ���� - auto(�ڵ�), manual(����)
	int door; //���� ����
	
	Car(){
		this("white", "auto", 4);
		
	}
	
	Car(Car c){ //�ν��Ͻ��� ���縦 ���� ������
		color = c.color;
		gearType = c.gearType;
		door = c.door;
	}
	
	Car(String color, String gearType, int door){
		this.color = color;
		this.gearType = gearType;
		this.door = door;
	}
	
//	public boolean equals(Object obj) {
//		return color == ((Car)obj).color;
//	}
	
	public boolean equals(Object obj) { //equals ������ �κ� //� �ڷ����� ���� �𸣱� ������ Object�� ����
		
		return (color == ((Car)obj).color) && (gearType == ((Car)obj).gearType) && (door == ((Car)obj).door); //true
	}
	
}

public class CarTest3 {
 
	public static void main(String[] args) {
		Car c1 = new Car();
		Car c2 = new Car(c1); //c1�� ���纻 c2�� �����Ѵ�.
		System.out.println(c1.door);
		System.out.println(c2.door);
		Object o = c1;
		//c2 = o;

		System.out.println(c1.equals(c2)); //equals�� �������ϸ� true(c2�� c1�� ���纻�̱� ����), �ƴϸ� false
	}

}
