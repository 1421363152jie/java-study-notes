package udp.chat;

public class Chatroom implements Runnable{
  
	 private String ip;
	 
	public Chatroom(String ip) {
		super();
		this.ip = ip;
	}

	@Override
	public void run() {
	//�����ͻ���
		clientside kh=new clientside(ip, 11002);
		//���������
		Serverside fw=new Serverside(11001);
		//�����ͻ����߳�
		Thread kht=new Thread(kh);
		//��������˵��߳�
		Thread fwt=new Thread(fw);
		//�����߳�
		kht.start();
		fwt.start();
		
	}


}
