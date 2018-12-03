package MyTCPBaoSong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Srever {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	 try {
		ServerSocket server=new ServerSocket(12000);
		int num=1;
		Socket sock=server.accept();
		System.out.println("客户端"+num+"连接成功可进行文件数据传送!");
		new Thread(new ServerIOput(sock, num)).start();
		num++;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

}
