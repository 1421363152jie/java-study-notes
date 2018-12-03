package UDPChat;

public class Chatrom {
      public static void main(String[] args) {
		Server sev=new Server(12001);
		Clent cle=new Clent("192.168.42.71",12000);
		Thread t1=new Thread(sev);
		Thread t2=new Thread(cle);
		t1.start();
		t2.start();
	}
	
}
