package cn.mj.model;

public class BillBean {
	
	/**
	 * 商品名称
	 */
	private String pname;
	
	/**
	 * 商品总量
	 */
	private Long  count;

	
	
	
	
	public BillBean() {
		super();
	}

	public BillBean(String pname, Long count) {
		super();
		this.pname = pname;
		this.count = count;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}


	
	

}
