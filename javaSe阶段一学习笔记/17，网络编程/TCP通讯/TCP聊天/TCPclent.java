package cn.tc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPclent {
    private String ip;
    private int port;
	public TCPclent(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
		Sender se=new Sender();
		Thread t=new Thread(se);
		t.start();
	}
    class Sender implements Runnable{

		@Override
		public void run() {
			Socket s=null;
			BufferedReader br=null;
			BufferedWriter bw=null;
				try {
					 s=new Socket(ip,port);
					br=new BufferedReader(new InputStreamReader(System.in));
					//获得输出通道
					OutputStream out=s.getOutputStream();
					 bw=new BufferedWriter(new OutputStreamWriter(out));
					 String line=null;
					while((line=br.readLine())!=null){
						if("bye".equals(line)){
							break;
						}
						System.out.println("我说:\n"+line);
						bw.write(line);
						bw.newLine();
						bw.flush();
					
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					
						try {
							if(bw!=null){
							bw.close();
							}
							if(br!=null){
								br.close();
							}
							if(s!=null){
								s.close();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			
			
		}
        public static void main(String[] args) {
			new TCPclent("192.168.42.71", 10000);
		}
    	
    }


