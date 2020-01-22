package java_chatting2;

import java.net.Socket;

public class TcpClient {

	public static void main(String[] args) {
		try {
			
			String serverIp = "127.0.0.1";//�̼���ϲ� 192.168.6.27
			
			// ������ �����Ͽ� ������ ��û�Ѵ�.
			Socket socket = new Socket(serverIp, 7777); // �̼���ϲ� 1234
			
			System.out.println("������ ����Ǿ����ϴ�.");
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
