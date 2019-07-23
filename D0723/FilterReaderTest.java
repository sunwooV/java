package D0723;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class FilterReaderTest extends FilterReader {

	protected FilterReaderTest(Reader in) {
		super(in);
	}

	//read 메소드를 Override한다.
	//주석부분은 읽지 않도록 Filter 처리
	public int read(char[] buf, int offset, int len) throws IOException {
		int numChars = 0;
		boolean inComment = false;
		while(numChars == 0) {
			numChars = in.read(buf, offset, len);
			if(numChars == -1)
				return -1;
			int last = offset;
			for(int i=offset;i<offset+numChars;i++) {
				//주석 부분은 filter로서 걸러낸다.
				if(inComment) {
					if((buf[i] == '*') && (buf[i+1] == '/')){
						System.out.println("주석1");
						inComment = false; //주석 종료 알림
						i++;
					}
					else {
						if((buf[i] == '/') && (buf[i+1] == '*')) {
							System.out.println("주석2");
							inComment = true; //주석 시작
							i++;
						}
						else {
							buf[last++] = buf[i]; //Data 담기
						}
					}
				}
			}
			numChars = last - offset;
		}
		return (numChars);
	}
	
	public int read() throws IOException {
		char[] buf = new char[1];
		//유니코드로서 2byte씩 읽어 들인다.
		int result = read(buf, 0, 1);
		if(result == -1) {
			return -1;
		} else {
			return ((int)buf[0]);
		}
	}
	
	public static void main(String[] args) {
		FileReader f = null;
		BufferedReader in = null;
		String buf;
		try {
			//File Input Stream을 생성한다.
			f = new FileReader("AaTxtTest.txt");
			//File Stream 인자로 하는 Filter Stream을 생성하여 이를 Buffer Stream에 연결한다.
			in = new BufferedReader(new FilterReaderTest(f));
			//한줄씩 읽어들인다. readLine()은 내부에서 read()메소드를 이용하여 작동한다.
			while((buf = in.readLine())!=null) {
				System.out.println(buf);
			}
			in.close();
		} catch(Exception e) {
			System.err.println(e);
		}

	}

}