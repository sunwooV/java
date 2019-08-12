package D0723;

import java.util.ArrayList;
import java.util.Collections;

//Order �ڽĸ� ��� ����
class Customer <T extends Order> implements Comparable<Customer> {
	private String name;
	private int age;
	private int point;
	private ArrayList<T> orders = new ArrayList<>();
	
	Customer(String name, int age, int point){
		this.name = name;
		this.age = age;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String location) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public ArrayList<T> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList orders) {
		this.orders = orders;
	}

	public void addOrders(T o) {
		orders.add(o);
	}
	
	@Override
	public int compareTo(Customer obj) {
		int thisTotal = point; //�Ŀ� ���� ����
		int total=0; //���� ���� ����
		if(obj instanceof SpecialCustomer) {
			total = obj.getPoint() + ((SpecialCustomer) obj).getBonus();
		}
		else {
			total = obj.getPoint();
		}
		
		if(total < thisTotal) { //obj < this
			return -1;
		}
		else if(total > thisTotal) { //obj > this
			return 1;
		}
		return 0;
		
	}
	
	public String toString() {
		return name + "-" + point;
	}
}

class SpecialCustomer<SpecialOrder> extends Customer {
	private int bonus;

	SpecialCustomer(String name, int age, int point, int bonus) {
		super(name, age, point);
		this.bonus = bonus;
	}
	
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	public void addOrders(SpecialOrder o) {
		if(o instanceof SpecialCustomer) {
			int result = ((SpecialCustomer) o).getPoint() + ((SpecialCustomer) o).getBonus();
		}
		getOrders().add(o);
	}

	@Override
	public int compareTo(Customer obj) {
		int thisTotal = super.getPoint() + bonus;
		int total=0;
		if(obj instanceof SpecialCustomer) {
			
			total = obj.getPoint() + ((SpecialCustomer) obj).getBonus();
		}
		else {
			total = obj.getPoint();
		}
		
		if(total < thisTotal) {
			return -1;
		}
		else if(total > thisTotal) {
			return 1;
		}
		return 0;
		
	}
	
	public String toString() {
		return super.getName() + "-" + (super.getPoint() + bonus);
	}
	
}

class Order {
	private int orderNo;
	private String product;
	private String orderDt;
	
	Order(int orderNo, String product, String orderDt){
		this.orderNo = orderNo;
		this.product = product;
		this.orderDt = orderDt;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(String orderDt) {
		this.orderDt = orderDt;
	}
}

class SpecialOrder extends Order {
	private String gift; //����ǰ

	SpecialOrder(int orderNo, String product, String orderDt, String gift) {
		super(orderNo, product, orderDt);
		this.gift = gift;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}
	
}

public class Practice1 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Customer<Order> c1 = new Customer<>("ȫ�浿", 30, 100);
		Order o = new Order(1234, "������ȣ��", "2019-07-01");
		c1.addOrders(o);

		SpecialCustomer<SpecialOrder> c2 = new SpecialCustomer<>("��浿", 31, 150, 50);
		SpecialOrder so = new SpecialOrder(1234, "������ȣ��", "2019-07-01", "��Ƽ��");
		c2.addOrders(so);
		
		Customer<Order> c3 = new Customer<>("�ű浿", 30, 180);
		
		ArrayList al = new ArrayList();
		al.add(c1);
		al.add(c2);
		al.add(c3);
		
		//Point�� Customer ����, SpecialCustomer�� bonus�� �����ش�.
		Collections.sort(al); //��������
		
		System.out.println(al.toString());
	}

}
