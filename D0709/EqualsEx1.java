package D0709;

public class EqualsEx1 {

	public static void main(String[] args) {
		Value v1 = new Value(10);
		Value v2 = new Value(10);
		
		if(v1.equals(v2)) { //다르다 => v1과 v2 모두 새로운 Value를 생성했기 때문이다.
			System.out.println("v1과 v2는 같습니다.");
		}
		else {
			System.out.println("v1과 v2는 다릅니다.");
		}
		
		v2 = v1;
		
		if(v1.equals(v2)) {
			System.out.println("v1과 v2는 같습니다.");
		}else {
			System.out.println("v1과 v2는 다릅니다.");
		}

	}//main

}

class Value{
	int value;
	
	Value(int value){
		this.value = value;
	}
}