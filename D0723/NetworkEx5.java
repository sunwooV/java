package D0723;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

//memberForm.html �̶�� ������ �ٿ�ε� �ȴ�.

public class NetworkEx5 {

	public static void main(String[] args) {
		URL url = null;
		InputStream in = null; //���ҽ����� �о���δ�.
		FileOutputStream out = null;
		String address = "http://192.168.0.16:8090/pro07/memberForm.html";

		int ch = 0;
		
		try {
			url = new URL(address);
			in = url.openStream();
			out = new FileOutputStream("memberForm.html");
			
			while((ch=in.read()) != -1) {
				System.out.println("downloading..");
				out.write(ch);
			}
			in.close();
			out.close();
//			System.out.println("��");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
