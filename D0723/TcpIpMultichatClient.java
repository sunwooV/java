package D0723;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectException;
import java.util.Scanner;

public class TcpIpMultichatClient {

	public static void main(String[] args) {
		String chatName = "����";
		
		try {
			String serverIp = "192.168.0.50";
			//������ �����Ͽ� ������ ��û�Ѵ�.
			Socket socket = new Socket(serverIp, 7777);
			System.out.println("������ ����Ǿ����ϴ�");
			Thread sender = new Thread(new ClientSender(socket, chatName)); //������ chat���� write
			Thread receiver = new Thread(new ClientReceiver(socket)); //�������� chat���� read
			
			sender.start();
			receiver.start();
		} catch(ConnectException ce) {
			ce.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	//������ chat������ �����ϴ� thread
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
		
		//��ȭ�� �Է�
		public void run() {
			Scanner sc = new Scanner(System.in);
			try {
				if(out != null) {
					out.writeUTF(name); //chat�̸��� ������ ����
				}
				
				while(out != null) {
					out.writeUTF("[" + name + "]" + sc.nextLine()); //ä�ù��� ������ ����
				}
			} catch(IOException e) {}
		}
	}
	
	
	//Server�� �����ϴ� Chat������ Read�ϴ� thread
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
