package cn.mj.query;

import java.util.Date;

import cn.mj.model.ConsoleLog;


public class ConsoleLogQuery extends ConsoleLog{
	
	private Integer pageNo;
	
	private Integer startNum;
	
    
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	

}
