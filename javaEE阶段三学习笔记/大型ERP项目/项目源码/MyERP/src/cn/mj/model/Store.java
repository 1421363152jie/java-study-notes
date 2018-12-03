package cn.mj.model;

import java.util.Set;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */

public class Store implements java.io.Serializable {

	// Fields

	private Integer storeId;
	private Integer stockman;
	private String name;
	private String address;

	private Emp stockAdmin;
	
	
	private Set<StoreDetail> sDetails;
	// Constructors

	/** default constructor */
	public Store() {
	}

	/** full constructor */
	public Store(Integer stockman, String name, String address) {
		this.stockman = stockman;
		this.name = name;
		this.address = address;
	}

	
	
	// Property accessors
    


	public Emp getStockAdmin() {
		return stockAdmin;
	}

	public Set<StoreDetail> getsDetails() {
		return sDetails;
	}

	public void setsDetails(Set<StoreDetail> sDetails) {
		this.sDetails = sDetails;
	}

	public void setStockAdmin(Emp stockAdmin) {
		this.stockAdmin = stockAdmin;
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStockman() {
		return this.stockman;
	}

	public void setStockman(Integer stockman) {
		this.stockman = stockman;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}