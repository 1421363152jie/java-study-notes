package bn.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int post;

	public Server(int post) {
		super();
		this.post = post;
		Receive r=new Receive();
		Thread tr=new Thread(r);
		tr.start();
	}
    class Receive implements Runnable{
       /**
        * �������Խ����ļ��ķ�����
        */
		@Override
		public void run() {
			//���������
			ServerSocket fs=null;
			//������������ȡ����
			BufferedInputStream bi=null;
			//���������д�����ݵ�ָ���̷�
			BufferedOutputStream bo=null;
			 Socket ks=null;
         try {
			 fs=new ServerSocket(post);
			 System.out.println("�������Ѿ�����,�ȴ�����...");
			 //�����˿ڣ�����������ͻ���
			  ks=fs.accept();
			 //������ӵĿͻ���ID
			 InetAddress ia=ks.getInetAddress();
			 System.out.println(ia.getHostAddress()+"�ɹ�����!");
			 bi=new BufferedInputStream(ks.getInputStream());
			 bo=new BufferedOutputStream(new FileOutputStream("F:/tui.jpg"));
			 int len=-1;
			 byte[] by=new byte[1024];
			 while((len=bi.read(by))!=-1){
				 bo.write(by);
				 System.out.println("����"+ia.getHostAddress()+"�ļ����");
				 bo.flush();
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
				try {
					if(bo!=null){
					bo.close();
					}
					if(bi!=null){
						bi.close();
					}
					if(fs!=null){
						fs.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
			}
		}
         
         
			
		}
    	
    }
	public static void main(String[] args) {
		new Server(14000);
	}
}
