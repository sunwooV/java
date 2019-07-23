package Project;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Vender{
	private String name;

	Vender(String name){
		this.name = name;
	}
	
	public void ordered() {
		System.out.println("��ǰ �ֹ��� Ȯ���߽��ϴ�.");
	}
	
	public void send(Customers c) {
		System.out.println(c.getName() + " ���Բ� ��ǰ�� ���½��ϴ�.");
	}
	
}

class Product{
	private int number;
	private String name;
	private int price;
	Vender vender;
	
	public Product(int number, String name, int price, Vender vender) {
		this.number = number;
		this.name = name;
		this.price = price;
		this.vender = vender;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Vender getVender() {
		return vender;
	}
	public void setVender(Vender vender) {
		this.vender = vender;
	}
	//��ǰ ���� ���
	public void printstock(){
		System.out.println("��ǰ��ȣ:" + this.number + ", ��ǰ�̸�:" + this.name + ", ��ǰ����:" + this.price + "��");
	}
	public boolean checkstock() {
		return true;
	}

	
}

class Order{
	private int quantity;
	private String pay;
	Product p;
	HashMap orderinfo = new HashMap(); 
	
	Order(Product p, int quantity, String pay){
		this.p = p;
		this.quantity = quantity;
		this.pay = pay;
	}
	

	public void paying() {
		System.out.println("��������:" + pay + ", �ѱݾ�:" + p.getPrice()*this.quantity + "�� �����Ǿ����ϴ�.");
				
	}
	
	public Product getP(Product p) {
		return p;
	}

	@SuppressWarnings("unchecked")
	public void addOrder(Order order) {
		String strinfo = order.p.getName() + order.p.getPrice();
		orderinfo.put(order.p.getNumber(), strinfo);
	}
	
	public void printOrder() {
		Set set = orderinfo.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("id = " + e.getKey());
		}
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Order) {
			return p.equals(((Order)obj).getP(p));
		}
		else return false;
	}
	
	public int hashCode() {
		return Objects.hash(p);
	}
	
}

class Customers{
	private String name;
	private String address;
	private String phonenumber;
	HashMap info = new HashMap();
	Order order;
	//Join j; //���̵�, �н����� ����
	
	Customers(String name, String address, String phonenumber){
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		this.order = order;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	//ȸ������
	public void join(String id, String pw) throws Exception {
		if(info.containsKey(id)) {
			throw new Exception("id�� �ߺ��Ǿ����ϴ�.");
		}
		else {
			info.put(id, pw);
		}
	}
	//ȸ������ ���
	public void printCustomers(){
		System.out.println("�̸�:" + this.name + ", �ּ�:" + this.address + ", ��ȭ��ȣ:" + this.phonenumber );
	}
	
}

public class venderTest {

	public static void main(String[] args) throws Exception {
		Vender v = new Vender("���Ż�");
		Product p = new Product(100, "Ƽ����", 10000, v);
		
		Order order = new Order(p, 10, "credit");
		
		Customers c = new Customers("ȫ�浿", "����� ������", "010-0000-0000");
		try {
			c.join("aaa", "www");
			//c.join("aaa", "www"); //HashMap Ű ��(id) �ߺ� x
			c.printCustomers();
			c.order.addOrder(order);
			c.order.paying();
			c.order.p.printstock();
			c.order.p.vender.ordered();
			c.order.p.vender.send(c);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
