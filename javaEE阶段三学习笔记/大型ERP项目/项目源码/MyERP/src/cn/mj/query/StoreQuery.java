package cn.mj.query;

import java.util.Date;

import cn.mj.model.Store;


public class StoreQuery extends Store{
	
	
	
	private String AdminName;
	
	
	
	
	private Integer pageNo;
	
	private Integer startNum;
	
    
	public String getAdminName() {
		return AdminName;
	}

	public void setAdminName(String adminName) {
		AdminName = adminName;
	}

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
