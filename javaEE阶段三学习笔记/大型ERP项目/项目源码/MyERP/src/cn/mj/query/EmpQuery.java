package cn.mj.query;

import java.util.Date;

import cn.mj.model.Emp;

public class EmpQuery extends Emp{

	
	private Date startBirthday;
	
	
	private Date endBirthday;
	
	
	
	
	
	
	public Date getStartBirthday() {
		return startBirthday;
	}

	public void setStartBirthday(Date startBirthday) {
		this.startBirthday = startBirthday;
	}

	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}

	private int startNum ;
	
	private int pageNo ;

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	
	
}
