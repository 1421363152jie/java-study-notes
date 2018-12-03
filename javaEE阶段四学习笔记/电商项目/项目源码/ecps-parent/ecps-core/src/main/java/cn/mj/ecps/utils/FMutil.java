package cn.mj.ecps.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FMutil {
	
	//ftlName:模板名称
	//map:数据
	//file:生成的文件名字
	//ftlName + map = file
	public static void outputFile(String ftlName, Map<String, Object> map, String file) throws Exception{
		//创建freemarker的配置对象
		Configuration config = new Configuration();
		
		config.setDefaultEncoding("UTF-8");
		//加载模板文件的位置，包名，注意包名前要有/
		config.setClassForTemplateLoading(FMutil.class, "/ftl/");
		//获得模板对象
		Template template = config.getTemplate(ftlName);
		String path = EbMJUtis.readProp("deploy_html_path");
		//注意使用OutputStreamWriter来指定编码
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path+"/"+file)),"UTF-8"));
		//把数据结合模板写到硬盘上
		template.process(map, writer);
	}
}