//package D0718;
//
//import java.util.ArrayList;
//
//public class FruitBoxEx1 {
//
//	public static void main(String[] args) {
//		Box<Fruit> fruitBox = new Box<Fruit>();
//		Box<Apple> appleBox = new Box<Apple>();
//		Box<Toy> toyBox = new Box<Toy>();
//		Box<Toy> toyBox2 = new Box<>(); //생성자에 타입생략가능, j아 1.7
//		//Box<Grape> grapeBox = new Box<Apple>(): //에러, 타입 불일치, 생성시에는 타입이 일치해야 한다.
//		
//		fruitBox.add(new Fruit());
//		fruitBox.add(new Apple()); //OK, void add(Fruit item), Apple는 Fruit의 자식
//		
//		appleBox.add(new Apple());
//		appleBox.add(new Apple());
//		//appleBox.add(new Toy()); //에러, Box<Apple>에는 Apple만 담을 수 있음
//		
//		toyBox.add(new Toy());
//		//toyBox.add(new Apple()); //에러, Box<Toy>에는 Apple을 담을 수 없음
//		
//		System.out.println(fruitBox);
//		System.out.println(appleBox);
//		System.out.println(toyBox);
//	}
//
//}
//
//class Box<T> {
//	ArrayList<T> list = new ArrayList<T>();
//	void add(T item) {
//		list.add(item);
//	}
//	int size() {
//		return list.size();
//	}
//	public String toString() {
//		return list.toString();
//	}
//}
//
//class Fruit {
//	public String toString() {
//		return "Fruit";
//	}
//}
//
//class Apple extends Fruit {
//	public String toString() {
//		return "Apple";
//	}
//}
//
//class Grape extends Fruit {
//	public String toString() {
//		return "Grape";
//	}
//}
//
//class Toy {
//	public String toString() {
//		return "Toy";
//	}
//}