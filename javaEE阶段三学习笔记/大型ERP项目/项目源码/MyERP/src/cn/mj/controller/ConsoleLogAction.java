package cn.mj.controller;

import java.util.List;

import cn.mj.model.ConsoleLog;
import cn.mj.query.ConsoleLogQuery;
import cn.mj.service.ConsoleLogService;
import cn.mj.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class ConsoleLogAction extends BaseAction{



	private ConsoleLogService consoleLogService;
	
	
     private ConsoleLogQuery query=new  ConsoleLogQuery();
   
       private ConsoleLog consoleLog=new ConsoleLog();
  


	public ConsoleLogService getConsoleLogService() {
		return consoleLogService;
	}


	public void setConsoleLogService(ConsoleLogService consoleLogService) {
		this.consoleLogService = consoleLogService;
	}


	public ConsoleLogQuery getQuery() {
		return query;
	}


	public void setQuery(ConsoleLogQuery query) {
		this.query = query;
	}


	public ConsoleLog getConsoleLog() {
		return consoleLog;
	}


	public void setConsoleLog(ConsoleLog consoleLog) {
		this.consoleLog = consoleLog;
	}


	public String consoleLog_list(){
		ActionContext context = ActionContext.getContext();
         Integer pageNo = query.getPageNo();
	     if(pageNo==null){
	    	 query.setPageNo(1);
	     }
         
         
		Page page = consoleLogService.queryObjBycondition(query, exclude);
		context.put("page", page);
		
		return SUCCESS;
		
	}
/**
 * 操作日志的展示
 * @return
 */
	public String consoleLog_consoleLog(){
		ActionContext context = ActionContext.getContext();
		List<ConsoleLog> clist = consoleLogService.queryObjByconditionNoPage(query, exclude);
		context.put("clist", clist);
		return SUCCESS;
		
	}
	
	
}
