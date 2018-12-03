package tcpclent;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	   try {
		Socket soc=new Socket("192.168.42.71",12000);
		new Thread(new ClentOutput(soc)).start();
	} catch (UnknownHostException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	   

	}

}
