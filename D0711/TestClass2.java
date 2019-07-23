package D0711;

public class TestClass2 {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("H1");
		System.out.println(sb);
		
		StringBuffer sb1 = new StringBuffer("abc");
		StringBuffer a = sb.append("anc");
		System.out.println(a); //H1anc
		
		StringBuffer sb2 = new StringBuffer("abc");
		char c = sb2.charAt(2);
		System.out.println(c); //c
		
		StringBuffer sb3 = new StringBuffer("0123456");
		char c2 = sb3.charAt(2);
		char c3 = sb3.charAt(3);
		System.out.println(c2); //2
		System.out.println(c3); //3
		
		sb3.insert(4, ',');
		System.out.println(sb3); //0123,456 -> StringBuffer는 본내용이 변화된다.
		
		StringBuffer sb4 = new StringBuffer("0123456");
		StringBuffer sb5 = sb4.replace(3, 6, "AB");
		System.out.println(sb5); //012AB6
		
		StringBuffer sb6 = new StringBuffer("0123456");
		String str = sb6.substring(3); //3456
		String str2 = sb6.substring(3, 5); //34
		System.out.println(str + " " + str2);
		
	}

	
}
