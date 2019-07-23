package D0709;

public class OperatorEx2 {

	public static void main(String[] args) {
		String str1 = "abc";
		String str2 = new String("abc");
		
		System.out.println("abc"=="abc"); //true
		System.out.println(str1 == "abc"); //true
		System.out.println(str2 == "abc"); //false
		System.out.println(str1.equals("abc")); //true
		System.out.println(str2.equals("abc")); //true
		System.out.println(str2.equals("ABC")); //false
		System.out.println(str2.equalsIgnoreCase("ABC")); //true

	}

}
