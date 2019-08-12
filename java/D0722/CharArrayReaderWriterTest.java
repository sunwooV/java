package D0722;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class CharArrayReaderWriterTest {

	public static void main(String[] args) throws IOException {
		int ch;
		//Input Stream���� ����� char �迭
		char arr[] = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'J', 'a', 'v', 'a', '!'};
		CharArrayReader in;
		CharArrayWriter out;
		
		//�־��� ���� �迭�� �־��� �κ��� �Է� ���۷� ����ϴ� Input Stream�� ����
		in = new CharArrayReader(arr, 7, 5); //index 7��°���� 5��
		//����Ʈ ũ���� ���� �迭�� ��� ���۷� ����ϴ� Output Stream�� ����
		out = new CharArrayWriter();
		while((ch = in.read()) != -1) {
			//��¹��ۿ� ����
			out.write(ch);
			//CharArrayWriter.size() : ���� ������ size
			System.out.println("read:[" + (char)ch + "], write:[" + out.toString() + "]" + out.size());
		}
		//��¹��ۿ� ������ ������ ���ڿ��� �����Ѵ�.
		System.out.println(" String: " + out.toString());
		//��¹��۸� char �迭�� ����
		char arrOut[] = out.toCharArray();
		for(int i=0;i<arrOut.length;i++) {
			System.out.print(arrOut[i] + ",");
		}

	}

}
