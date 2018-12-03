package TCPstuty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Serverxian implements Runnable {
    private Socket sock=null;
    private int number;
	
	public Serverxian(Socket sock, int number) {
		super();
		this.sock = sock;
		this.number = number;
	}

	@Override
	public void run() {
    try {
		BufferedReader br=new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter pw=new PrintWriter(sock.getOutputStream(),true);
		while(true){
			String line=br.readLine();
			System.out.println("客户端"+number+":"+line);
			pw.println(line);
			if(line.equals("byte")){
				System.out.println("客户端"+number+"断开连接");
				break;
			}
		}
		br.close();
		pw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

	}

	
}
