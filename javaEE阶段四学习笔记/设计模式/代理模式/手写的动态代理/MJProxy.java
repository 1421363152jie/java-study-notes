package cn.mj.proxy.jdk.my.proxxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.annotation.processing.Completion;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;



//ʵ�������������Ĵ���
public class MJProxy {

	private static String ln="\r\n";
	
	 public static Object newProxyInstance(MJClassLoader loader,
             Class<?>[] interfaces,
             MJInvocationHandler h){
		
		 try {
			 //1,����Դ����
			 String proxySrc=generateSrc(interfaces[0]);
			 
			 //2����������Դ������������̱���Ϊ��java�ļ�
			String filePath = MJProxy.class.getResource("").getPath();
			  File file=new File(filePath+"$Proxy0.java");
			  FileWriter fw=new  FileWriter(file);
			  fw.write(proxySrc);
			  fw.flush();
			  fw.close();
		
		 //3������Դ���룬�������ɡ�class�ļ�
		 JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
		 StandardJavaFileManager manager=compiler.getStandardFileManager(null, null, null);
		 Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);
		 CompilationTask task=compiler.getTask(null, manager, null, null, null, iterable);
		 task.call();
		 manager.close();
		 //4,��class�ļ��е����ݣ���̬���ش�jvm
		 
		 
		 
		 
		 //5,���ر������Ĵ������
		 Class<?> class1 = loader.findClass("$Proxy0");
		 Constructor<?> constructor = class1.getConstructor(MJInvocationHandler.class);
		 file.delete();
		 return constructor.newInstance(h);
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	 }
	 
	 private static String generateSrc(Class<?> interfaces){
		 StringBuffer src=new StringBuffer();
		 src.append("package cn.mj.proxy.jdk.my.proxxy;"+ln);
		 src.append("import java.lang.reflect.Method;"+ln);
		 src.append("public class $Proxy0 implements "+interfaces.getName()+"{"+ln);
		 src.append("MJInvocationHandler h;"+ln);
		 src.append("public  $Proxy0(MJInvocationHandler h){"+ln);
		 src.append("this.h=h;"+ln);
		 src.append("}"+ln);
		 for (Method m : interfaces.getMethods()) {
			src.append("public "+m.getReturnType().getName()+" "+m.getName()+"(){"+ln);
			src.append("try{"+ln);
			src.append("Method m ="+interfaces.getName()+".class.getMethod(\""+m.getName()+"\",new Class[]{});"+ln);
			src.append("this.h.invoke(this,m,null);"+ln);
			src.append("}catch(Throwable e){"+ln+"e.printStackTrace();"+ln+"}"+ln);
			src.append("}"+ln);
		}
		 src.append("}");	 
		return src.toString();
	 }
}
