package D0812;

import java.util.ArrayList;
import java.util.HashMap;

class UsedSite {
	private String sitename;
	private String URL;
	private boolean bcheckpay;// 지불여부

	UsedSite(String sitename, String URL) {
		this.sitename = sitename;
		this.URL = URL;
	}

	// 아이템출력
	public void printItem(Item item) {

	}

	// 장바구니아이템출력
	public void shoppingBasket(Item item) {

	}

	// 입금확인
	public void depositcheck(Buyer buyer) {
		if (bcheckpay) {
			System.out.println("입금이 완료되었습니다.");
		}
	}
	//buyer가 물건을 받았다는 확인
	public boolean checkitem() {
		return true;
	}
	//
	public boolean getBcheckpay() {
		return bcheckpay;
	}

	public void setBcheckpay(boolean bcheckpay) {
		this.bcheckpay = bcheckpay;
	}
	

}

class UserManage {

	private HashMap users = new HashMap();

	// 회원가입 메소드
	public void joinMember(User user) {
		certify(user);
		users.put(user.getId(), user.getPwd());
	}

	// 비밀번호수정메소드
	public void modifyMember(User user, String pwd) {
		users.replace(user.getId(), pwd);
	}

	// 회원삭제메소드
	public void deleteMember(User user) {
		users.remove(user.getId());
	}

	// 회원정보출력
	public void printMember(User user) {

	}

	// 파는사람이면 인증을한다
	public void certify(User user) {
		if (user instanceof Seller) {
			// 인증한다.
		}
	}

}

class User {
	private String name;
	private String jumin;
	private String phonenumber;
	private String adderss;
	private String email;
	private String id;
	private String pwd;
	public  UsedSite us;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJumin() {
		return jumin;
	}

	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAdderss() {
		return adderss;
	}

	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}

class Seller extends User {
	private ArrayList items = new ArrayList();
	private String account;


	// 파는물건 등록
	public void addItem(Item item) {
		items.add(item);
	}

	// 파는물건 수정
	public void modifyItem(Item item) {

	}

	// 파는물건 삭제
	public void deleteItem(Item item) {
		items.remove(item);
	}

	// 물품을 사는 사람에게 보냄
	public boolean sendItem() {
		// 입금확인후 물건보냈으면 true,아니면 false
		if(checkpay()) {
			return true;
		}
		return false;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	// 입금확인하는 메소드
	public boolean checkpay() {
		//사는사람이 돈을 냈으면
		if(us.getBcheckpay()) {
			return true;
		}
		//아니면 false
		else {
			return false;
		}
	}
}

class Buyer extends User {
	
	private ArrayList basket = new ArrayList();

	// 장바구니에물건추가
	public void addshoppingBasket(Item item) {
		basket.add(item);

	}

	// 장바구니에 물건 삭제
	public void deleteshoppingBakset(Item item) {
		basket.remove(item);

	}

	// 물건을 산다.
	public void buyItem(int quantity, Item item) {

	}

	// 결제방법선택
	public void payment() {

	}

	// 입금확인하는 메소드
	public void checkpay() {
		//돈을 냈으면
		us.setBcheckpay(true);
		//아니면 false
		us.setBcheckpay(false);
	}

	// 배송방법선택
	public void delivery() {

	}

	// 물건을 받다
	public void receiveItem() {

	}

	// 배송조회
	public void printdeliveryinfo(Seller seller) {
		if (seller.sendItem()) {
			System.out.println("출고완료");
		}
	}
}

class Item {
	private String name;
	private int price;

	Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
}

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
