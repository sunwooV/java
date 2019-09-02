package D0725;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

//Comparator Order Ŭ���� ������
//HashMap value ���� list�ȿ� value�� �־ ����

class Order2 {
	private int orderNo;
	private Date orderDt;
	private HashMap products; //�ֹ� ��ǰ
	
	Order2(int orderNo, Date orderDt){
		this.orderNo = orderNo;
		this.orderDt = orderDt;
		products = new HashMap();
	}
	
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(Date orderDt) {
		this.orderDt = orderDt;
	}

	public HashMap getProducts() {
		return products;
	}

	public void setProducts(HashMap products) {
		this.products = products;
	}
	
	//�ֹ���ǰ�� �߰��Ѵ�.
	public void addProduct(int ordProdNo, Product2 p) {
		products.put(ordProdNo, p);
	}
	
	//�ֹ���ǰ�� �����Ѵ�.
	public void delProduct(int ordProdNo) {
		products.remove(ordProdNo);
	}
	
	//�ֹ���ǰ�� �����Ѵ�.
	public void chgProduct(int ordProdNo, Product2 p) {
		products.replace(ordProdNo, p);
		
	}
	
	//�ֹ���ǰ�� Ű�� �������� �����Ͽ� ����Ѵ�.
	public void printKeyBySort() {
		TreeMap tm = new TreeMap(products);
		Iterator iteratorKey = tm.keySet().iterator(); //Ű�� �������� ����
		
		System.out.println();
        System.out.println("=== key(�ֹ��� ��ǰ��ȣ) ���� ���� �� ===");
		
		while(iteratorKey.hasNext()) {
			int key = (int) iteratorKey.next();
			System.out.println("[" + key + "] " + ((Product2)tm.get(key)).getProductNo() + ", " + ((Product2)tm.get(key)).getProductNm());
		}
	}
	
	//�ֹ���ǰ�� Value�� �������� �����Ͽ� ����Ѵ�.
	//Value�� ��ǰ��ȣ�� �������� �����Ѵ�.
	@SuppressWarnings("unchecked")
	public void printValueBySort(Comparator c) {
		List list = new ArrayList();
        list.addAll(products.keySet());
         
        Collections.sort(list, c);
        
        System.out.println();
        System.out.println("=== value(��ǰ��ȣ) ���� ���� �� ===");
        
		int sum = list.size();
		for(int i=0;i<sum;i++) {
			Object v1 = products.get(list.get(i)); //list���� key���� ����Ǿ������Ƿ� value�� �������� ����
			System.out.println(((Product2)v1).getProductNo() + ", " + ((Product2)v1).getProductNm());
		}

	}
	
	class valueCompare implements Comparator {

		public int compare(Object o1,Object o2){
	        Object v1 = products.get(o1);
	        Object v2 = products.get(o2);
	        
	        int result = ((Comparable<Integer>) ((Product2)v1).getProductNo()).compareTo(((Product2)v2).getProductNo());
	        return result;
	    }
	}
	
}


class Product2 {
	private int productNo; //��ǰ��ȣ
	private String productNm; //��ǰ�̸�
	
	Product2(int productNo, String productNm){
		this.productNo = productNo;
		this.productNm = productNm;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}

	
}


public class Practice1_2 {
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");

		Order2 order = new Order2(1010, format1.parse("2019-07-25"));
		Product2 p = new Product2(123456, "�Һ�");
		Product2 p2 = new Product2(23456, "����");
		Product2 p3 = new Product2(36985, "�Ȱ�");
		Product2 p4 = new Product2(45678, "å");
		
		Comparator vc = order.new valueCompare(); //���� ����
		
		order.addProduct(1, p);
		order.addProduct(2, p2);
		order.addProduct(3, p4);
		
		order.printKeyBySort();
		
		order.delProduct(3);
		System.out.println("å -> ����");
		
		order.printKeyBySort();
		
		order.chgProduct(1, p3);
		System.out.println("�Һ� -> �Ȱ� ����");
		
		order.printKeyBySort();
		
		order.printValueBySort(vc);

	}

}
