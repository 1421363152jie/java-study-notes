package cn.mj.proxy.jdk.my.proxxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//代码生成，编译，重新动态load到jvm中
public class MJClassLoader extends ClassLoader{

	
	private File baseDir;
	
	public MJClassLoader(){
	   String basepath = MJClassLoader.class.getResource("").getPath();	
	 this.baseDir=new File(basepath);
	}
	
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String className=MJClassLoader.class.getPackage().getName()+"."+name;
		if(baseDir!=null){
			
			File classFile=new File(baseDir,name.replace("\\.", "/")+".class");
			if(classFile.exists()){
				FileInputStream in=null;
				ByteArrayOutputStream out=null;
				try{
					in=new FileInputStream(classFile);
		            out=new ByteArrayOutputStream();
		 			byte[] buff=new byte[1024];
					int len;
					while(((len=in.read(buff))!=-1)){
						out.write(buff, 0, len);
					}
					return defineClass(className, out.toByteArray(), 0,out.size());
				}catch(Exception e){
					e.printStackTrace();
				}finally{
				
					if(in!=null){
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(out!=null){
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					classFile.delete();
				}
			}
		}
		return null;
	}

	
}
