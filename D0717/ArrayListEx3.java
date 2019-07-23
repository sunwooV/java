package D0717;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListEx3 {

	public static void main(String[] args) {
		ArrayList a = new ArrayList();
		
		Book22 b1 = new Book22("CC");
		Book22 b2 = new Book22("AA");
		Book22 b3 = new Book22("BB");
		
		a.add(b1); a.add(b2); a.add(b3);
		
		Collections.sort(a);
		
		System.out.println(a);
	}
}


class Book22 implements Comparable {
	private String title;
	public Book22(String t) {
		title = t;
	}
	public String getTitle() { 
		return title;
	}
	@Override
	public int compareTo(Object obj) {
		Book22 book = (Book22) obj;
		return title.compareTo(book.getTitle());
	}
}
