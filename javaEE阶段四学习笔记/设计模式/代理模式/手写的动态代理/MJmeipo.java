package cn.mj.proxy.jdk.my.proxxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MJmeipo implements MJInvocationHandler{

	private Object obj;

	//获取被代理的对象
	public Object getInstance(Object object) throws Exception{
		this.obj=object;
		System.out.println(this.getClass());
		return MJProxy.newProxyInstance(new MJClassLoader(), object.getClass().getInterfaces(), this);
	}
	
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
			System.out.println("提要求给媒婆!");
			System.out.println("选取合适的妹子");
			Object invoke = method.invoke(obj, args);
			System.out.println("最终找到，你看合适不?");
			return invoke;
	}

}
