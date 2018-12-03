package cn.tc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	  //����˶˿�
     private int post;
     
      public TCPServer(int post) {
		super();
		this.post = post;
		recevice r=new recevice();
		Thread t1=new Thread(r);
		t1.start();
	}

	class recevice implements Runnable{

		@Override
		public void run() {
		  //��������˵�Socket
			ServerSocket s=null;
			Socket ss=null;
			BufferedReader br=null;
			try {
				s=new ServerSocket(post);
				System.out.println("�����Ѿ�����");
				//����Socket
				ss=s.accept();
				//��ÿͻ��˵�IP
				InetAddress ia=ss.getInetAddress();
				//���������
				InputStream in=ss.getInputStream();
				br=new BufferedReader(new InputStreamReader(in));
				String line=null;
				while((line=br.readLine())!=null){
					System.out.println(ia.getHostAddress()+"˵"+line);
					if("�˳�".equals(line)){
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
					try {
						if(br!=null){
						br.close();
						}
						if(s!=null){
							s.close();
						}
						if(ss!=null){
							ss.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		}
    	  
      }
	   public static void main(String[] args){
		   new TCPServer(12000);
	   }
  
}
