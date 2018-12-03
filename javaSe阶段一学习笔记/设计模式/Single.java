package cn.mj.single;

//懒汉式(静态内部类)
public class Single {
	
	
	private static class LazyHolder{
		private static final Single ISTANCT=new Single(); 
	}

	//不将默认的构造方法私有化，相当于有一个默认的public的无参数构造方法，就意味着在代码
	//中随时可以new出来一个新的对象
   private Single(){
	   
   }
	
   public static final Single getInstance(){
	   //方法中的逻辑，是要在用户调用的时候才开始执行
	   //方法中的逻辑需要分配内存，也是调用是才分配的
	   return LazyHolder.ISTANCT;
   }
}
