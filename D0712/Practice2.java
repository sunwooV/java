package D0712;

class Book{
	private int number; //�����ڹ�ȣ
	private String title; //����
	private String author; //����
//	Book book;

	public Book(int number, String title, String author) {
		setMethod(number, title, author);
//		this.number = number;
//		this.title = title;
//		this.author = author;
	}

	public void setMethod(int number, String title, String author){ //(Book book)
		this.number = number;
		this.title = title;
		this.author = author;
//		this.book = book;
	}
	
	public String getMethod(){
		return number + " " + title + " " + author;
//		return book.number + book.title + book.author;
	}
	
	public void setLateFee(int day) {
	}
	
	public int getLateFee() {
		return 0;
	}
	
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Book) {
			return number == ((Book)obj).number;
		}else {
			return false;
		}
	} 
   }

class Animation extends Book{
	private int day; //��ü����
	
	public Animation(int number, String title, String author) {
		super(number, title, author);
	}
	@Override
	public void setLateFee(int day) {
		this.day = day;
	}
	@Override
	public int getLateFee() {
		return day * 300;
	}
}

class Science extends Book{
	private int day;

	public Science(int number, String title, String author) {
		super(number, title, author);
	}
	@Override
	public void setLateFee(int day) {
		this.day = day;
	}
	@Override
	public int getLateFee() {
		return day * 200;
	}
}

public class Practice2 {

	public static void main(String[] args) {

		Book book = new Animation(1000, "�ִϸ��̼�", "�ִϸ��̼� �۰�");
		Book book2 = new Science(1000, "������ ����", "���� �۰�");

		System.out.println(book.equals(book2)); //true
		
//		book.setMethod(book);
//		System.out.println(book.getMethod());
		
		book.setLateFee(5);
		book2.setLateFee(5);
		
		System.out.println(book.getLateFee() + ", " + book2.getLateFee());
		
	}

}
