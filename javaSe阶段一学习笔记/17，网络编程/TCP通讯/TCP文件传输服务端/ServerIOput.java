package MyTCPBaoSong;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerIOput implements Runnable{
    private Socket soc;
    private int num;
	public ServerIOput(Socket soc, int num) {
		super();
		this.soc = soc;
		this.num = num;
	}
	@Override
	public void run() {
	    //创建字节输入流
		BufferedInputStream br=null;
		PrintStream ps=null;
		try {
		   br=new BufferedInputStream(soc.getInputStream());
			 ps=new PrintStream("D:/An.java");
			int len=-1;
			byte[] by=new byte[1024];
			while((len=br.read(by))!=-1){
				ps.write(by);
				ps.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
			if(ps!=null){
				ps.close();
			}
			if(br!=null){
					br.close();
			}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
    