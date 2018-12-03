package udp.chat;

public class Chatroom implements Runnable{
  
	 private String ip;
	 
	public Chatroom(String ip) {
		super();
		this.ip = ip;
	}

	@Override
	public void run() {
	//创建客户端
		clientside kh=new clientside(ip, 11002);
		//创建服务端
		Serverside fw=new Serverside(11001);
		//创建客户端线程
		Thread kht=new Thread(kh);
		//创建服务端的线程
		Thread fwt=new Thread(fw);
		//启动线程
		kht.start();
		fwt.start();
		
	}


}
