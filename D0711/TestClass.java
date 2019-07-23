package D0711;

public class TestClass {

	public static void main(String[] args) {
		char[] c = {'H', 'e', 'l', 'l', 'o'};
		String s = new String(c);
		System.out.println(s); //Hello
		
		StringBuffer sb = new StringBuffer("Hello");
		String s2 = new String(sb); 
		System.out.println(sb); //Hello
		System.out.println(s2); //Hello
		
		String s3 = "Hello";
		char c3 = s3.charAt(1); //e 인덱스 [1]에 위치한 문자
		System.out.println(c3); 
		
		//int compareTo(String str)
		//str보다 내가 큰지/작은지
		int I = "aae".compareTo("aaa"); //Unicode값으로 String 비교 -> Unicode 차잇값을 가진다.
		System.out.println("compareTo: " + I); //4 (aae - aaa)
		
		String s4 = "Hello";
		String s5 = s4.concat(" World");
		System.out.println(s5); //Hello World
		
		String s6 = "abcdefg";
		boolean b = s6.contains("bf"); //false
		boolean b2 = s6.contains("bc"); //true
		System.out.println(b + " " + b2);
		
		String file = "Hello.txt";
		boolean b3 = file.endsWith("txt"); //true //지정된 문자열로 끝나는지 검사
		System.out.println(b3);
		
		String s7 = "Hello";
		int idx1 = s7.indexOf('o'); //4
		System.out.println(idx1);
		
		//int indexOf(int ch, int pos)
		//문자(ch)가 존재하는 위치를 pos부터 확인하여 index를 알려줌
		String s8 = "Hello";
		int idx2 = s8.indexOf('e', 0); //0을 기준으로 'e'가 어디있는지
		int idx3 = s8.indexOf('l', 2); //2
		int idx31 = s8.indexOf('l', 3); //3 //3부터 확인하여 'l'가 어디있는지	
		System.out.println("indexOf(,): " + idx2 + " " + idx3);
		
		String s9 = "ABCDEFG";
		int idx4 = s9.indexOf("DE"); //첫번째 문자가 위치한 인덱스 반환
		System.out.println(idx4); //3
		
		String s10 = "java.lang.Object";
		int idx5 = s10.lastIndexOf('.'); //마지막으로 .이 위치한 인덱스 반환
		System.out.println(idx5); //9
	
		String s11 = "Hello";
		String s12 = s11.replace('H', 'C'); //old문자를 nw로 변경
		System.out.println(s12); //Cello
		
		String sb2 = "AABBAABB";
		String r = sb2.replaceAll("BB", "bb"); //전체 변경
		System.out.println(r); //AAbbAAbb
		
		String r2 = sb2.replaceFirst("BB", "bb"); //일치하는 것중 첫번째 것만 변경
		System.out.println(r2); //AAbbAABB
		
		String animals = "dog,cat,bear"; //분리자로 나누어 배열을 반환
		String[] arr = animals.split(",");
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i] + " "); //dog cat bear
		}
		
		String[] arr2 = animals.split(",", 2); //분리하되 문자열을 2 수만큼 자른다
		for(int i=0;i<arr2.length;i++) {
			System.out.print(arr2[i] + " "); //dog cat,bear 
		}
		
		boolean b4 = s10.startsWith("java"); //주어진 문자열로 시작하는지 검사
		System.out.println(b4); //true
		
		String s13 = "	Hello World	";
		String s14 = s13.trim(); //문자열의 왼쪽, 오른쪽 끝에 있는 공백을 없앤 결과를 반환
		System.out.println(s14);
		
		
		String b5 = String.valueOf(true); //지정된 값을 문자열로 반환
		System.out.println(b5); //true
		
		
		String[] arr3 = {"A","B", "C"};
		String s15 = String.join("-", arr3);
		System.out.println(s15); //A-B-C
		
	}

}
