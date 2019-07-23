package D0722;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileInputOutputStream {

	public static void main(String[] args) {
		boolean append = false;
		int i, len = 0;
		String strFile01 = "C:\\MyProject\\workspace\\JAVA\\HashSetLotto.java";
		String strFile02 = "C:\\MyProject\\workspace\\JAVA\\fileStreamTest.txt";
		
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(new File(strFile01));
			out = new FileOutputStream(strFile02, append);
		} catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			while((i = in.read()) != -1) { //1byte�� �о
				System.out.println(i); //1byte�̹Ƿ� �ѱ��� ����(�ѱ��� 2byte �̹Ƿ�)
				out.write(i); //fileStreamTest�� ������.
				len++;
			}
			in.close();
			out.close();
			System.out.println(len + " bytes are copied...");
		} catch(IOException e) {
			System.out.println(e);
		}

	}

}
