package java_chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MakeServerSocket { // 동시 접속 아님. 1:1임.

	ServerSocket serverSocket = null;
	Socket socket = null;

	// 내가 상대방하고 채팅을 읽어드리고, 써내고 하는 것.
	PrintWriter writer = null;
	BufferedReader reader = null;
	String lineStr;

	public MakeServerSocket() {

		try {
			serverSocket = new ServerSocket(2000); // 2000 포트번호. 이건 내가 정할 수 있는 번호임.

			// 서버 = 기다리는 쪽(무한루프)
			while (true) {

				socket = serverSocket.accept(); // 의미적인 부분에서는 while문에서 기다리는 것인데, 실질적으로는 여기서 멈춰서 기다리고 있음. 클라이언트를 기다림. 누군가(클라이언트)가 접속을 하게 되면 socket객체가 생성됨.
				System.out.println("Client 요청됨");

				// 채팅 주고 받는 코드
				writer = new PrintWriter(socket.getOutputStream(), true); // 내가 보낸 것.
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 상대방이 보낸 채팅을 어떻게 소스코드로 만들까?

				// 기다리는 부분. Buffer에 뭔가 있기를 기다리는 부분.
				while ((lineStr = reader.readLine()) != null) {
					writer.write(lineStr); // write하면 상대편 socket으로 보내버림. 위에서 socket.getOutputStream() 이렇게 써놨기 때문에.
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
// Stream은 읽어드리고 써내는 파일. 파일을 writer/out, 파일을 reader/in