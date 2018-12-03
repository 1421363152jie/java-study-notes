package cn.mj.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConversion extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map map, String[] values, Class toclass) {
		Date date=null;
		if(values!=null&&values.length>0&&toclass==Date.class){
			try {
				date=new SimpleDateFormat("yyyy-MM-dd").parse(values[0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			
		}
			
		return date;
	}

	@Override
	public String convertToString(Map map, Object obj) {
		String datestr="";
		if(obj!=null&&(obj.getClass()==Date.class||obj.getClass()==Timestamp.class)){
			datestr=new SimpleDateFormat("yyyy-MM-dd").format(obj);
		}
		return datestr;
	}
	
	

}
