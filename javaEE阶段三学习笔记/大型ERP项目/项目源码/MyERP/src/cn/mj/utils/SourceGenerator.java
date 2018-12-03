package cn.mj.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class SourceGenerator {
       
	
	public static void main(String[] args) {
	    gener("Menu");
	}	
	public static void gener(String ClassName){
		try {
			generQuery(ClassName);
			generDao(ClassName);
			generDaoImpl(ClassName);
			generService(ClassName);
			generServiceImpl(ClassName);
			generDaoConfig(ClassName);
			generServiceConfig(ClassName);
			generAction(ClassName);
			generActionConfig(ClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成Dao
	 * @param ClassName
	 * @throws Exception
	 */
	public static void generDao(String ClassName) throws  Exception{
		BufferedReader br=new BufferedReader(new FileReader("config/cn/mj/moban/DemoDao.tlf"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("src/cn/mj/dao/"+ClassName+"Dao.java"));
		String line=null;
		String newline=null;
		while((line=br.readLine())!=null){
			newline=line.replace("Demo", ClassName);
			bw.write(newline);
			bw.newLine();
			bw.flush();
			
		}
		bw.close();
		br.close();
		
	}
	/**
	 * 生成Query
	 * @param ClassName
	 * @throws Exception
	 */
	public static void generQuery(String ClassName) throws  Exception{
		BufferedReader br=new BufferedReader(new FileReader("config/cn/mj/moban/DemoQuery.tlf"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("src/cn/mj/query/"+ClassName+"Query.java"));
		String line=null;
		String newline=null;
		while((line=br.readLine())!=null){
			newline=line.replace("Demo", ClassName);
			bw.write(newline);
			bw.newLine();
			bw.flush();
			
		}
		bw.close();
		br.close();
		
	}
	
	/**
	 *生成DaoImpl
	 * @param ClassName
	 * @throws Exception
	 */
	public static void generDaoImpl(String ClassName) throws  Exception{
		BufferedReader br=new BufferedReader(new FileReader("config/cn/mj/moban/DemoDaoImpl.tlf"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("src/cn/mj/dao/impl/"+ClassName+"DaoImpl.java"));
		String line=null;
		String newline=null;
		while((line=br.readLine())!=null){
			newline=line.replace("Demo", ClassName);
			bw.write(newline);
			bw.newLine();
			bw.flush();
			
		}
		bw.close();
		br.close();
		
	}
	
	/**
	 * 生成Service
	 * @param ClassName
	 * @throws Exception
	 */
	public static void generService(String ClassName) throws  Exception{
		BufferedReader br=new BufferedReader(new FileReader("config/cn/mj/moban/DemoService.tlf"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("src/cn/mj/service/"+ClassName+"Service.java"));
		String line=null;
		String newline=null;
		while((line=br.readLine())!=null){
			newline=line.replace("Demo", ClassName);
			bw.write(newline);
			bw.newLine();
			bw.flush();
			
		}
		bw.close();
		br.close();
		
	}
	/**
	 * 生成ServiceImpl
	 * @param ClassName
	 * @throws Exception
	 */
	
	public static void generServiceImpl(String ClassName) throws  Exception{
		//获得小写类名
		String lowerClassname=ClassName.substring(0,1).toLowerCase()+ClassName.substring(1);
		
		
		BufferedReader br=new BufferedReader(new FileReader("config/cn/mj/moban/DemoServiceImpl.tlf"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("src/cn/mj/service/impl/"+ClassName+"ServiceImpl.java"));
		String line=null;
		String newline=null;
		while((line=br.readLine())!=null){
			newline=line.replace("Demo", ClassName).replace("demo", lowerClassname);
			bw.write(newline);
			bw.newLine();
			bw.flush();
			
		}
		bw.close();
		br.close();
		
	}
	/**
	 * action代码生成
	 * @param ClassName
	 * @throws Exception
	 */
	public static void generAction(String ClassName) throws  Exception{
		//获得小写类名
		String lowerClassname=ClassName.substring(0,1).toLowerCase()+ClassName.substring(1);
		
		
		BufferedReader br=new BufferedReader(new FileReader("config/cn/mj/moban/DemoAction.tlf"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("src/cn/mj/controller/"+ClassName+"Action.java"));
		String line=null;
		String newline=null;
		while((line=br.readLine())!=null){
			newline=line.replace("Demo", ClassName).replace("demo", lowerClassname);
			bw.write(newline);
			bw.newLine();
			bw.flush();
			
		}
		bw.close();
		br.close();
		
	}
	
	
	/**
	 * 
	 * 生成Dao的配置
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public static void generDaoConfig(String ClassName) throws Exception{
		String lowerClassname=ClassName.substring(0,1).toLowerCase()+ClassName.substring(1);
		SAXReader reader=new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-dao.xml"));
		Element rootElement = doc.getRootElement();
		Element et = rootElement.addElement("bean").addAttribute("id", lowerClassname+"Dao").addAttribute("class","cn.mj.dao.impl."+ClassName+"DaoImpl");
		et.addElement("property").addAttribute("name","sessionFactory").addAttribute("ref", "sessionFactory");
		
		XMLWriter xw=new XMLWriter(new FileWriter("config/ApplicationContext-dao.xml"), OutputFormat.createPrettyPrint());
		xw.write(doc);
		xw.close();
		
		
		
		
	}
	
	/**
	 * service的配置
	 * @param ClassName
	 * @throws Exception
	 */
	public static void generServiceConfig(String ClassName) throws Exception{
		String lowerClassname=ClassName.substring(0,1).toLowerCase()+ClassName.substring(1);
		SAXReader reader=new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-service.xml"));
		Element rootElement = doc.getRootElement();
		Element et = rootElement.addElement("bean").addAttribute("id", lowerClassname+"Service").addAttribute("class","cn.mj.service.impl."+ClassName+"ServiceImpl");
		et.addElement("property").addAttribute("name",lowerClassname+"Dao").addAttribute("ref", lowerClassname+"Dao");
		
		XMLWriter xw=new XMLWriter(new FileWriter("config/ApplicationContext-service.xml"), OutputFormat.createPrettyPrint());
		xw.write(doc);
		xw.close();
		
		
		
		
	}
	
	/**
	 * action配置生成
	 * @param ClassName
	 * @throws Exception
	 */
	
	public static void generActionConfig(String ClassName) throws Exception{
		String lowerClassname=ClassName.substring(0,1).toLowerCase()+ClassName.substring(1);
		SAXReader reader=new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-action.xml"));
		Element rootElement = doc.getRootElement();
		Element et = rootElement.addElement("bean").addAttribute("id", lowerClassname+"Action").addAttribute("class","cn.mj.controller."+ClassName+"Action").addAttribute("scope", "prototype");
		et.addElement("property").addAttribute("name",lowerClassname+"Service").addAttribute("ref", lowerClassname+"Service");
		
		XMLWriter xw=new XMLWriter(new FileWriter("config/ApplicationContext-action.xml"), OutputFormat.createPrettyPrint());
		xw.write(doc);
		xw.close();
		
		
		
		
	}
	
	
	
}
