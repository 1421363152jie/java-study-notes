package an.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clent {
     private String ip;
     private int post;
	public Clent(String ip, int post) {
		super();
		this.ip = ip;
		this.post = post;
		SendFile  sf=new SendFile();
		Thread t=new Thread(sf);
		t.start();
	}
     class SendFile  implements Runnable{

		@Override
		public void run() {
		   //创建客户端
			Socket sk=null;
			//创建输入流读取文件
			BufferedInputStream bi=null;
			//创建输入流发送文件
			BufferedOutputStream bo=null;
			try {
			 sk=new Socket(ip,post);
			 System.out.println("连接服务端中...");
			 bi=new BufferedInputStream(new FileInputStream("D:/mt.jpg"));
			 OutputStream ow=sk.getOutputStream();
			 bo=new BufferedOutputStream(ow);
			 int len=-1;
			 byte[] by=new byte[1024];
			 while((len=bi.read(by))!=-1){
				 bo.write(by);
				 bo.flush();
				 
			 }
			 System.out.println("文件已经传输!");
			 
			 
			 
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally{
				try {
					if(bo!=null){
					bo.close();
					}
					if(bi!=null){
						bi.close();
					}
					if(sk!=null){
						sk.close();
					}
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			
			}
			
		}
    	 
     }
     public static void main(String[] args) {
		new Clent("192.168.42.71", 14000);
	}
	

}
