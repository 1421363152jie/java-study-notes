package cn.mj.single;

//����ʽ(��̬�ڲ���)
public class Single {
	
	
	private static class LazyHolder{
		private static final Single ISTANCT=new Single(); 
	}

	//����Ĭ�ϵĹ��췽��˽�л����൱����һ��Ĭ�ϵ�public���޲������췽��������ζ���ڴ���
	//����ʱ����new����һ���µĶ���
   private Single(){
	   
   }
	
   public static final Single getInstance(){
	   //�����е��߼�����Ҫ���û����õ�ʱ��ſ�ʼִ��
	   //�����е��߼���Ҫ�����ڴ棬Ҳ�ǵ����ǲŷ����
	   return LazyHolder.ISTANCT;
   }
}
