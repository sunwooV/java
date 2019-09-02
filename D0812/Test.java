package D0812;

import java.util.ArrayList;
import java.util.HashMap;

class UsedSite {
	private String sitename;
	private String URL;
	private boolean bcheckpay;// ���ҿ���

	UsedSite(String sitename, String URL) {
		this.sitename = sitename;
		this.URL = URL;
	}

	// ���������
	public void printItem(Item item) {

	}

	// ��ٱ��Ͼ��������
	public void shoppingBasket(Item item) {

	}

	// �Ա�Ȯ��
	public void depositcheck(Buyer buyer) {
		if (bcheckpay) {
			System.out.println("�Ա��� �Ϸ�Ǿ����ϴ�.");
		}
	}
	//buyer�� ������ �޾Ҵٴ� Ȯ��
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

	// ȸ������ �޼ҵ�
	public void joinMember(User user) {
		certify(user);
		users.put(user.getId(), user.getPwd());
	}

	// ��й�ȣ�����޼ҵ�
	public void modifyMember(User user, String pwd) {
		users.replace(user.getId(), pwd);
	}

	// ȸ�������޼ҵ�
	public void deleteMember(User user) {
		users.remove(user.getId());
	}

	// ȸ���������
	public void printMember(User user) {

	}

	// �Ĵ»���̸� �������Ѵ�
	public void certify(User user) {
		if (user instanceof Seller) {
			// �����Ѵ�.
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


	// �Ĵ¹��� ���
	public void addItem(Item item) {
		items.add(item);
	}

	// �Ĵ¹��� ����
	public void modifyItem(Item item) {

	}

	// �Ĵ¹��� ����
	public void deleteItem(Item item) {
		items.remove(item);
	}

	// ��ǰ�� ��� ������� ����
	public boolean sendItem() {
		// �Ա�Ȯ���� ���Ǻ������� true,�ƴϸ� false
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
	// �Ա�Ȯ���ϴ� �޼ҵ�
	public boolean checkpay() {
		//��»���� ���� ������
		if(us.getBcheckpay()) {
			return true;
		}
		//�ƴϸ� false
		else {
			return false;
		}
	}
}

class Buyer extends User {
	
	private ArrayList basket = new ArrayList();

	// ��ٱ��Ͽ������߰�
	public void addshoppingBasket(Item item) {
		basket.add(item);

	}

	// ��ٱ��Ͽ� ���� ����
	public void deleteshoppingBakset(Item item) {
		basket.remove(item);

	}

	// ������ ���.
	public void buyItem(int quantity, Item item) {

	}

	// �����������
	public void payment() {

	}

	// �Ա�Ȯ���ϴ� �޼ҵ�
	public void checkpay() {
		//���� ������
		us.setBcheckpay(true);
		//�ƴϸ� false
		us.setBcheckpay(false);
	}

	// ��۹������
	public void delivery() {

	}

	// ������ �޴�
	public void receiveItem() {

	}

	// �����ȸ
	public void printdeliveryinfo(Seller seller) {
		if (seller.sendItem()) {
			System.out.println("���Ϸ�");
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
