package java_chatting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MakeClientSocket {
	
	Socket socket = null;
	
	PrintWriter writer = null;
	BufferedReader reader = null;
	
	public MakeClientSocket() {
		try {
			// socket�� ������ �ſ� �޴� �� �� �� ������ ����.
			socket = new Socket("192.168.6.27", 2000); // localhost = 127.0.0.1 // �����ϰ� ���ϰ� ����� �� �ְԲ� ���ִ� ��. // �̼���ϲ� 192.168.6.27 
			writer = new PrintWriter(socket.getOutputStream(), true); // OutputStream�� socket�� 2000�� ����Ǿ� �ִ� ���� ����
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String str = null;
			
			//// ������ �κ� ////
			BufferedReader sReader = new BufferedReader(new InputStreamReader(System.in));
			
			while ((str = sReader.readLine()) != null) { // �ش� Buffer �ȿ� ���� ������ while�� ����.
				writer.println(str); // localhost�� ����. �ڱⰡ ������ Ȯ���ϴ� �ڵ�
				System.out.println("output : " + str);
			}
			////
			
			//// �޴� �κ� //// while���� ���ѷ����̱� ������ �Դٰ��ٰ� �ȵ�. ������ ���� �� �ľ� ���� ĥ �� ����. �׷��� �����带 ��������.
			
			writer.close();
			reader.close();
			sReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		new MakeClientSocket();
	}
}
