package cn.mj.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.mj.utils.ERPConstant;

public class ERPOrderStateTag extends TagSupport {

	private String orderType;

	private String orderState;
	
	
	
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		String text="";
        switch (orderType) {
		case ERPConstant.ORDER_PURCHASE:
			switch (orderState) {
			case ERPConstant.ORDER_PURCHASE_AUDIT:
				text=text+ERPConstant.ORDER_PURCHASE_AUDIT_TEXT;
				break;
			case ERPConstant.ORDER_PURCHASE_AUDIT_NO_PASS:
				text=text+ERPConstant.ORDER_PURCHASE_AUDIT_NO_PASS_TEXT;
				break;	
			case ERPConstant.ORDER_PURCHASE_AUDIT_PASS:
				text=text+ERPConstant.ORDER_PURCHASE_AUDIT_PASS_TEXT;
				break;	
			};
			break;
		case ERPConstant.ORDER_CARRIAGE:
			switch (orderState) {
			case ERPConstant.ORDER_CARRIAGE_WAIT_PURCHASE:
				text=text+ERPConstant.ORDER_CARRIAGE_WAIT_PURCHASE_TEXT;
				break;
			case ERPConstant.ORDER_CARRIAGE_WAIT_PURCHASEING:
				text=text+ERPConstant.ORDER_CARRIAGE_WAIT_PURCHASEING_TEXT;
				break;	
			};
			break;	
		case ERPConstant.ORDER_PUT_IN_STORAGE:
			switch (orderState) {
			case ERPConstant.ORDER_PUT_IN_STORAGE_WAIT:
				text=text+ERPConstant.ORDER_PUT_IN_STORAGE_WAIT_TEXT;
				break;
			case ERPConstant.ORDER_PUT_IN_STORAGE_BUYING:
				text=text+ERPConstant.ORDER_PUT_IN_STORAGE_BUYING_TEXT;
				break;	
			case ERPConstant.ORDER_PUT_IN_STORAGE_PASS:
				text=text+ERPConstant.ORDER_PUT_IN_STORAGE_PASS_TEXT;
				break;	
			};	
			break;	
		case ERPConstant.ORDER_MARKET:
			text=text+ERPConstant.ORDER_MARKET_TEXT;
			break;
		};
		try {
			out.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}
	
	
	
}
