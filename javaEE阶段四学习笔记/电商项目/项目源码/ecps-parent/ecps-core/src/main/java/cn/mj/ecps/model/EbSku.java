package cn.mj.ecps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EbSku {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SKU_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Long skuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.ITEM_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Long itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SKU
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private String sku;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SKU_PRICE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private BigDecimal skuPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SHOW_STATUS
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Short showStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.STOCK_INVENTORY
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Integer stockInventory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SKU_UPPER_LIMIT
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Integer skuUpperLimit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.LOCATION
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private String location;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SKU_IMG
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private String skuImg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SKU_SORT
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Integer skuSort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SKU_NAME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private String skuName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.MARKET_PRICE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private BigDecimal marketPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.CREATE_TIME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.UPDATE_TIME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.CREATE_USER_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Long createUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.UPDATE_USER_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Long updateUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.ORIGINAL_SKU_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Long originalSkuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.LAST_STATUS
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Short lastStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.MERCHANT_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Long merchantId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SKU_TYPE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Short skuType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.SALES
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Long sales;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.RES_CODE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private String resCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EB_SKU.PACK_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    private Integer packId;

    private  EbItem item;



    private List<EbSpecValue> specList;

    public List<EbSpecValue> getSpecList() {
        return specList;
    }

    public EbItem getItem() {
        return item;
    }

    public void setItem(EbItem item) {
        this.item = item;
    }

    public void setSpecList(List<EbSpecValue> specList) {
        this.specList = specList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SKU_ID
     *
     * @return the value of EB_SKU.SKU_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SKU_ID
     *
     * @param skuId the value for EB_SKU.SKU_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.ITEM_ID
     *
     * @return the value of EB_SKU.ITEM_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.ITEM_ID
     *
     * @param itemId the value for EB_SKU.ITEM_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SKU
     *
     * @return the value of EB_SKU.SKU
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public String getSku() {
        return sku;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SKU
     *
     * @param sku the value for EB_SKU.SKU
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SKU_PRICE
     *
     * @return the value of EB_SKU.SKU_PRICE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SKU_PRICE
     *
     * @param skuPrice the value for EB_SKU.SKU_PRICE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SHOW_STATUS
     *
     * @return the value of EB_SKU.SHOW_STATUS
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Short getShowStatus() {
        return showStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SHOW_STATUS
     *
     * @param showStatus the value for EB_SKU.SHOW_STATUS
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setShowStatus(Short showStatus) {
        this.showStatus = showStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.STOCK_INVENTORY
     *
     * @return the value of EB_SKU.STOCK_INVENTORY
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Integer getStockInventory() {
        return stockInventory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.STOCK_INVENTORY
     *
     * @param stockInventory the value for EB_SKU.STOCK_INVENTORY
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setStockInventory(Integer stockInventory) {
        this.stockInventory = stockInventory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SKU_UPPER_LIMIT
     *
     * @return the value of EB_SKU.SKU_UPPER_LIMIT
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Integer getSkuUpperLimit() {
        return skuUpperLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SKU_UPPER_LIMIT
     *
     * @param skuUpperLimit the value for EB_SKU.SKU_UPPER_LIMIT
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSkuUpperLimit(Integer skuUpperLimit) {
        this.skuUpperLimit = skuUpperLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.LOCATION
     *
     * @return the value of EB_SKU.LOCATION
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.LOCATION
     *
     * @param location the value for EB_SKU.LOCATION
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SKU_IMG
     *
     * @return the value of EB_SKU.SKU_IMG
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public String getSkuImg() {
        return skuImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SKU_IMG
     *
     * @param skuImg the value for EB_SKU.SKU_IMG
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SKU_SORT
     *
     * @return the value of EB_SKU.SKU_SORT
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Integer getSkuSort() {
        return skuSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SKU_SORT
     *
     * @param skuSort the value for EB_SKU.SKU_SORT
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSkuSort(Integer skuSort) {
        this.skuSort = skuSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SKU_NAME
     *
     * @return the value of EB_SKU.SKU_NAME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SKU_NAME
     *
     * @param skuName the value for EB_SKU.SKU_NAME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.MARKET_PRICE
     *
     * @return the value of EB_SKU.MARKET_PRICE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.MARKET_PRICE
     *
     * @param marketPrice the value for EB_SKU.MARKET_PRICE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.CREATE_TIME
     *
     * @return the value of EB_SKU.CREATE_TIME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.CREATE_TIME
     *
     * @param createTime the value for EB_SKU.CREATE_TIME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.UPDATE_TIME
     *
     * @return the value of EB_SKU.UPDATE_TIME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.UPDATE_TIME
     *
     * @param updateTime the value for EB_SKU.UPDATE_TIME
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.CREATE_USER_ID
     *
     * @return the value of EB_SKU.CREATE_USER_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.CREATE_USER_ID
     *
     * @param createUserId the value for EB_SKU.CREATE_USER_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.UPDATE_USER_ID
     *
     * @return the value of EB_SKU.UPDATE_USER_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.UPDATE_USER_ID
     *
     * @param updateUserId the value for EB_SKU.UPDATE_USER_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.ORIGINAL_SKU_ID
     *
     * @return the value of EB_SKU.ORIGINAL_SKU_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Long getOriginalSkuId() {
        return originalSkuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.ORIGINAL_SKU_ID
     *
     * @param originalSkuId the value for EB_SKU.ORIGINAL_SKU_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setOriginalSkuId(Long originalSkuId) {
        this.originalSkuId = originalSkuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.LAST_STATUS
     *
     * @return the value of EB_SKU.LAST_STATUS
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Short getLastStatus() {
        return lastStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.LAST_STATUS
     *
     * @param lastStatus the value for EB_SKU.LAST_STATUS
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setLastStatus(Short lastStatus) {
        this.lastStatus = lastStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.MERCHANT_ID
     *
     * @return the value of EB_SKU.MERCHANT_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.MERCHANT_ID
     *
     * @param merchantId the value for EB_SKU.MERCHANT_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SKU_TYPE
     *
     * @return the value of EB_SKU.SKU_TYPE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Short getSkuType() {
        return skuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SKU_TYPE
     *
     * @param skuType the value for EB_SKU.SKU_TYPE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSkuType(Short skuType) {
        this.skuType = skuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.SALES
     *
     * @return the value of EB_SKU.SALES
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Long getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.SALES
     *
     * @param sales the value for EB_SKU.SALES
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setSales(Long sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.RES_CODE
     *
     * @return the value of EB_SKU.RES_CODE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public String getResCode() {
        return resCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.RES_CODE
     *
     * @param resCode the value for EB_SKU.RES_CODE
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EB_SKU.PACK_ID
     *
     * @return the value of EB_SKU.PACK_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public Integer getPackId() {
        return packId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EB_SKU.PACK_ID
     *
     * @param packId the value for EB_SKU.PACK_ID
     *
     * @mbg.generated Tue Jul 10 11:04:27 CST 2018
     */
    public void setPackId(Integer packId) {
        this.packId = packId;
    }
}