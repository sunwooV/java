package D0717;

public class Test {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		System.out.println("(1) ���� ����Ʈ�� ��� 3�� �����ϱ�");
		list.insertLastNode("��");
		list.insertLastNode("��");
		list.insertLastNode("��");
		list.printList();
		
		System.out.println("(2) \"��\"��� �ڿ� \"��\" ��� �����ϱ�");
		ListNode pre = list.searchNode("��");
		
		if(pre == null) {
			System.out.println("�˻� ���� >> ã�� �����Ͱ� �����ϴ�.");
		}
		else {
			list.insertMiddleNode(pre, "��");
		}
		list.printList();
		
		System.out.println("(3) ����Ʈ�� ù ��°�� ��� �߰��ϱ�");
		list.insertFirstNode("��");
		list.printList();
		
		System.out.println("(4) ����Ʈ�� ������ ��� �����ϱ�");
		list.deleteLastNode();
		list.printList();
		
		System.out.println("(5) ����Ʈ�� �߰� ��� \"��\" �����ϱ�");
		list.deleteMiddleNode("��");
		list.printList();
		
		System.out.println("(6) ����Ʈ�� ù ��° ��� �����ϱ�");
		list.deleteFirstNode();
		list.printList();
		
		System.out.println("(7) ����Ʈ �������� ����ϱ�");
		list.reverseList();
		list.printList();
		
	}

}

class ListNode{
	String data;
	ListNode next;
	
	public ListNode() {
		this.data = null;
		this.next = null;
	}
	
	public ListNode(String data) {
		this.data = data;
		this.next = null;
	}
	
	public ListNode(String data, ListNode next) {
		this.data = data;
		this.next = next;
		this.next = null;
	}
	public String getData() {
		return this.data;
	}
}

class LinkedList {
	private ListNode head;
	
	public LinkedList() {
		this.head = null;
	}
	
	void insertLastNode(String str) {
		ListNode node = new ListNode(str);
		if(head == null) {
			head = node;
		}
		else {
			ListNode current = head;
			while(current.next != null) {
				current = current.next;
			}
			current.next = node;
			
		}
	}
	
	void insertMiddleNode(ListNode pre, String str) {
		ListNode node = new ListNode(str);
		if(head == null) {
			head = node;
		}
		else {
			ListNode current = head;
			while(current.next != pre) {
				current = current.next;
			}
			current = current.next; //pre
			node.next = current.next;
			current.next = node;
		}
	}
	
	void insertFirstNode(String str) {
		ListNode node = new ListNode(str);
		if(head == null) {
			head = node;
		}
		else {
			ListNode current = head;
			node.next = current;
			head = node;
		}
	}
	
	void deleteLastNode() {
		if(head == null) {
			System.out.println("���� ��尡 �������� �ʽ��ϴ�.");
		}
		else {
			ListNode prev = head;
			ListNode current = head.next;
			while(current.next != null) {
				prev = current;
				current = current.next;
			}
			prev.next = null;
			
		}
	}
	
	void deleteMiddleNode(String str) {
		ListNode node = new ListNode(str);
		if(head == null) {
			System.out.println("���� ��尡 �������� �ʽ��ϴ�.");
		}
		else {
			ListNode prev = head;
			ListNode current = head.next;
			while(current.data != node.data) {
				prev = current;
				current = current.next;
			}
			prev.next = current.next;
		}
		
	}
	
	void deleteFirstNode() {
		if(head == null) {
			System.out.println("���� ��尡 �������� �ʽ��ϴ�.");
		}
		else {
			head = head.next;
		}
	}
	
	ListNode searchNode(String str) {
		ListNode node = new ListNode(str);
		if(head == null) {
			System.out.println("��尡 ��� �ֽ��ϴ�.");
			return null;
		}
		else {
			ListNode current = head;
			while(current.data != node.data) {
				current = current.next;
			}
			return current;
		}
	}
	
	void reverseList() {
		ListNode next = head;
		ListNode current = null;
		ListNode prev = null;
		
		while(next != null) {
			prev = current;
			current = next;
			next = next.next;
			current.next = prev;
		}
		head = current;
	}
	
	void printList() {
		if(head == null) {
			System.out.println("����� ����Ʈ�� �������� �ʽ��ϴ�.");
		}
		else {
			ListNode current = head;
			System.out.print("[");
			while(current.next != null) {
				System.out.print(current.data + " ");
				current = current.next;
			}
			System.out.print(current.data);
			System.out.print("]");
			System.out.println();
		}
		
	}
}