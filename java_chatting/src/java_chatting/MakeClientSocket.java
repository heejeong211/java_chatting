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
			// socket은 보내는 거와 받는 거 둘 다 가지고 있음.
			socket = new Socket("192.168.6.27", 2000); // localhost = 127.0.0.1 // 서버하고 나하고 통신할 수 있게끔 해주는 것. // 미선언니꺼 192.168.6.27 
			writer = new PrintWriter(socket.getOutputStream(), true); // OutputStream은 socket과 2000에 연결되어 있는 빨대 역할
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String str = null;
			
			//// 보내는 부분 ////
			BufferedReader sReader = new BufferedReader(new InputStreamReader(System.in));
			
			while ((str = sReader.readLine()) != null) { // 해당 Buffer 안에 뭐라도 있으면 while문 가동.
				writer.println(str); // localhost로 보냄. 자기가 보낸거 확인하는 코드
				System.out.println("output : " + str);
			}
			////
			
			//// 받는 부분 //// while문이 무한루프이기 때문에 왔다갔다가 안됨. 상대방이 글을 다 쳐야 내가 칠 수 있음. 그래서 쓰레드를 만들어야함.
			
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
