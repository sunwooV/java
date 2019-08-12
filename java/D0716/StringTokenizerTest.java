package D0716;

import java.util.StringTokenizer;

public class StringTokenizerTest {

	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("This \t is a \n String");
		
		while(st.hasMoreTokens()) //Token()�� �� ���� ������ (boolean type)
			System.out.println(st.nextToken());
		
		
		System.out.println("============================");
		//���ڷ� �־��� ���ڿ��� :�� �����ڷμ� �����Ѵ�.
		String str = "80::95:70"; 
		StringTokenizer st2 = new StringTokenizer(str, ":", false); //80 95 70
		int i = 0;
		while(st2.hasMoreTokens()) {
			System.out.println(i + ":" + st2.nextToken());
			i++;
		}
		
		
		System.out.println("============================");
		//StringTokenizer�� �����ڳ��� �پ� �ִ� ��� ���� ���� ������ ���� ���ϴ� ������ �ִ�.
		String[] result = str.split(":");  //80 : 95 70 -> 4���� ������
		for(int x = 0;x<result.length;x++) {
			System.out.println(x + ":" + result[x]);
		}
		
		
		System.out.println("============================");
		//������ split�� �������� �����ڰ� ���� �� �̸� ó������ �� �� => 2���� ���ڸ� �޴� split���.
		String str2 = "81-2-010-1234-5678";
		String[] arr2 = str2.split("-", 4);
		for(int x = 0;x<arr2.length;x++) {
			System.out.println(x + ":" + arr2[x]);
		}
		
		


	}

}
