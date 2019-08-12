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
	private LinkedList foods; //주문음식
	private ArrayList serviceFoods; //제공된 음식
	
	AutoFood(int orderFoodNo, Date orderDt){
		this.orderFoodNo = orderFoodNo;
		this.orderDt = orderDt;
		foods = new LinkedList();
		serviceFoods = new ArrayList();
	}
	
	//음식을 주문한다.
	//주문 시 조리시간을 기준으로 정렬(조리시간이 짧은 음식을 상위로)
	//주문된 음식유형(클래스)별로 총 주문 수량을 count한다.
	public void orderFood(Food f) {
		
		foods.add(f);
		f.counting(); //주문한 수량 count
		
		Comparator tc = (Comparator) new TimeComparing();
		Collections.sort(foods, tc);
	}
	
	//주문된 음식을 출력한다.
	public void printOrderFood() {
		Iterator it = foods.iterator();
		System.out.println("\n=== 주문 시간 빠른 순 ===");
		while(it.hasNext()) {
			Food food = (Food) it.next();
			System.out.print("[" + food.getFoodNo() + "] " + food.getFoodNm() + " - " + food.getTime() + "분 ");
		}
		System.out.println();
	}
	
	//주문음식에서 삭제하고 제공된 음식으로 이동시킨다.
	public void serviceFood(Food f) {
		
		foods.remove(f);
		serviceFoods.add(f);
	}
	
	//음식유형(클래스)별로 총 주문 수량을 출력한다.
	public void printTotalCount() {
		System.out.println("\n===== 총 판매 수량 =====");
		System.out.println("Noodle : " + Noodle.count + " 그릇");
		System.out.println("FriedRice : " + FriedRice.count + " 그릇");
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
	private int foodNo; //음식 번호
	private String foodNm; //음식 이름
	private int time; //조리시간
	
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
	static int count = 0; //총 그릇 수
	
	Noodle(int foodNo, String foodNm, int time) {
		super(foodNo, foodNm, time);

	}
	
	public void counting() {
		count++;
	}
	
}

class FriedRice  
extends Food {
	static int count = 0; //총 그릇 수

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
		
		Food f = new Noodle(1, "탄탄면", 10);
		Food f2 = new Noodle(2, "짜장면", 5);
		
		Food f3 = new FriedRice(3, "새우 볶음밥", 15);
		
		af.orderFood(f3);
		af.orderFood(f2);
		af.orderFood(f);
		
		af.printOrderFood();
		
		af.serviceFood(f2);
		System.out.println(" -> 짜장면 서비스 완료");
		
		af.printOrderFood();
		
		af.printTotalCount();

	}

}
