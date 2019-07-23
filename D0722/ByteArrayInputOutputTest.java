package D0722;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayInputOutputTest {

	public static void main(String[] args) throws IOException {
		int ch;
		byte arr[] = {(byte) 'J', (byte) 'a', (byte) 'v', (byte) 'a', (byte) '!' };
		//�迭 arr�� �Է¹��۷� ����ϴ� ��ü ����
		ByteArrayInputStream in = new ByteArrayInputStream(arr);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		FileOutputStream outFile = new FileOutputStream("fileStreamTest.txt");
		//�迭 arr�� ������ �д´�.
		while((ch = in.read()) != -1) {
			//���Stream�� ���ۿ� ����մϴ�.
			out.write(ch);
			//InputStream.available() : �о�� �� �ִ� �������� ũ��
			//ByteArrayOutputStream.toString() : ������ ������ ���
			//ByteArrayOutputStream.size() : ������ ���� Size ����
			System.out.println(" read : [" + (char) ch + "]" +
			", write: [" + out.toString() + "]" + out.size() + ", available:" +in.available());
		}
		
		System.out.println("String :"+ out.toString());
		byte arrOut[] = out.toByteArray();
		for(int i=0;i<arrOut.length;i++) {
			System.out.println(arrOut[i] + ",");
		}
		
		//��¹����� ������ ������ �־��� OutputStream(File)�� ����մϴ�.
		out.writeTo(outFile);

	}

}
