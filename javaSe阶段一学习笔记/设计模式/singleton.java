/**
��������
*/
public class Singleton {

  private static Singleton s=new Singleton();
  
  private Singleton(){
	  System.out.println("����1");
  }
  
  public static Singleton getSingleton(){
	  return s;
  }
  
 
}