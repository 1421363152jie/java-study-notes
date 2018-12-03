package cn.mj.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.mj.utils.ERPConstant;

public class ERPOrderTypeTag extends TagSupport {

	private String orderType;

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
			text=text+ERPConstant.ORDER_PURCHASE_TEXT;
			break;
		case ERPConstant.ORDER_CARRIAGE:
			text=text+ERPConstant.ORDER_CARRIAGE_TEXT;
			break;	
		case ERPConstant.ORDER_PUT_IN_STORAGE:
			text=text+ERPConstant.ORDER_PUT_IN_STORAGE_TEXT;
			break;	
		case ERPConstant.ORDER_MARKET:
			text=text+ERPConstant.ORDER_MARKET_TEXT;
			break;
		};
		try {
			out.write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.doStartTag();
		
		
		
		
	}
	
	
	
}
