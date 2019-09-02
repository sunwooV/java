package D0709;

class Car{
	String color; //색상
	String gearType; //변속기 종류 - auto(자동), manual(수동)
	int door; //문의 개수
	
	Car(){
		this("white", "auto", 4);
		
	}
	
	Car(Car c){ //인스턴스의 복사를 위한 생성자
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
	
	public boolean equals(Object obj) { //equals 재정의 부분 //어떤 자료형이 올지 모르기 때문에 Object로 정의
		
		return (color == ((Car)obj).color) && (gearType == ((Car)obj).gearType) && (door == ((Car)obj).door); //true
	}
	
}

public class CarTest3 {
 
	public static void main(String[] args) {
		Car c1 = new Car();
		Car c2 = new Car(c1); //c1의 복사본 c2를 생성한다.
		System.out.println(c1.door);
		System.out.println(c2.door);
		Object o = c1;
		//c2 = o;

		System.out.println(c1.equals(c2)); //equals를 재정의하면 true(c2는 c1의 복사본이기 때문), 아니면 false
	}

}
