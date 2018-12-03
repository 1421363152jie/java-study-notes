package TCPstuty;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clent {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws Exception {
		Socket soc=new Socket("192.168.42.71",10000);
        new Thread(new ClentInput(soc)).start();
        new Thread(new ClentOutput(soc)).start();
        
	}

}
