package D0723;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class NetworkEx1 {

	public static void main(String[] args) {
		InetAddress ip = null;
		InetAddress[] ipArr = null;
		
		try {
			ip = InetAddress.getByName("www.naver.com");
			System.out.println("getHostName(): " + ip.getHostName());
			System.out.println("getHostAddress(): " + ip.getHostAddress());
			System.out.println("toString():" + ip.toString());
			
			byte[] ipAddr = ip.getAddress();
			System.out.println("getAddress(): " + Arrays.toString(ipAddr));
			
			String result = "";
			for(int i=0;i<ipAddr.length;i++) {
				result += (ipAddr[i] < 0) ? ipAddr[i]+256 : ipAddr[i];
				result += ".";
			} 
			System.out.println("getAddress()+256: " + result);
			System.out.println();
		} catch(UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("getHostName(): " + ip.getHostName()); //호스트 이름(DESKTOP-IN6GGIM)
			System.out.println("getHostAddress(): " + ip.getHostAddress()); //호스트 ip 주소
			System.out.println();
		} catch(UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			ipArr = InetAddress.getAllByName("www.naver.com");
			
			for(int i=0;i<ipArr.length;i++) {
				System.out.println("ipArr[" + i + "]: " + ipArr[i]);;
				//ipArr[0]: www.naver.com/210.89.160.88
				//ipArr[1]: www.naver.com/125.209.222.142
			}
		} catch(UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
