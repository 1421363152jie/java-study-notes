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
		  //�����ͻ���
	    try {
			DatagramSocket ds=new DatagramSocket();
			//�����ֽ������������ļ�
			BufferedInputStream dur=new BufferedInputStream(new FileInputStream("D:/mt.jpg"));
			//д�뵽�ֽ�������
			byte[] by=new byte[1024*64];
			int len=-1;
			while((len=dur.read(by))!=-1){
				InetAddress ia=InetAddress.getByName(id);
				//�����������
				DatagramPacket dp=new DatagramPacket(by, len, ia,post);
				//��������
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
