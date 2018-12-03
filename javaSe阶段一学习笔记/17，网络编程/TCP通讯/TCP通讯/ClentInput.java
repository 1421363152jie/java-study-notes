package TCPstuty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClentInput implements Runnable{
    private Socket soc=null;
	
	
	
	public ClentInput(Socket soc) {
		super();
		this.soc = soc;
	}



	@Override
	public void run() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			while(true){
				String line=br.readLine();
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
