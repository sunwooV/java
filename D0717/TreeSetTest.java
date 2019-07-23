package D0717;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

//TreeSet ���Ҵ� �ݵ�� Comparable �̾�� �Ѵ�.
//TreeSet����Ϸ��� ���տ� ���� ���Ұ� comparable�� �����ϴ� �����̾�� �Ѵ�.
//Comparator�� ���ڷ� �޾� ���̴� Treeset �� �����ε�� �����ڸ� ���

public class TreeSetTest {

	public static void main(String[] args) {
		new TreeSetTest().go();

	}
	
	@SuppressWarnings("unchecked")
	public void go() {
		//Comparable �̿�
		Book b1 = new Book("How Cats Work");
		Book b2 = new Book("Remix your Body");
		Book b3 = new Book("Finding Emo");
		
		TreeSet tree = new TreeSet();
		tree.add(b1);
		tree.add(b2);
		tree.add(b3);
		
		//���ĵǾ� ��ȸ�ȴ�.
		System.out.println("1 : " + tree);
		
		//Comparator �̿�
		Book2 t1 = new Book2("How Cats Work");
		Book2 t2 = new Book2("Remix your Body");
		Book2 t3 = new Book2("Finding Emo");
		
		TreeSet tree2 = new TreeSet(new BookCompare());
		tree2.add(t1);
		tree2.add(t2);
		tree2.add(t3);
		//���ĵǾ� ��ȸ�ȴ�.
		
		System.out.println("2 : " + tree2);
		//ù��°�� ��ȸ
		System.out.println("3 : " + tree2.first());
		//�������� ��ȸ
		System.out.println("4 : " + tree2.last());
		
		//����
		System.out.println("5 : " + tree2.remove(new Book2("Finding Emo")));
		System.out.println("6 : " + tree2);
		tree2.add(t3);
		
		//���ں��� ���� ���� �׸���� ����
		SortedSet ss = tree2.headSet(new Book2("Remix your Body"));
		System.out.println("7 : " + ss);
		//���ں��� ���� ũ�ų� ���� �׸���� ����
		SortedSet ss2 = tree2.tailSet(new Book2("Finding Emo"));
		System.out.println("8 : " + ss2);
		//SubSet(a,b) : a�̻� b�̸��� ����
		SortedSet ss3 = tree2.subSet(new Book2("Finding Emo"), new Book2("Remix your Body"));
		System.out.println("9 : " + ss3);
		
		//copy
		TreeSet tree3 = (TreeSet) tree2.clone();
		System.out.println("10 : " + tree3);
	}

}

class Book implements Comparable {
	private String title;
	
	public Book(String t) {
		this.title = t;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return title;
	}
	
	public int compareTo(Object obj) {
		Book book = (Book) obj;
		return title.compareTo(book.getTitle());
	}
}

class Book2 {
	private String title;
	
	public Book2(String t) {
		this.title = t;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return title;
	}
}


//Comparator�� equals �޼ҵ嵵 ���������� ������ �϶�� �ȶߴ� ��
//Object class�� equals �޼ҵ尡 �����Ǿ� �ֱ� ����
@SuppressWarnings("rawtypes")
class BookCompare implements Comparator{
	public int compare(Object arg0, Object arg1) {
		return ((Book2)arg0).getTitle().compareTo(((Book2)arg1).getTitle());
	}
}