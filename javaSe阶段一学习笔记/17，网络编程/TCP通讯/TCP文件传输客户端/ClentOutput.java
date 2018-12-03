package tcpclent;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClentOutput implements Runnable{
   private Socket soc;

public ClentOutput(Socket soc) {
	super();
	this.soc = soc;
}

    @Override
    public void run() {
    	BufferedInputStream br=null;
    	PrintStream ps=null;
	try {
		 br=new BufferedInputStream(new FileInputStream("F:/An.java"));
		 ps=new PrintStream(soc.getOutputStream());
			int len=-1;
			byte[] by=new byte[1024];
			while((len=br.read(by))!=-1){
				ps.write(by);
				ps.flush();
			}
			
	    } catch ( IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	    }finally{
	    	try {
	    		if(ps!=null)
	    		ps.close();
	    		if(br!=null)
				br.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	    }
	
}
   

}
