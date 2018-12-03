package udp.chat;

public class MyChat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Chatroom f1=new  Chatroom("192.168.42.71");
		 Thread t1=new Thread(f1);
		 t1.start();

	}

}
