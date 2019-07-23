package D0723;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

//Stream으로 바로 접근하여 Data를 주고 받는다.

public class NetworkEx4 {

	public static void main(String[] args) {
		URL url = null;
		BufferedReader input = null;
		String address = "http://192.168.0.16:8090/pro07/memberForm.html";
		String line = "";
		
		try {
			url = new URL(address);
			
			input = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			//stream 형식을 reader형식으로 바꿔준다. 
			
			while((line = input.readLine())!=null) {
				System.out.println(line);
			}
			input.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
