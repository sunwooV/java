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

//Comparator class 따로 만들어서 함.
//HashMap value 정렬 list안에 key값 넣어서 정렬

class Order {
	private int orderNo;
	private Date orderDt;
	private HashMap products; //주문 상품
	
	Order(int orderNo, Date orderDt){
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
	
	//주문상품을 추가한다.
	public void addProduct(int ordProdNo, Product p) {
		products.put(ordProdNo, p);
	}
	
	//주문상품을 삭제한다.
	public void delProduct(int ordProdNo) {
		products.remove(ordProdNo);
	}
	
	//주문상품을 변경한다.
	public void chgProduct(int ordProdNo, Product p) {
		products.replace(ordProdNo, p);
		
	}
	
	//주문상품을 키를 기준으로 정렬하여 출력한다.
	public void printKeyBySort() {
		TreeMap tm = new TreeMap(products);
		Iterator iteratorKey = tm.keySet().iterator(); //키값 오름차순 정렬
		
		System.out.println();
        System.out.println("=== key(주문별 제품번호) 기준 정렬 후 ===");
		
		while(iteratorKey.hasNext()) {
			int key = (int) iteratorKey.next();
			System.out.println("[" + key + "] " + ((Product)tm.get(key)).getProductNo() + ", " + ((Product)tm.get(key)).getProductNm());
		}
	}
	
	//주문상품을 Value를 기준으로 정렬하여 출력한다.
	//Value는 제품번호를 기준으로 정렬한다.
	@SuppressWarnings("unchecked")
	public void printValueBySort(Comparator c) {
		List list = new ArrayList();
        list.addAll(products.values());
         
        Collections.sort(list, c);
        
        System.out.println();
        System.out.println("=== value(제품번호) 기준 정렬 후 ===");
        
		int sum = list.size();
		for(int i=0;i<sum;i++) {
			System.out.println(((Product)list.get(i)).getProductNo() + ", " + ((Product)list.get(i)).getProductNm());
		}

	}
	
}

class valueCompare implements Comparator<Product> {

	public int compare(Product o1,Product o2){
        
        int result = ((Comparable<Integer>) ((Product)o1).getProductNo()).compareTo(((Product)o2).getProductNo());
        return result;
    }
}


class Product {
	private int productNo; //제품번호
	private String productNm; //제품이름
	
	Product(int productNo, String productNm){
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


public class Practice1_1 {
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");

		Order order = new Order(1010, format1.parse("2019-07-25"));
		Product p = new Product(123456, "텀블러");
		Product p2 = new Product(23456, "필통");
		Product p3 = new Product(36985, "안경");
		Product p4 = new Product(45678, "책");
		
		Comparator vc = new valueCompare(); //정렬 기준
		
		order.addProduct(1, p);
		order.addProduct(2, p2);
		order.addProduct(3, p4);
		
		order.printKeyBySort();
		
		order.delProduct(3);
		System.out.println("책 -> 삭제");
		
		order.printKeyBySort();
		
		order.chgProduct(1, p3);
		System.out.println("텀블러 -> 안경 변경");
		
		order.printKeyBySort();
		
		order.printValueBySort(vc);

	}

}
