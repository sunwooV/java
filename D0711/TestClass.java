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
		char c3 = s3.charAt(1); //e �ε��� [1]�� ��ġ�� ����
		System.out.println(c3); 
		
		//int compareTo(String str)
		//str���� ���� ū��/������
		int I = "aae".compareTo("aaa"); //Unicode������ String �� -> Unicode ���հ��� ������.
		System.out.println("compareTo: " + I); //4 (aae - aaa)
		
		String s4 = "Hello";
		String s5 = s4.concat(" World");
		System.out.println(s5); //Hello World
		
		String s6 = "abcdefg";
		boolean b = s6.contains("bf"); //false
		boolean b2 = s6.contains("bc"); //true
		System.out.println(b + " " + b2);
		
		String file = "Hello.txt";
		boolean b3 = file.endsWith("txt"); //true //������ ���ڿ��� �������� �˻�
		System.out.println(b3);
		
		String s7 = "Hello";
		int idx1 = s7.indexOf('o'); //4
		System.out.println(idx1);
		
		//int indexOf(int ch, int pos)
		//����(ch)�� �����ϴ� ��ġ�� pos���� Ȯ���Ͽ� index�� �˷���
		String s8 = "Hello";
		int idx2 = s8.indexOf('e', 0); //0�� �������� 'e'�� ����ִ���
		int idx3 = s8.indexOf('l', 2); //2
		int idx31 = s8.indexOf('l', 3); //3 //3���� Ȯ���Ͽ� 'l'�� ����ִ���	
		System.out.println("indexOf(,): " + idx2 + " " + idx3);
		
		String s9 = "ABCDEFG";
		int idx4 = s9.indexOf("DE"); //ù��° ���ڰ� ��ġ�� �ε��� ��ȯ
		System.out.println(idx4); //3
		
		String s10 = "java.lang.Object";
		int idx5 = s10.lastIndexOf('.'); //���������� .�� ��ġ�� �ε��� ��ȯ
		System.out.println(idx5); //9
	
		String s11 = "Hello";
		String s12 = s11.replace('H', 'C'); //old���ڸ� nw�� ����
		System.out.println(s12); //Cello
		
		String sb2 = "AABBAABB";
		String r = sb2.replaceAll("BB", "bb"); //��ü ����
		System.out.println(r); //AAbbAAbb
		
		String r2 = sb2.replaceFirst("BB", "bb"); //��ġ�ϴ� ���� ù��° �͸� ����
		System.out.println(r2); //AAbbAABB
		
		String animals = "dog,cat,bear"; //�и��ڷ� ������ �迭�� ��ȯ
		String[] arr = animals.split(",");
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i] + " "); //dog cat bear
		}
		
		String[] arr2 = animals.split(",", 2); //�и��ϵ� ���ڿ��� 2 ����ŭ �ڸ���
		for(int i=0;i<arr2.length;i++) {
			System.out.print(arr2[i] + " "); //dog cat,bear 
		}
		
		boolean b4 = s10.startsWith("java"); //�־��� ���ڿ��� �����ϴ��� �˻�
		System.out.println(b4); //true
		
		String s13 = "	Hello World	";
		String s14 = s13.trim(); //���ڿ��� ����, ������ ���� �ִ� ������ ���� ����� ��ȯ
		System.out.println(s14);
		
		
		String b5 = String.valueOf(true); //������ ���� ���ڿ��� ��ȯ
		System.out.println(b5); //true
		
		
		String[] arr3 = {"A","B", "C"};
		String s15 = String.join("-", arr3);
		System.out.println(s15); //A-B-C
		
	}

}
