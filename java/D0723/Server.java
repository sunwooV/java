package D0723;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			//���������� �����Ͽ� 7777�� ��Ʈ�� ����(bind)��Ų��.
			serverSocket = new ServerSocket(7777);
			
			System.out.println("[Server]" + getTime() + "������ �غ�Ǿ����ϴ�.");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				System.out.println("[Server]" + getTime() + "�����û�� ��ٸ��ϴ�.");
				//���������� Ŭ���̾�Ʈ�� �����û�� �� ������ ������ ���߰� ��� ��ٸ���.
				//Ŭ���̾�Ʈ�� �����û�� ���� Ŭ���̾�Ʈ ���ϰ� ����� ���ο� ������ �����Ѵ�.
				Socket socket = serverSocket.accept();
				System.out.println("[Server]" + getTime() + socket.getInetAddress() + "�κ��� �����û�� ���Խ��ϴ�.");
				
				//������ ��½�Ʈ���� ��´�.
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				//���� ����(remote socket)�� �����͸� ������.
				dos.writeUTF("[Notice] Test Message1 from Server.");
				System.out.println("[Server]" + getTime() + "�����͸� �����߽��ϴ�.");
				
				//��Ʈ���� ������ �ݾ��ش�.
				dos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static Date getTime() {
		Date date = new Date();
		return date;
	}

}
