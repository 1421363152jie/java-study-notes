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
	 * �����ļ����նˣ�
	 */
     private int post;

	public Server(int post) {
		super();
		this.post = post;
	}

	@Override
	public void run() {
     // public static void main(String[] args){
	  //�����˿ڣ��Զ˿ڽ��м���
		ByteArrayInputStream du=null;
		DatagramSocket duan=null;
		BufferedOutputStream xie=null;
		try {
			 duan=new DatagramSocket(post);
			//�����ֽ������������
			byte[] by=new byte[1024*64];
			System.out.println("�����������!");
			//����������ݰ�
			DatagramPacket bao=new DatagramPacket(by, by.length);
			//���ݰ����ռ���
			duan.receive(bao);
			//�����ֽ���������ȡ�ļ���Ϣд�뵽ָ���̷���
			du=new ByteArrayInputStream(by);
			//�����ֽ������д���ļ�
		   xie=new BufferedOutputStream(new FileOutputStream("F:wen.java"));
			int len;
			while((len=du.read(by))!=-1){
				xie.write(by, 0, len);
				//��÷��Ͷ˵�ID
				xie.flush();
				InetAddress ia=bao.getAddress();
				System.out.println(ia.getHostAddress()+"�������ļ�");
				
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