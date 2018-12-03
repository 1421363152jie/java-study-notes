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
		   //�����ͻ���
			Socket sk=null;
			//������������ȡ�ļ�
			BufferedInputStream bi=null;
			//���������������ļ�
			BufferedOutputStream bo=null;
			try {
			 sk=new Socket(ip,post);
			 System.out.println("���ӷ������...");
			 bi=new BufferedInputStream(new FileInputStream("D:/mt.jpg"));
			 OutputStream ow=sk.getOutputStream();
			 bo=new BufferedOutputStream(ow);
			 int len=-1;
			 byte[] by=new byte[1024];
			 while((len=bi.read(by))!=-1){
				 bo.write(by);
				 bo.flush();
				 
			 }
			 System.out.println("�ļ��Ѿ�����!");
			 
			 
			 
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
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
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			
			}
			
		}
    	 
     }
     public static void main(String[] args) {
		new Clent("192.168.42.71", 14000);
	}
	

}
