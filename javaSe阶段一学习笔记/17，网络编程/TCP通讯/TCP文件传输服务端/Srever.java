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
		System.out.println("�ͻ���"+num+"���ӳɹ��ɽ����ļ����ݴ���!");
		new Thread(new ServerIOput(sock, num)).start();
		num++;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

}
