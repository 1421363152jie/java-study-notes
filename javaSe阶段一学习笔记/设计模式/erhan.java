/**
��������
**/

public class Singleton1 {

  private static Singleton1 s ;
  
  private Singleton1(){
	  System.out.println("����1");
  }
  
  public static Singleton1 getSingleton(){
	 if(s==null){
		 s=new Singleton1();
	 }
	 return s;
  }
  
 
}
