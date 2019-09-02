package D0722;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class CharArrayReaderWriterTest {

	public static void main(String[] args) throws IOException {
		int ch;
		//Input Stream으로 사용할 char 배열
		char arr[] = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'J', 'a', 'v', 'a', '!'};
		CharArrayReader in;
		CharArrayWriter out;
		
		//주어진 문자 배열의 주어진 부분을 입력 버퍼로 사용하는 Input Stream을 생성
		in = new CharArrayReader(arr, 7, 5); //index 7번째부터 5개
		//디폴트 크기의 문자 배열을 출력 버퍼로 사용하는 Output Stream을 생성
		out = new CharArrayWriter();
		while((ch = in.read()) != -1) {
			//출력버퍼에 쓰기
			out.write(ch);
			//CharArrayWriter.size() : 사용된 버퍼의 size
			System.out.println("read:[" + (char)ch + "], write:[" + out.toString() + "]" + out.size());
		}
		//출력버퍼에 쓰여진 내용을 문자열로 리턴한다.
		System.out.println(" String: " + out.toString());
		//출력버퍼를 char 배열로 리턴
		char arrOut[] = out.toCharArray();
		for(int i=0;i<arrOut.length;i++) {
			System.out.print(arrOut[i] + ",");
		}

	}

}
