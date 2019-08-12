package D0726;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

class AutoFood {
	private int orderFoodNo;
	private Date orderDt;
	private LinkedList foods; //�ֹ�����
	private ArrayList serviceFoods; //������ ����
	
	AutoFood(int orderFoodNo, Date orderDt){
		this.orderFoodNo = orderFoodNo;
		this.orderDt = orderDt;
		foods = new LinkedList();
		serviceFoods = new ArrayList();
	}
	
	//������ �ֹ��Ѵ�.
	//�ֹ� �� �����ð��� �������� ����(�����ð��� ª�� ������ ������)
	//�ֹ��� ��������(Ŭ����)���� �� �ֹ� ������ count�Ѵ�.
	public void orderFood(Food f) {
		
		foods.add(f);
		f.counting(); //�ֹ��� ���� count
		
		Comparator tc = (Comparator) new TimeComparing();
		Collections.sort(foods, tc);
	}
	
	//�ֹ��� ������ ����Ѵ�.
	public void printOrderFood() {
		Iterator it = foods.iterator();
		System.out.println("\n=== �ֹ� �ð� ���� �� ===");
		while(it.hasNext()) {
			Food food = (Food) it.next();
			System.out.print("[" + food.getFoodNo() + "] " + food.getFoodNm() + " - " + food.getTime() + "�� ");
		}
		System.out.println();
	}
	
	//�ֹ����Ŀ��� �����ϰ� ������ �������� �̵���Ų��.
	public void serviceFood(Food f) {
		
		foods.remove(f);
		serviceFoods.add(f);
	}
	
	//��������(Ŭ����)���� �� �ֹ� ������ ����Ѵ�.
	public void printTotalCount() {
		System.out.println("\n===== �� �Ǹ� ���� =====");
		System.out.println("Noodle : " + Noodle.count + " �׸�");
		System.out.println("FriedRice : " + FriedRice.count + " �׸�");
	}
}

class TimeComparing implements Comparator<Food> {

	@Override
	public int compare(Food o1, Food o2) {
		int t1 = o1.getTime();
		int t2 = o2.getTime();
		
		if(t1 > t2) return 1;
		else if( t1 < t2) return -1;
		else return 0;
	}
	
}

abstract class Food {
	private int foodNo; //���� ��ȣ
	private String foodNm; //���� �̸�
	private int time; //�����ð�
	
	Food(int foodNo, String foodNm, int time){
		this.foodNo = foodNo;
		this.foodNm = foodNm;
		this.time = time;
	}

	public int getFoodNo() {
		return foodNo;
	}

	public void setFoodNo(int foodNo) {
		this.foodNo = foodNo;
	}

	public String getFoodNm() {
		return foodNm;
	}

	public void setFoodNm(String foodNm) {
		this.foodNm = foodNm;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public abstract void counting();
}

class Noodle extends Food {
	static int count = 0; //�� �׸� ��
	
	Noodle(int foodNo, String foodNm, int time) {
		super(foodNo, foodNm, time);

	}
	
	public void counting() {
		count++;
	}
	
}

class FriedRice  
extends Food {
	static int count = 0; //�� �׸� ��

	FriedRice(int foodNo, String foodNm, int time) {
		super(foodNo, foodNm, time);
	}
	
	public void counting() {
		count++;
	}
	
}

public class Practice1 {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
		AutoFood af = new AutoFood(100, sf.parse("2019-07-26"));
		
		Food f = new Noodle(1, "źź��", 10);
		Food f2 = new Noodle(2, "¥���", 5);
		
		Food f3 = new FriedRice(3, "���� ������", 15);
		
		af.orderFood(f3);
		af.orderFood(f2);
		af.orderFood(f);
		
		af.printOrderFood();
		
		af.serviceFood(f2);
		System.out.println(" -> ¥��� ���� �Ϸ�");
		
		af.printOrderFood();
		
		af.printTotalCount();

	}

}
