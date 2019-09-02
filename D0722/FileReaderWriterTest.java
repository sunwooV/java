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
		//append가 true -> 기존 파일 마지막에 추가
		//append가 false -> 기존의 파일내용을 덮어쓴다.
		int i, len = 0;
		String strFile01 = "C:\\MyProject\\workspace\\JAVA\\HashSetLotto.java";
		String strFile02 = "C:\\MyProject\\workspace\\JAVA\\fileStreamTest.txt";
		
		InputStream in = null;
		OutputStream out = null;
		try {
			//파일을 Character Input Stream에 연결한다.
			in = new FileInputStream(new File(strFile01));
			//파일을 Character Output Stream에 연결한다.
			out = new FileOutputStream(strFile02, append);
		} catch(FileNotFoundException e) {
			System.out.println(e);
		} catch(IOException e) {
			System.out.println(e);
		}
		
		try {
			//Input Stream에 연결된 파일 내용을 읽는다.
			while((i = in.read()) != -1) { //1 Char 를 읽어서
				//Output Stream에 연결된 파일에 내용을 출력한다.
				System.out.println(i); //한단어가 출력
				out.write(i); //1 char 쓴다.
				//총 읽은 char수를 count한다.
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