package UDPChat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server implements Runnable{
	/**
	 * 创建文件接收端，
	 */
     private int post;

	public Server(int post) {
		super();
		this.post = post;
	}

	@Override
	public void run() {
     // public static void main(String[] args){
	  //创建端口，对端口进行监听
		ByteArrayInputStream du=null;
		DatagramSocket duan=null;
		BufferedOutputStream xie=null;
		try {
			 duan=new DatagramSocket(post);
			//定义字节数组接收数据
			byte[] by=new byte[1024*64];
			System.out.println("服务端已启动!");
			//定义接收数据包
			DatagramPacket bao=new DatagramPacket(by, by.length);
			//数据包接收监听
			duan.receive(bao);
			//创建字节输入流读取文件信息写入到指定盘符下
			du=new ByteArrayInputStream(by);
			//创建字节输出流写入文件
		   xie=new BufferedOutputStream(new FileOutputStream("F:wen.java"));
			int len;
			while((len=du.read(by))!=-1){
				xie.write(by, 0, len);
				//获得发送端的ID
				xie.flush();
				InetAddress ia=bao.getAddress();
				System.out.println(ia.getHostAddress()+"发送啦文件");
				
			}	
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			   try {
				   if(xie!=null)
				  xie.close();
				   if(du!=null)
				  du.close();
				   if(duan!=null)
				   duan.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
		}
		
	}
     
	

}