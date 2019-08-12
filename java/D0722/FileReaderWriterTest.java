package D0722;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileReaderWriterTest {

	public static void main(String[] args) {
		boolean append = false;
		//append�� true -> ���� ���� �������� �߰�
		//append�� false -> ������ ���ϳ����� �����.
		int i, len = 0;
		String strFile01 = "C:\\MyProject\\workspace\\JAVA\\HashSetLotto.java";
		String strFile02 = "C:\\MyProject\\workspace\\JAVA\\fileStreamTest.txt";
		
		InputStream in = null;
		OutputStream out = null;
		try {
			//������ Character Input Stream�� �����Ѵ�.
			in = new FileInputStream(new File(strFile01));
			//������ Character Output Stream�� �����Ѵ�.
			out = new FileOutputStream(strFile02, append);
		} catch(FileNotFoundException e) {
			System.out.println(e);
		} catch(IOException e) {
			System.out.println(e);
		}
		
		try {
			//Input Stream�� ����� ���� ������ �д´�.
			while((i = in.read()) != -1) { //1 Char �� �о
				//Output Stream�� ����� ���Ͽ� ������ ����Ѵ�.
				System.out.println(i); //�Ѵܾ ���
				out.write(i); //1 char ����.
				//�� ���� char���� count�Ѵ�.
				len++;
			}
			in.close();
			out.close();
			System.out.println(len + " chars are copied...");
		} catch(IOException e) {
			System.out.println(e);
		}

	}

}