package TCPstuty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket server =new ServerSocket(10000);
		int number=1;//保存客户端的个数
		while(true){
		Socket sock=server.accept();
       System.out.println("客户端"+number+"连接成功!");
       //服务端开启独立线程
       new Thread(new Serverxian(sock, number)).start();
       number++;
		}
	}



}
