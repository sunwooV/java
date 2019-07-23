package D0722;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayInputOutputTest {

	public static void main(String[] args) throws IOException {
		int ch;
		byte arr[] = {(byte) 'J', (byte) 'a', (byte) 'v', (byte) 'a', (byte) '!' };
		//배열 arr을 입력버퍼로 사용하는 객체 생성
		ByteArrayInputStream in = new ByteArrayInputStream(arr);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		FileOutputStream outFile = new FileOutputStream("fileStreamTest.txt");
		//배열 arr의 내용을 읽는다.
		while((ch = in.read()) != -1) {
			//출력Stream의 버퍼에 출력합니다.
			out.write(ch);
			//InputStream.available() : 읽어올 수 있는 데이터의 크기
			//ByteArrayOutputStream.toString() : 버퍼의 내용을 출력
			//ByteArrayOutputStream.size() : 버퍼의 현재 Size 리턴
			System.out.println(" read : [" + (char) ch + "]" +
			", write: [" + out.toString() + "]" + out.size() + ", available:" +in.available());
		}
		
		System.out.println("String :"+ out.toString());
		byte arrOut[] = out.toByteArray();
		for(int i=0;i<arrOut.length;i++) {
			System.out.println(arrOut[i] + ",");
		}
		
		//출력버퍼의 내용을 인지로 주어진 OutputStream(File)에 출력합니다.
		out.writeTo(outFile);

	}

}
