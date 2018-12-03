package udp.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class clientside implements Runnable{
    private String ip;
    private int pors;
	public clientside(String ip, int pors) {
		super();
		this.ip = ip;
		this.pors = pors;
	}

	@Override
	public void run() {
		//创建客户端对象
		DatagramSocket kds=null;
		//创建输入流
		BufferedReader br=null;
		try {
			kds=new DatagramSocket();
			br=new BufferedReader(new InputStreamReader(System.in));
			//读取数据
			while(true){
			String line=br.readLine();
			//转换数据
			byte[] kby=line.getBytes();
			//创建发送目的IP对象
			InetAddress dia=InetAddress.getByName(ip);
			//打包数据
			DatagramPacket kdp=new DatagramPacket(kby, kby.length, dia, pors);
			//发送数据
			System.out.println("我说:\r\n"+line);
			kds.send(kdp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(kds!=null){
				kds.close();
			}
		}
		
		
	}


}
