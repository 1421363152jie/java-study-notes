package cn.mj.ecps.utils;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EbMJUtis {

    private static HttpSolrServer ss;


    public static  String readProp(String propStr){
        InputStream in = EbMJUtis.class.getClassLoader().getResourceAsStream("ecps_configuration.properties");
        Properties prop=new Properties();
        String result=null;
        try {
            prop.load(in);
            result = prop.getProperty(propStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return result;
    }

    public static HttpSolrServer getHttpSolrServer(){
        if(ss!=null){
            return ss;
        }else{
            ss=new HttpSolrServer(EbMJUtis.readProp("solr_path"));
            return ss;
        }
    }

    public static  void printAjax(HttpServletResponse response,String result){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
