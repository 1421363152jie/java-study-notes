package UDPChat;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.imageio.stream.FileImageInputStream;

public class Clent implements Runnable{
    private String id;
    private int post;
	
		public Clent(String id, int post) {
		super();
		this.id = id;
		this.post = post;
	}


		@Override
	  public void run() {
 //public static void main(String[] args){
		  //创建客户端
	    try {
			DatagramSocket ds=new DatagramSocket();
			//创建字节输入流输入文件
			BufferedInputStream dur=new BufferedInputStream(new FileInputStream("D:/mt.jpg"));
			//写入到字节数组中
			byte[] by=new byte[1024*64];
			int len=-1;
			while((len=dur.read(by))!=-1){
				InetAddress ia=InetAddress.getByName(id);
				//打包发送数据
				DatagramPacket dp=new DatagramPacket(by, len, ia,post);
				//发送数据
				ds.send(dp);
			}
			dur.close();
			ds.close();
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
  
    
    
	

}
