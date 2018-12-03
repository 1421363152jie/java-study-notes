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
        * 创建可以接收文件的服务器
        */
		@Override
		public void run() {
			//创建服务端
			ServerSocket fs=null;
			//创建输入流读取数据
			BufferedInputStream bi=null;
			//创建输出流写入数据到指定盘符
			BufferedOutputStream bo=null;
			 Socket ks=null;
         try {
			 fs=new ServerSocket(post);
			 System.out.println("服务器已经启动,等待连接...");
			 //监听端口，获得连接来客户端
			  ks=fs.accept();
			 //获得连接的客户端ID
			 InetAddress ia=ks.getInetAddress();
			 System.out.println(ia.getHostAddress()+"成功接入!");
			 bi=new BufferedInputStream(ks.getInputStream());
			 bo=new BufferedOutputStream(new FileOutputStream("F:/tui.jpg"));
			 int len=-1;
			 byte[] by=new byte[1024];
			 while((len=bi.read(by))!=-1){
				 bo.write(by);
				 System.out.println("接收"+ia.getHostAddress()+"文件完毕");
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
