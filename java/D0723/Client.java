package D0723;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			String serverIp = "192.168.0.50";
			
			System.out.println("[Client]" + "������ �������Դϴ�. ���� IP: " + serverIp);
			//������ �����Ͽ� ������ ��û�Ѵ�.
			Socket socket = new Socket(serverIp, 7777);
			
			//������ �Է½�Ʈ���� ��´�.
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			//�������κ��� ���� �����͸� ����Ѵ�.
			System.out.println("[Client]" + "�����κ��� ���� �޽���: " + dis.readUTF());
			System.out.println("[Client]" + "������ �����մϴ�.");
			
			//��Ʈ���� ������ �ݴ´�.
			dis.close();
			socket.close();
			System.out.println("[Client]" + "������ ����Ǿ����ϴ�.");
		} catch(ConnectException ce) {
			ce.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
