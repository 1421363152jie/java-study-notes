
package TCPstuty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClentOutput implements Runnable{
    private Socket soc=null;
    
	
	
	public ClentOutput(Socket soc) {
		super();
		this.soc = soc;
	}



	@Override
	public void run() {
		try {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(soc.getOutputStream(),true);
		while(true){
				String line=br.readLine();
				pw.println(line);
				if(line.equals("bye")){
					break;
		    }
		}
				br.close();
				pw.close();
				soc.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	
	

