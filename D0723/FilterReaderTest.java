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

	//read �޼ҵ带 Override�Ѵ�.
	//�ּ��κ��� ���� �ʵ��� Filter ó��
	public int read(char[] buf, int offset, int len) throws IOException {
		int numChars = 0;
		boolean inComment = false;
		while(numChars == 0) {
			numChars = in.read(buf, offset, len);
			if(numChars == -1)
				return -1;
			int last = offset;
			for(int i=offset;i<offset+numChars;i++) {
				//�ּ� �κ��� filter�μ� �ɷ�����.
				if(inComment) {
					if((buf[i] == '*') && (buf[i+1] == '/')){
						System.out.println("�ּ�1");
						inComment = false; //�ּ� ���� �˸�
						i++;
					}
					else {
						if((buf[i] == '/') && (buf[i+1] == '*')) {
							System.out.println("�ּ�2");
							inComment = true; //�ּ� ����
							i++;
						}
						else {
							buf[last++] = buf[i]; //Data ���
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
		//�����ڵ�μ� 2byte�� �о� ���δ�.
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
			//File Input Stream�� �����Ѵ�.
			f = new FileReader("AaTxtTest.txt");
			//File Stream ���ڷ� �ϴ� Filter Stream�� �����Ͽ� �̸� Buffer Stream�� �����Ѵ�.
			in = new BufferedReader(new FilterReaderTest(f));
			//���پ� �о���δ�. readLine()�� ���ο��� read()�޼ҵ带 �̿��Ͽ� �۵��Ѵ�.
			while((buf = in.readLine())!=null) {
				System.out.println(buf);
			}
			in.close();
		} catch(Exception e) {
			System.err.println(e);
		}

	}

}