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
//		FruitBox<Fruit2> fruitBox = new FruitBox<Fruit2>(); //Eatable�� �ڽ�
//		FruitBox<Apple> appleBox = new FruitBox<Apple>(); //Fruit�� �ڽ�
//		FruitBox<Grape> grapeBox = new FruitBox<Grape>(); //Fruit�� �ڽ�
//		//FruitBox<Grape> grapeBox = new FruitBox<Apple>(); //����, Ÿ�� ����ġ
//		//FruitBox<Toy> toyBox = new FruitBox<Toy>(); //���� //��� ���� �ʾƼ�
//		
//		fruitBox.add(new Fruit2());
//		fruitBox.add(new Apple());
//		fruitBox.add(new Grape());
//		
//		appleBox.add(new Apple());
//		//appleBox.add(new Grape());//����, Grape�� Apple�� �ڼ��� �ƴ�
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