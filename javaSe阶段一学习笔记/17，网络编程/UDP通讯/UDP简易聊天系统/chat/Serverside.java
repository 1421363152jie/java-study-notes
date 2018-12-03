package udp.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/*
 * 服务端
 */
public class Serverside implements Runnable{
    private int pors;
    
	public Serverside(int pors) {
		super();
		this.pors = pors;
	}

	@Override
	public void run() {
	   //创建服务端对象
		DatagramSocket ds=null;
		try {
			ds=new DatagramSocket(pors);
			//定义接收数据数组
			byte[] by=new byte[1024];
			//定义数据包
			while(true){
			DatagramPacket dp=new DatagramPacket(by, by.length);
			//接收数据
			ds.receive(dp);
			//获得发送端的ID
			InetAddress ia=dp.getAddress();
			//解析数据
			byte[] bys=dp.getData();
			int leng=dp.getLength();
			String data=new String(bys, 0, leng);
			//输出数据
			System.out.println(ia.getHostAddress()+"说:\r\n"+data);
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ds!=null){
				ds.close();
			}
		}
		
	}

	  

}
