package udp.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/*
 * �����
 */
public class Serverside implements Runnable{
    private int pors;
    
	public Serverside(int pors) {
		super();
		this.pors = pors;
	}

	@Override
	public void run() {
	   //��������˶���
		DatagramSocket ds=null;
		try {
			ds=new DatagramSocket(pors);
			//���������������
			byte[] by=new byte[1024];
			//�������ݰ�
			while(true){
			DatagramPacket dp=new DatagramPacket(by, by.length);
			//��������
			ds.receive(dp);
			//��÷��Ͷ˵�ID
			InetAddress ia=dp.getAddress();
			//��������
			byte[] bys=dp.getData();
			int leng=dp.getLength();
			String data=new String(bys, 0, leng);
			//�������
			System.out.println(ia.getHostAddress()+"˵:\r\n"+data);
			
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
