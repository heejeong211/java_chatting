package java_chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MakeServerSocket { // ���� ���� �ƴ�. 1:1��.

	ServerSocket serverSocket = null;
	Socket socket = null;

	// ���� �����ϰ� ä���� �о�帮��, �᳻�� �ϴ� ��.
	PrintWriter writer = null;
	BufferedReader reader = null;
	String lineStr;

	public MakeServerSocket() {

		try {
			serverSocket = new ServerSocket(2000); // 2000 ��Ʈ��ȣ. �̰� ���� ���� �� �ִ� ��ȣ��.

			// ���� = ��ٸ��� ��(���ѷ���)
			while (true) {

				socket = serverSocket.accept(); // �ǹ����� �κп����� while������ ��ٸ��� ���ε�, ���������δ� ���⼭ ���缭 ��ٸ��� ����. Ŭ���̾�Ʈ�� ��ٸ�. ������(Ŭ���̾�Ʈ)�� ������ �ϰ� �Ǹ� socket��ü�� ������.
				System.out.println("Client ��û��");

				// ä�� �ְ� �޴� �ڵ�
				writer = new PrintWriter(socket.getOutputStream(), true); // ���� ���� ��.
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ������ ���� ä���� ��� �ҽ��ڵ�� �����?

				// ��ٸ��� �κ�. Buffer�� ���� �ֱ⸦ ��ٸ��� �κ�.
				while ((lineStr = reader.readLine()) != null) {
					writer.write(lineStr); // write�ϸ� ����� socket���� ��������. ������ socket.getOutputStream() �̷��� ����� ������.
					System.out.println("input : " + lineStr);
				}

				writer.close();
				reader.close();
				socket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new MakeServerSocket();
	}
}
// Stream�� �о�帮�� �᳻�� ����. ������ writer/out, ������ reader/in