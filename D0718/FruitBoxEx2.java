//package D0718;
//
//import java.util.ArrayList;
//
//interface Eatable {}
//
//class Fruit2 implements Eatable {
//	public String toString() {
//		return "Fruit";
//	}
//}
//
//class Box2<T> {
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
//class FruitBox<T extends Fruit2 & Eatable> extends Box2<T> {}
//
//public class FruitBoxEx2 {
//
//	public static void main(String[] args) {
//		FruitBox<Fruit2> fruitBox = new FruitBox<Fruit2>(); //Eatable의 자식
//		FruitBox<Apple> appleBox = new FruitBox<Apple>(); //Fruit의 자식
//		FruitBox<Grape> grapeBox = new FruitBox<Grape>(); //Fruit의 자식
//		//FruitBox<Grape> grapeBox = new FruitBox<Apple>(); //에러, 타입 불일치
//		//FruitBox<Toy> toyBox = new FruitBox<Toy>(); //에러 //상속 받지 않아서
//		
//		fruitBox.add(new Fruit2());
//		fruitBox.add(new Apple());
//		fruitBox.add(new Grape());
//		
//		appleBox.add(new Apple());
//		//appleBox.add(new Grape());//에러, Grape는 Apple의 자손이 아님
//		
//		grapeBox.add(new Grape());
//		
//		System.out.println("fruitBox - " + fruitBox);
//		System.out.println("appleBox - " + appleBox);
//		System.out.println("grapeBox - " + grapeBox);
//	}
//
//}
//
//class Apple extends Fruit2 {
//	public String toString() {
//		return "Apple";
//	}
//}
//
//class Grape extends Fruit2 {
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