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
		int number=1;//����ͻ��˵ĸ���
		while(true){
		Socket sock=server.accept();
       System.out.println("�ͻ���"+number+"���ӳɹ�!");
       //����˿��������߳�
       new Thread(new Serverxian(sock, number)).start();
       number++;
		}
	}



}
