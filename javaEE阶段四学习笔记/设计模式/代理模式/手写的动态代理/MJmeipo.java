package cn.mj.proxy.jdk.my.proxxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MJmeipo implements MJInvocationHandler{

	private Object obj;

	//��ȡ������Ķ���
	public Object getInstance(Object object) throws Exception{
		this.obj=object;
		System.out.println(this.getClass());
		return MJProxy.newProxyInstance(new MJClassLoader(), object.getClass().getInterfaces(), this);
	}
	
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
			System.out.println("��Ҫ���ý��!");
			System.out.println("ѡȡ���ʵ�����");
			Object invoke = method.invoke(obj, args);
			System.out.println("�����ҵ����㿴���ʲ�?");
			return invoke;
	}

}
