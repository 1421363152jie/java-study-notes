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
		//�����ͻ��˶���
		DatagramSocket kds=null;
		//����������
		BufferedReader br=null;
		try {
			kds=new DatagramSocket();
			br=new BufferedReader(new InputStreamReader(System.in));
			//��ȡ����
			while(true){
			String line=br.readLine();
			//ת������
			byte[] kby=line.getBytes();
			//��������Ŀ��IP����
			InetAddress dia=InetAddress.getByName(ip);
			//�������
			DatagramPacket kdp=new DatagramPacket(kby, kby.length, dia, pors);
			//��������
			System.out.println("��˵:\r\n"+line);
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
