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
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	} catch (IOException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
	   

	}

}
