package cn.mj.utils;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JSONUtils {

	public static void printArray(HttpServletResponse response,Collection coll,String[] exclude){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		JsonConfig jc=new JsonConfig();
		jc.setExcludes(exclude);
		JSONArray ja=JSONArray.fromObject(coll, jc);
		String result = ja.toString();
		try {
			response.getWriter().write(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void printObject(HttpServletResponse response,Object obj,String[] exclude){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		JsonConfig jc=new JsonConfig();
		jc.setExcludes(exclude);
	    JSONObject jo=JSONObject.fromObject(obj, jc);
		String result = jo.toString();
		try {
			response.getWriter().write(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}