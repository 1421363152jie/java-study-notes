package cn.mj.utils;

public interface ERPConstant {
	
	
	/*
	 * 采购订单
	 */
	public static final String  ORDER_PURCHASE="1";
	
	public static final String  ORDER_PURCHASE_TEXT="采购单";
	//////采购单状态/////////
	//待审核audit
	public static final String  ORDER_PURCHASE_AUDIT="1";
	
	public static final String  ORDER_PURCHASE_AUDIT_TEXT="待审核";
	//审核失败
	public static final String  ORDER_PURCHASE_AUDIT_NO_PASS="2";
	
	public static final String  ORDER_PURCHASE_AUDIT_NO_PASS_TEXT="审核失败";
	//审核通过
	public static final String  ORDER_PURCHASE_AUDIT_PASS="3";
	
	public static final String  ORDER_PURCHASE_AUDIT_PASS_TEXT="审核通过";
	
	
	/*carriage
	 *运输单 
	 */
   public static final String ORDER_CARRIAGE="2";
   
   public static final String ORDER_CARRIAGE_TEXT="运输单";
   //待采购
   public static final String ORDER_CARRIAGE_WAIT_PURCHASE="1";
   
   public static final String ORDER_CARRIAGE_WAIT_PURCHASE_TEXT="待采购";
   //采购中
   public static final String ORDER_CARRIAGE_WAIT_PURCHASEING="2";
   
   public static final String ORDER_CARRIAGE_WAIT_PURCHASEING_TEXT="采购中";
   
   /*storage
    *入库单 
    */
   public static final String ORDER_PUT_IN_STORAGE="3";
   
   public static final String ORDER_PUT_IN_STORAGE_TEXT="入库单";
   //待入库
   public static final String ORDER_PUT_IN_STORAGE_WAIT="1";
   
   public static final String ORDER_PUT_IN_STORAGE_WAIT_TEXT="待入库";
   //入库中
   public static final String ORDER_PUT_IN_STORAGE_BUYING="2";
   
   public static final String ORDER_PUT_IN_STORAGE_BUYING_TEXT="入库中";
   //入库完成
   public static final String ORDER_PUT_IN_STORAGE_PASS="3";
   
   public static final String ORDER_PUT_IN_STORAGE_PASS_TEXT="入库完成";
   

   
   /*market
    * 销售单
    */
   public static final String ORDER_MARKET="4";
   
   public static final String ORDER_MARKET_TEXT="销售单";
   
}
