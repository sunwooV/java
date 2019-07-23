package D0723;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectException;
import java.util.Scanner;

public class TcpIpMultichatClient {

	public static void main(String[] args) {
		String chatName = "서누";
		
		try {
			String serverIp = "192.168.0.50";
			//소켓을 생성하여 연결을 요청한다.
			Socket socket = new Socket(serverIp, 7777);
			System.out.println("서버에 연결되었습니다");
			Thread sender = new Thread(new ClientSender(socket, chatName)); //서버로 chat문장 write
			Thread receiver = new Thread(new ClientReceiver(socket)); //서버에서 chat문장 read
			
			sender.start();
			receiver.start();
		} catch(ConnectException ce) {
			ce.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	//서버에 chat문장을 전달하는 thread
	static class ClientSender extends Thread{
		Socket socket;
		DataOutputStream out;
		String name;
		
		ClientSender(Socket socket, String name){
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//대화를 입력
		public void run() {
			Scanner sc = new Scanner(System.in);
			try {
				if(out != null) {
					out.writeUTF(name); //chat이름을 서버에 전달
				}
				
				while(out != null) {
					out.writeUTF("[" + name + "]" + sc.nextLine()); //채팅문장 서버에 전달
				}
			} catch(IOException e) {}
		}
	}
	
	
	//Server에 전달하는 Chat문장을 Read하는 thread
	static class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream in;
		
		ClientReceiver(Socket socket){
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {}
		}
		
		public void run() {
			while(in != null) {
				try {
					System.out.println(in.readUTF());
				} catch(IOException e) {
					
				}
			}
		}
	}

	

}
